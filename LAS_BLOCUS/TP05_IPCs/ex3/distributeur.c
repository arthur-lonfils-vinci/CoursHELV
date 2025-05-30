#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
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

int main() {
    // Get the shared memory segment
    int shm_id = sshmget(SHM_KEY, sizeof(TicketData), 0);
    
    // Get the semaphore
    int sem_id = sem_get(SEM_KEY, 1);
    
    // Attach to the shared memory segment
    TicketData *ticket_data = (TicketData *)sshmat(shm_id);
    
    // Lock the shared resource
    sem_down0(sem_id);
    
    // Increment the ticket number
    ticket_data->lastDistributed++;
    int ticketNumber = ticket_data->lastDistributed;
    
    // Unlock the shared resource
    sem_up0(sem_id);
    
    // Display the ticket number
    printf("Ticket numéro %d distribué\n", ticketNumber);
    
    // Detach from shared memory
    sshmdt(ticket_data);
    
    return 0;
}