#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>

#include "ipc_conf.h"
#include "utils_v2.h"

//*****************************************************************************//
// USAGE
//*****************************************************************************//

void checkUsage (int argc, char *argv[]) {
  if (argc != 2 || (!strcmp(argv[1],"-c") && !strcmp(argv[1],"-d"))) {
    printf("Usage:\n");
    printf("       %s -c   to create IPCs\n", argv[0]);
    printf("       %s -d   to destroy IPCs\n", argv[0]);
    exit(EXIT_FAILURE);
  }
}

//******************************************************************************//
// MAIN FUNCTION
//******************************************************************************//

int main (int argc, char *argv[]) {
  checkUsage(argc,argv);
  
  if (!strcmp(argv[1],"-c")) {
    // IPC creation and initialization
    int shm_id = shmget(TRAIN_SHM_KEY, 2 * sizeof(pid_t), IPC_CREAT | IPC_EXCL | PERM);
    checkNeg(shm_id, "IPCs already created");
  
    sem_create(TRAIN_SEM_KEY, 1, PERM, 0);
    
    printf("IPCs created.\n");
  } else {  // argv[1] = "-d"
    // IPC destruction
    printf("Destroying IPCs...\n");
    int shm_id = shmget(TRAIN_SHM_KEY, 2 * sizeof(pid_t), 0);
    checkNeg(shm_id, "IPCs not existing");

    sshmdelete(shm_id);
  
    int sem_id = sem_get(TRAIN_SEM_KEY, 1);
    sem_delete(sem_id);

    printf("IPCs freed.\n");
  }
  
  exit(EXIT_SUCCESS);
}
