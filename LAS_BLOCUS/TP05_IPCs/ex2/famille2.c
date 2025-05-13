#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "utils.h"

#define SEM_KEY 12345

void child_process(void *sem_id_ptr) {
    int sem_id = *((int *)sem_id_ptr);
    
    sem_down0(sem_id);
    
    // Critical section
    for (int i = 0; i < 3; i++) {
        printf("je suis le fils %d\n", getpid());
        sleep(1);
    }
    
    sem_up0(sem_id);
    exit(0);
}

int main() {
    int sem_id = sem_create(SEM_KEY, 1, 0666, 1);
    
    fork_and_run1(child_process, &sem_id);
    fork_and_run1(child_process, &sem_id);
    
    // Wait for both children to finish
    swait(NULL);
    swait(NULL);
    
    sem_delete(sem_id);
    EXIT_SUCCESS;
}
