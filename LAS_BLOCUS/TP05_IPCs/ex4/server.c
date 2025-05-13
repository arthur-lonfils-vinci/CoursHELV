#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <ctype.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <poll.h>
#include "utils.h"

#define SHM_KEY 12346
#define SEM_CLIENT_KEY 54321
#define SEM_SERVER_KEY 54322

// Structure to store the data in shared memory
typedef struct {
    char content[11]; // 10 characters + null terminator
} ContentData;

// Helper function for colored output
void printColored(const char* message, int color) {
    colorOn(1, color);
    printf("%s", message);
    colorOff();
}

// Function to handle the server operation in a loop
void processRequests() {
    // Get the shared memory segment
    int shm_id = sshmget(SHM_KEY, sizeof(ContentData), 0);
    
    // Get the semaphores
    int sem_client_id = sem_get(SEM_CLIENT_KEY, 1);
    int sem_server_id = sem_get(SEM_SERVER_KEY, 1);
    
    // Attach to the shared memory segment
    ContentData *content_data = (ContentData *)sshmat(shm_id);
    
    while (1) {
        printColored("\nServer: waiting for client to write to shared memory...\n", CYAN_TEXT);
        
        // Wait for the client to write the string
        sem_down0(sem_server_id);
        
        printColored("Server: received string '", YELLOW_TEXT);
        printColored(content_data->content, WHITE_TEXT);
        printColored("', converting to uppercase...\n", YELLOW_TEXT);
        
        // Convert the string to uppercase
        for (int i = 0; i < 10 && content_data->content[i] != '\0'; i++) {
            content_data->content[i] = toupper(content_data->content[i]);
        }
        
        printColored("Server: converted to uppercase: '", GREEN_TEXT);
        printColored(content_data->content, WHITE_TEXT);
        printColored("'\n", GREEN_TEXT);
        
        // Signal the client that the string has been converted
        sem_up0(sem_client_id);
        
        // Wait for the client to read the string
        sem_down0(sem_server_id);
        
        // Check for exit command without requiring Enter press (using poll)
        printf("\n");
        printColored("Press 'q' to quit or wait for the next client request...\n", PURPLE_TEXT);
        
        // Non-blocking check for user input
        struct pollfd fds;
        fds.fd = STDIN_FILENO;
        fds.events = POLLIN;
        
        // Check if there's input available (timeout set to 10 seconds)
        if (poll(&fds, 1, 3000) > 0) {
            char input[10];
            if (fgets(input, sizeof(input), stdin) != NULL && (input[0] == 'q' || input[0] == 'Q')) {
                printColored("\nExiting server...\n", RED_TEXT);
                printColored("Do you want to clean up resources (shared memory and semaphores)? (y/n): ", RED_TEXT);
                
                if (fgets(input, sizeof(input), stdin) != NULL && (input[0] == 'y' || input[0] == 'Y')) {
                    printColored("Cleaning up resources...\n", YELLOW_TEXT);
                    
                    // Detach from shared memory
                    sshmdt(content_data);
                    
                    // Execute admin -d to clean up
                    if (fork() == 0) {
                        execl("./admin", "admin", "-d", NULL);
                        exit(EXIT_FAILURE); // Only reached if execl fails
                    } else {
                        swait(NULL); // Wait for child to finish
                    }
                    
                    printColored("Resources cleaned up successfully!\n", GREEN_TEXT);
                } else {
                    // Just detach from shared memory
                    sshmdt(content_data);
                    
                    printColored("Resources preserved. Remember to run './admin -d' to clean up later.\n", YELLOW_TEXT);
                }
                
                break;
            }
        }
        
        // No need for delay - will immediately wait for next client request
    }
}

int main() {
    // Print welcome message
    printColored("==========================================\n", BLUE_TEXT);
    printColored("       SERVER UPPERCASE CONVERTER         \n", BLUE_TEXT);
    printColored("==========================================\n\n", BLUE_TEXT);
    
    printColored("Server started. Press 'q' to exit.\n\n", GREEN_TEXT);
    
    processRequests();
    
    return 0;
}
