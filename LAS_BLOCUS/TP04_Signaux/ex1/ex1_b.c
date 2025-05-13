#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>

#include "utils.h"

// Signal handler function for SIGUSR1
void sigusr1_handler() {
    // Using write instead of printf since printf is not signal-safe
    const char *message = "signal SIGUSR1 reçu !\n";
    write(STDOUT_FILENO, message, 21); // 21 is the length of the message
    exit(0); // Exit the process
}

int main() {
    // Setup signal handler before forking
    ssigaction(SIGUSR1, sigusr1_handler);
    
    int child = sfork();

    if (child == 0) {
        // Child process
        printf("Child process started with PID: %d\n", getpid());
        // Infinite loop with sleep
        while (1) {
            sleep(1);
        }
    } else {
        // Parent process
        printf("Parent process. Child PID: %d\n", child);
        // Give the child some time to start properly
        sleep(1);
        // Send SIGUSR1 to the child
        printf("Sending SIGUSR1 to child...\n");
        skill(child, SIGUSR1);
        // Wait for child to terminate
        swait(NULL);
        printf("Child has terminated. Parent exiting.\n");
    }

    return 0;
}