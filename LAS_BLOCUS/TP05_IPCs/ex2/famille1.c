#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "utils.h"

/**
 * Function executed by each child process
 * Displays the message 3 times with 1 second delay between each
 */
void child_process() {
    for (int i = 0; i < 3; i++) {
        printf("je suis le fils %d\n", getpid());
        sleep(1);
    }
    exit(EXIT_SUCCESS);
}

int main() {
    // Create first child
    pid_t pid1 = fork_and_run0(child_process);
    
    // Create second child
    pid_t pid2 = fork_and_run0(child_process);
    
    printf("Parent process created two children: %d and %d\n", pid1, pid2);
    
    // Wait for both children to finish
    swait(NULL);
    swait(NULL);
    
    printf("Both children have completed\n");
    
    return EXIT_SUCCESS;
}