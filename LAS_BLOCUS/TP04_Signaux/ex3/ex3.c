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
#include <signal.h>

#include "utils.h"

// Signal handler for SIGPIPE
void sigpipe_handler(int signum) {
    fprintf(stderr, "Parent received SIGPIPE: The pipe has no readers.\n");
    exit(signum);  // Exit with the signal number as the exit code
}

int main() {
	int pipefd[2];
	
	if (pipe(pipefd) == -1) {
		perror("pipe");
		exit(EXIT_FAILURE);
	}

	// Set up signal handler for SIGPIPE in the parent process
	ssigaction(SIGPIPE, sigpipe_handler);

	pid_t pid = sfork();
	
	if (pid != 0) {
		// Parent process
		close(pipefd[0]); // Close the read end of the pipe

		while (1) {
			char buffer[1024];
			int bytesRead = read(STDIN_FILENO, buffer, sizeof(buffer) - 1);
			if (bytesRead <= 0) {
				break; // End of file or error
			}
			buffer[bytesRead] = '\0'; // Null-terminate the string

			// Write to the pipe
			write(pipefd[1], buffer, bytesRead);
		}
		close(pipefd[1]); // Close the write end of the pipe
		
		wait(NULL); // Wait for the child process to finish
	} else {
		// Child process
		// Close both ends of the pipe, including the read end
		close(pipefd[0]); // Close the read end of the pipe
		close(pipefd[1]); // Close the write end of the pipe

		// The read loop is now removed since we've closed the pipe
		// This will cause the parent to receive SIGPIPE when it writes to the pipe
		
		exit(EXIT_SUCCESS);
	}
	
	return 0;
}