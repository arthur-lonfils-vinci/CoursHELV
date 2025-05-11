#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include <sys/wait.h>
#include <sys/ipc.h>
#include <unistd.h>

#include "utils.h"

int main(int argc, char *argv[]) {
	// Check command line arguments
	if (argc < 3) {
		fprintf(stderr, "Usage: %s <cmd_1> <cmd_2> [arg_1 arg_2 ...]\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	// Store command names for clarity
	char *cmd1 = argv[1]; // First command without arguments
	char *cmd2 = argv[2]; // Second command, may have arguments
	
	// Create a pipe
	int pipe_fd[2]; // pipe_fd[0] is read end, pipe_fd[1] is write end
	if (pipe(pipe_fd) == -1) {
		perror("pipe");
		exit(EXIT_FAILURE);
	}

	// Create first child process for cmd1
	pid_t pid1 = fork();
	if (pid1 < 0) {
		perror("fork for cmd1");
		exit(EXIT_FAILURE);
	}

	if (pid1 == 0) {
		// Child process 1 (cmd1)
		// Close unused read end of the pipe
		close(pipe_fd[0]);

		// Redirect stdout to the write end of the pipe
		// Output of cmd1 will go to the pipe
		if (dup2(pipe_fd[1], STDOUT_FILENO) == -1) {
			perror("dup2 for cmd1");
			exit(EXIT_FAILURE);
		}

		// Close the original write end as it's duplicated to stdout
		close(pipe_fd[1]);

		// Execute the first command (without arguments)
		execlp(cmd1, cmd1, NULL);
		
		// If execlp returns, it failed
		perror("execlp for cmd1");
		exit(EXIT_FAILURE);
	}

	// Create second child process for cmd2
	pid_t pid2 = fork();
	if (pid2 < 0) {
		perror("fork for cmd2");
		exit(EXIT_FAILURE);
	}

	if (pid2 == 0) {
		// Child process 2 (cmd2 with arguments)
		// Close unused write end of the pipe
		close(pipe_fd[1]);

		// Redirect stdin to the read end of the pipe
		// cmd2 will read input from the pipe
		if (dup2(pipe_fd[0], STDIN_FILENO) == -1) {
			perror("dup2 for cmd2");
			exit(EXIT_FAILURE);
		}

		// Close the original read end as it's duplicated to stdin
		close(pipe_fd[0]);

		 // Prepare arguments array for cmd2
		// The +1 is for the command itself as the first argument
		char **cmd2_args = (char **)malloc((argc - 1) * sizeof(char *));
		if (cmd2_args == NULL) {
			perror("malloc for cmd2_args");
			exit(EXIT_FAILURE);
		}
		
		// Copy the command name as first argument (argv[0] for the new process)
		cmd2_args[0] = cmd2;
		
		// Copy all additional arguments
		for (int i = 3; i < argc; i++) {
			cmd2_args[i - 2] = argv[i];
		}
		
		// NULL-terminate the argument list
		cmd2_args[argc - 2] = NULL;
		
		// Execute the second command with its arguments
		execvp(cmd2, cmd2_args);
		
		// If execvp returns, it failed
		perror("execvp for cmd2");
		free(cmd2_args);
		exit(EXIT_FAILURE);
	}

	// Parent process
	
	// Close both ends of the pipe in the parent
	// This is important to avoid deadlocks
	close(pipe_fd[0]);
	close(pipe_fd[1]);

	// Wait for both child processes to complete
	int status1, status2;
	waitpid(pid1, &status1, 0);
	waitpid(pid2, &status2, 0);

	// Check if both commands executed successfully
	if (WIFEXITED(status1) && WEXITSTATUS(status1) != 0) {
		fprintf(stderr, "Command '%s' failed with exit status %d\n", 
				cmd1, WEXITSTATUS(status1));
	}

	if (WIFEXITED(status2) && WEXITSTATUS(status2) != 0) {
		fprintf(stderr, "Command '%s' failed with exit status %d\n", 
				cmd2, WEXITSTATUS(status2));
	}
	
	return 0;
}