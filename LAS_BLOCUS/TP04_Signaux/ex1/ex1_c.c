#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>

#include "utils.h"

// Global counter for signal reception in child process
volatile int signal_count = 0;
// Global counter for signals sent by parent
volatile int signals_sent = 0;
// Global flag to control parent's signal sending loop
volatile int child_alive = 1;

// Signal handler function for SIGUSR1 in child process
void sigusr1_handler()
{
	signal_count++;
	// Using write instead of printf since printf is not signal-safe
	const char *message = "signal SIGUSR1 reçu !\n";
	write(STDOUT_FILENO, message, 23); // 21 is the length of the message

	if (signal_count >= 7)
	{
		const char *end_message = "Fin du fils. J'ai utilisé mes 7 vies...\n";
		write(STDOUT_FILENO, end_message, 42); // 42 is the length of the message
		exit(0);															 // Exit the child process
	}
}

// Signal handler for SIGCHLD in parent process
void sigchld_handler()
{
	// Set flag to stop sending signals
	child_alive = 0;

	// Wait for child to avoid zombie
	swait(NULL);

	// Display number of signals sent
	char message[100];
	int len = sprintf(message, "Fin du père. %d SIGUSR1 envoyés.\n", signals_sent);
	write(STDOUT_FILENO, message, len);

	exit(0); // Exit the parent process
}

int main()
{
	// Setup SIGUSR1 handler (will be inherited by child process)
	ssigaction(SIGUSR1, sigusr1_handler);

	int child = sfork();

	if (child == 0)
	{
		// Child process - block until it receives 7 SIGUSR1 signals
		while (1)
		{
			pause(); // Sleep until signal is received
		}
	}
	else
	{
		// Parent process - setup SIGCHLD handler
		ssigaction(SIGCHLD, sigchld_handler);

		// Keep sending SIGUSR1 to child
		while (child_alive)
		{
			skill(child, SIGUSR1);
			signals_sent++;

			// Small delay to prevent flooding the system with signals
			// This also increases the chance that some signals will be "lost"
			// which demonstrates signal coalescing
			usleep(1000); // 1 millisecond delay
		}
	}

	return 0; // This line should not be reached
}