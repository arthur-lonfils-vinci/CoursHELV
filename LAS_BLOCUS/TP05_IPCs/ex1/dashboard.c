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

#define SHM_KEY 12345  // Must match the key in radar.c

int main() {
    // Get the shared memory segment (created by radar.c)
    int shm_id = sshmget(SHM_KEY, sizeof(RadarData), 0666);
    
    // Attach to the shared memory segment
    RadarData *radar_data = (RadarData *)sshmat(shm_id);
    
    printf("Dashboard started. Reading distance data every 5 seconds for 1 minute.\n");
    
    // Read and display the distance every 5 seconds for 1 minute (12 iterations)
    for (int i = 0; i < 12; i++) {
        printf("Current distance: %d cm\n", radar_data->distance);
        
        if (radar_data->distance < 30) {
            printError("WARNING! Object very close!\n");
        } else if (radar_data->distance < 100) {
            printColor("CAUTION: Object nearby\n", "", YELLOW_TEXT);
        } else {
            printOk("Distance OK\n");
        }
        
        sleep(5);  // Wait for 5 seconds
    }
    
    // Detach from shared memory
    sshmdt(radar_data);
    
		// Remove the shared memory segment (optional, can be done by radar.c)
    sshmdelete(shm_id);
    
    printf("Dashboard stopped after 1 minute.\n");
    
    return 0;
}
