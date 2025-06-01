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
    printf("Usage: client <string>\n");
    printf("  <string>: A 10-character lowercase string\n");
    exit(EXIT_FAILURE);
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printUsage();
    }
    
    // Check if the input string is valid (10 lowercase characters)
    if (strlen(argv[1]) != 10) {
        printf("Error: The input string must be exactly 10 characters long.\n");
        printUsage();
    }
    
    for (int i = 0; i < 10; i++) {
        if (argv[1][i] < 'a' || argv[1][i] > 'z') {
            printf("Error: The input string must contain only lowercase characters.\n");
            printUsage();
        }
    }
    
    // Get the shared memory segment
    int shm_id = sshmget(SHM_KEY, sizeof(ContentData), 0);
    
    // Get the semaphores
    int sem_client_id = sem_get(SEM_CLIENT_KEY, 1);
    int sem_server_id = sem_get(SEM_SERVER_KEY, 1);
    
    // Attach to the shared memory segment
    ContentData *content_data = (ContentData *)sshmat(shm_id);
    
    // Wait for client semaphore (permission to write)
    sem_down0(sem_client_id);
    
    // Write the string to shared memory
    strncpy(content_data->content, argv[1], 10);
    content_data->content[10] = '\0';
    
    printf("Client: wrote '%s' to shared memory\n", content_data->content);
    
    // Signal the server that it can process the string
    sem_up0(sem_server_id);
    
    // Wait for the server to convert the string to uppercase
    sem_down0(sem_client_id);
    
    // Display the uppercase string
    printf("Client: received uppercase version: '%s'\n", content_data->content);
    
    // Signal server that client has read the string
    sem_up0(sem_server_id);

	// Permit the client to write again
	sem_up0(sem_client_id);
    
    // Detach from shared memory
    sshmdt(content_data);
    
    return 0;
}
