#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>
#include <signal.h>
#include "../ex1/utils.h" // Make sure the path to utils.h is correct

// Signal handler function
void signal_handler(int signum)
{
	printf(" Signal '%s' reçu ", strsignal(signum));
	fflush(stdout); // Make sure the message is displayed immediately
}

int main()
{
	pid_t pid = getpid();
	printf("[%d] Hello, I am TIMER! ;)\n", pid);

	// Set up signal handlers for signals 1-32
	for (int i = 1; i <= 32; i++)
	{
		// Skip signals that cannot be caught or ignored
		if (i == SIGKILL || i == SIGSTOP || i == 0)
		{
			printf("Signal %d (%s) non capture\n", i, strsignal(i));
			continue;
		}

		// Set up a handler for this signal using the utility function
		if (sigaction(i, NULL, NULL) < 0)
		{
			printf("Signal %d (%s) non capture\n", i, strsignal(i));
		}
		else
		{
			ssigaction(i, signal_handler);
		}
	}

	// Main loop: print a dot, then sleep for 1 second
	while (1)
	{
		swrite(STDOUT_FILENO, ".", 1);

		sleep(1);
	}

	return 0; // This will never be reached
}