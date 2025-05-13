#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/types.h>
#include "utils.h"

// Structure to store the radar data in shared memory
typedef struct {
    int distance;
} RadarData;

#define SHM_KEY 12345  // Use the result of echo $RANDOM for a unique key

int main() {
    // Create or get shared memory segment
    int shm_id = sshmget(SHM_KEY, sizeof(RadarData), IPC_CREAT | 0666);
    
    // Attach to the shared memory segment
    RadarData *radar_data = (RadarData *)sshmat(shm_id);
    
    printf("Radar started. Writing distance data every 3 seconds for 1 minute.\n");
    
    // Initialize random distance
    radar_data->distance = randomIntBetween(10, 200);
    printf("Initial distance: %d cm\n", radar_data->distance);
    
    // Write random distances every 3 seconds for 1 minute (20 iterations)
    for (int i = 0; i < 20; i++) {
        sleep(3);  // Wait for 3 seconds
        
        // Generate a random distance between 10 and 200 cm
        radar_data->distance = randomIntBetween(10, 200);
        printf("New distance: %d cm\n", radar_data->distance);
    }
    
    // Detach from shared memory
    sshmdt(radar_data);
    
    printf("Radar stopped after 1 minute.\n");
    
    return 0;
}