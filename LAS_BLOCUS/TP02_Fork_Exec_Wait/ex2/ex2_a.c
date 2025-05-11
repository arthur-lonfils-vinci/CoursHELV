#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>

#include "utils.h"

int main() {
	pid_t child = sfork();

	if (child == 0) {
		// Child process
		printf("4 5 6\n");
		fflush(stdout);
		exit(0); // Exit the child process
	} else {
		// Parent process
		int status;
		waitpid(child, &status, 0); // Wait for the child process to finish
		int exit_code = WEXITSTATUS(status); // Get the exit code of the child process
		if (WIFEXITED(status)) {
			printf("Child process %d exited normally\n", child);
		} else {
			printf("Child process %d exited abnormally\n", child);
		}
		// Print the exit code
		printf("Exit code: %d\n", exit_code);
		printf("1 2 3\n");
		fflush(stdout);
	}
}