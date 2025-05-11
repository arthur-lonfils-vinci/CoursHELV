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

int main() {
	int pipefd[2];
	
	if (pipe(pipefd) == -1) {
		perror("pipe");
		exit(EXIT_FAILURE);
	}

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
		close(pipefd[1]); // Close the write end of the pipe

		char buffer[1024];
		int bytesRead;
		while ((bytesRead = read(pipefd[0], buffer, sizeof(buffer))) > 0) {
			// Convert lowercase to uppercase
			for (int i = 0; i < bytesRead; i++) {
				if (buffer[i] >= 'a' && buffer[i] <= 'z') {
					buffer[i] = buffer[i] - 'a' + 'A';
				} else if (buffer[i] >= 'A' && buffer[i] <= 'Z') {
					buffer[i] = buffer[i] - 'A' + 'a';
				}
			}
			write(STDOUT_FILENO, buffer, bytesRead);
		}
		close(pipefd[0]); // Close the read end of the pipe
		exit(EXIT_SUCCESS);
	}
	
	return 0;
}