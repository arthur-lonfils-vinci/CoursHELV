#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <sys/sem.h>
#include <fcntl.h>

#include "utils.h"

#define MAX_CHAR 20

int main() {
	char *name_file = smalloc(MAX_CHAR);

	swrite(STDOUT_FILENO, "Enter the name of the file: ", 28);
	fflush(stdout);
	sread(STDIN_FILENO, name_file, MAX_CHAR); 

	// Remove the newline character from the input
	name_file[strcspn(name_file, "\n")] = 0;    

	// Create a file with the name read from stdin
	int fd = sopen(name_file, O_CREAT | O_WRONLY | O_TRUNC | O_RDWR, S_IRUSR | S_IWUSR | S_IXUSR);
	close(fd);
	
	// Explicitly set executable permissions
	if (chmod(name_file, S_IRUSR | S_IWUSR | S_IXUSR) == -1) {
		perror("chmod");
		exit(EXIT_FAILURE);
	}

	pid_t pid = sfork();

	if (pid == 0) {
		// Child process
		swrite(STDOUT_FILENO, "Valid file name & permissions set\n", 36);
		fflush(stdout);
		execlp("ls", "ls", "-l", name_file, NULL);
		perror("execlp");
		exit(EXIT_FAILURE);
	}

	waitpid(pid, NULL, 0);
	// Write the shebang line to the file
	int fd2 = sopen(name_file, O_WRONLY | O_APPEND, 0);
	
	const char *shebang = "#!/bin/bash\n";
	if (write(fd2, shebang, strlen(shebang)) == -1) {
		perror("write");
		close(fd2);
		exit(EXIT_FAILURE);
	}

	swrite(STDOUT_FILENO, "Enter each lines of the script (type 'exit' to finish):\n", 56);
	fflush(stdout);
	while (true) {
		char *line = smalloc(MAX_CHAR);
		ssize_t bytes_read = sread(STDIN_FILENO, line, MAX_CHAR);
		if (bytes_read <= 0) {
			free(line);
			break; // End of input
		}
		
		// Remove the newline character from the input
		line[strcspn(line, "\n")] = 0;

		if (strcmp(line, "exit") == 0) {
			free(line);
			break; // Exit command received
		}

		write(fd2, line, strlen(line));
		write(fd2, "\n", 1);
		free(line);
	}

	// Check the file content
	pid_t pid2 = sfork();
	if (pid2 == 0) {
		// Child process
		swrite(STDOUT_FILENO, "Valid file content:\n", 21);
		fflush(stdout);
		execlp("cat", "cat", name_file, NULL);
		perror("execlp");
		exit(EXIT_FAILURE);
	}
	waitpid(pid2, NULL, 0);
	
	close(fd2);

	// Execute the script
	pid_t pid3 = sfork();
	if (pid3 == 0) {
		// Child process
		swrite(STDOUT_FILENO, "Executing the script...\n", 24);
		fflush(stdout);
		execl("/bin/bash", "bash", name_file, NULL);  // Use bash explicitly to run the script
		perror("exec");
		exit(EXIT_FAILURE);
	}
	
	int status;
	waitpid(pid3, &status, 0);
	
	if (WIFEXITED(status)) {
		if (WEXITSTATUS(status) == 0) {
			swrite(STDOUT_FILENO, "Script executed successfully\n", 29);
		} else {
			char msg[100];
			int len = snprintf(msg, sizeof(msg), "Script execution failed with exit code %d\n", WEXITSTATUS(status));
			swrite(STDOUT_FILENO, msg, len);
		}
	} else {
		swrite(STDOUT_FILENO, "Script execution failed\n", 24);
	}
	fflush(stdout);

	free(name_file);
	
	return 0;
}