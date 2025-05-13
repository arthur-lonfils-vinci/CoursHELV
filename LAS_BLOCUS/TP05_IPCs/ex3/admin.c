#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/types.h>
#include "utils.h"

#define SHM_KEY 12345
#define SEM_KEY 54321

// Structure to store the ticket numbers in shared memory
typedef struct {
    int lastDistributed;  // Last ticket number given out
    int lastCalled;       // Last ticket number called to the counter
} TicketData;

void printUsage() {
    printf("Usage: admin1 -c|-d\n");
    printf("  -c: Create and initialize shared memory and semaphore\n");
    printf("  -d: Destroy shared memory and semaphore\n");
    exit(EXIT_FAILURE);
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printUsage();
    }

    if (strcmp(argv[1], "-c") == 0) {
        // Create shared memory segment
        int shm_id = sshmget(SHM_KEY, sizeof(TicketData), IPC_CREAT | 0666);
        
        // Create semaphore (binary semaphore with initial value 1)
        sem_create(SEM_KEY, 1, 0666, 1);  // Removed unused variable
        
        // Attach to the shared memory segment
        TicketData *ticket_data = (TicketData *)sshmat(shm_id);
        
        // Initialize the ticket data
        ticket_data->lastDistributed = 0;
        ticket_data->lastCalled = 0;
        
        // Detach from shared memory
        sshmdt(ticket_data);
        
        printf("Shared memory and semaphore created and initialized\n");
        
    } else if (strcmp(argv[1], "-d") == 0) {
        // Get the shared memory segment
        int shm_id = sshmget(SHM_KEY, sizeof(TicketData), 0);
        
        // Get the semaphore
        int sem_id = sem_get(SEM_KEY, 1);
        
        // Delete the shared memory segment
        sshmdelete(shm_id);
        
        // Delete the semaphore
        sem_delete(sem_id);
        
        printf("Shared memory and semaphore destroyed\n");
        
    } else {
        printUsage();
    }
    
    return 0;
}
