#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>

// Utils header
#include "utils_v3.h"

// Global variables
int pipefd[2] = {-1, -1};
int end_parent = 0;
int end_child = 0;
int signal_received = 0;


// Handler for SIGUSR1
void sigusr1_handler() {
	signal_received = 1;
}


// Handler for SIGINT
void sigint_handler() {
	end_child = 1;
	end_parent = 1;
}


// Function to handle the child process
void child_handler() {

	// Set the signal handler for SIGUSR1
	ssigaction(SIGUSR1, sigusr1_handler);

	// Close the unused read-end of the pipe
	sclose(pipefd[0]);

	int degree = 0;

	while (1) {	// while till it received a SIGINT
		if (signal_received == 1) { // if SIGUSR1 received
			swrite(pipefd[1], &degree, sizeof(int));
			degree += 2;
			signal_received = 0;
		}

		if (end_child == 1) { // if SIGINT received
			break;
		}
	}

	colorOn(1, PURPLE_TEXT);
	printf("\n[Capteur] Fin de process\n");
	colorOff();

	// Close write-end of the pipe
	sclose(pipefd[1]);
	
	exit(EXIT_SUCCESS);
}


// Main process
int main() {

	// Set the signal handler for SIGINT
	ssigaction(SIGINT, sigint_handler);

	// Set pipe
	spipe(pipefd);

	pid_t child_pid;

	// Set the child process
	child_pid = fork_and_run0(child_handler);

	// Close the unused write-end of the pipe
	sclose(pipefd[1]);

	while (1) {	// while till it received a SIGINT

		// Wait 1 seconds
		sleep(1);

		// Send the SIGUSR1 signal to the child process
		skill(child_pid, SIGUSR1);

		int degree;

		// Read the temperature from the pipe
		if(sread(pipefd[0], &degree, sizeof(int)) > 0) {
			colorOn(1, BLUE_TEXT);
			printf("[Controller] Donnée reçue : température = %d°C\n", degree);
			colorOff();
		}


		if (end_parent == 1) { // if SIGINT received
			break;
		}
	}

	colorOn(1, BLUE_TEXT);
	printf("[Controller] Fin du process\n");
	colorOff();

	// Close the read-end of the pipe
	sclose(pipefd[0]);

	
	return EXIT_SUCCESS;
}