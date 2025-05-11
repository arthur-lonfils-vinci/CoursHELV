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
	int pipe_parent_to_child[2]; // Parent writes, child reads
	int pipe_child_to_parent[2]; // Child writes, parent reads
	
	if (pipe(pipe_parent_to_child) == -1 || pipe(pipe_child_to_parent) == -1) {
		perror("pipe");
		exit(EXIT_FAILURE);
	}

	pid_t pid = sfork();
	
	if (pid != 0) {
		// Parent process
		close(pipe_parent_to_child[0]); // Close unused read end
		close(pipe_child_to_parent[1]); // Close unused write end

		while (1) {
			// read integer from stdin
			int num;
			if (scanf("%d", &num) != 1) {
				// If Ctrl+D is pressed or invalid input, send -1 to the child process
				num = -1; // End of input

				// Write -1 to the pipe
				write(pipe_parent_to_child[1], &num, sizeof(num));

				// Read the average from the child process
				double average;
				read(pipe_child_to_parent[0], &average, sizeof(average));
				printf("Average from child: %f\n", average);
				
				break; // End of input
			} else {
				// Write to the pipe
				write(pipe_parent_to_child[1], &num, sizeof(num));

				int sumFromChild;
				// Read the sum from the child process
				read(pipe_child_to_parent[0], &sumFromChild, sizeof(sumFromChild));
				printf("Sum from child: %d\n", sumFromChild);
			}
		}
		close(pipe_parent_to_child[1]); // Close write end
		close(pipe_child_to_parent[0]); // Close read end
		
		wait(NULL); // Wait for the child process to finish
	} else {
		// Child process
		close(pipe_parent_to_child[1]); // Close unused write end
		close(pipe_child_to_parent[0]); // Close unused read end

		int num;
		int sumOfAll = 0;
		int count = 0;

		while (read(pipe_parent_to_child[0], &num, sizeof(num)) > 0) {
			if (num == -1) {
				// calculate the average
				double average = (count > 0) ? (double)sumOfAll / count : 0;
				write(pipe_child_to_parent[1], &average, sizeof(average));
				break; // End of input
			}
			
			// Sum the numbers
			sumOfAll += num;
			count++;
			
			// Send current sum back to parent
			write(pipe_child_to_parent[1], &sumOfAll, sizeof(sumOfAll));
		}

		close(pipe_parent_to_child[0]); // Close read end
		close(pipe_child_to_parent[1]); // Close write end
		exit(EXIT_SUCCESS);
	}
	
	return 0;
}