#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/types.h>
#include "utils.h"

#define SHM_KEY 12346
#define SEM_CLIENT_KEY 54321
#define SEM_SERVER_KEY 54322

// Structure to store the data in shared memory
typedef struct {
    char content[11]; // 10 characters + null terminator
} ContentData;

void printUsage() {
    printf("Usage: admin -c|-d\n");
    printf("  -c: Create and initialize shared memory and semaphores\n");
    printf("  -d: Destroy shared memory and semaphores\n");
    exit(EXIT_FAILURE);
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printUsage();
    }

    if (strcmp(argv[1], "-c") == 0) {
        // Create shared memory segment with correct size
        int shm_id = sshmget(SHM_KEY, sizeof(ContentData), IPC_CREAT | 0666);
        
        // Create semaphores:
        // - Client semaphore: Initially 1 (client can write first)
        // - Server semaphore: Initially 0 (server must wait for client)
        sem_create(SEM_CLIENT_KEY, 1, 0666, 1);
        sem_create(SEM_SERVER_KEY, 1, 0666, 0);
        
        // Attach to the shared memory segment
        ContentData *content_data = (ContentData *)sshmat(shm_id);
        
        // Initialize shared memory content
        memset(content_data->content, 0, sizeof(content_data->content));
        
        // Detach from shared memory
        sshmdt(content_data);
        
        printf("Shared memory and semaphores created and initialized\n");
        
    } else if (strcmp(argv[1], "-d") == 0) {
        // Get the shared memory segment
        int shm_id = sshmget(SHM_KEY, sizeof(ContentData), 0);
        
        // Get the semaphores
        int sem_client_id = sem_get(SEM_CLIENT_KEY, 1);
        int sem_server_id = sem_get(SEM_SERVER_KEY, 1);
        
        // Delete the shared memory segment
        sshmdelete(shm_id);
        
        // Delete the semaphores
        sem_delete(sem_client_id);
        sem_delete(sem_server_id);
        
        printf("Shared memory and semaphores destroyed\n");
        
    } else {
        printUsage();
    }
    
    return 0;
}
