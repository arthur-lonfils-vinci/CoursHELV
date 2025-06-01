#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

#include "ipc_conf.h"
#include "utils_v2.h"

#define CROSSING_TIME  10

//*****************************************************************************//
// USAGE
//*****************************************************************************//

void checkUsage (int argc, char *argv[]) {
  if (argc != 2) {
    printf("Usage:\n");
    printf("       %s R   to turn switch to the right\n", argv[0]);
    printf("       %s L   to turn switch to the left\n", argv[0]);
    exit(EXIT_FAILURE);
  }
}

//*****************************************************************************//
// MAIN
//*****************************************************************************//

int main (int argc, char *argv[]) {
  checkUsage(argc, argv);
  
  //INIT SIGNAL
  sigset_t set1;
  ssigemptyset(&set1);
  ssigaddset(&set1,SIGUSR1);
  ssigprocmask(SIG_BLOCK, &set1, NULL);
  ssigaction(SIGUSR1, ehandler);

  //GET SHARED RESSOURCES
  int shm_id = sshmget(TRAIN_SHM_KEY, 2 * sizeof(pid_t), 0);
  int *mem = sshmat(shm_id);
  int sem_id = sem_get(TRAIN_SEM_KEY, 1);

  printf("[%s] WAIT UNTIL THE SWITCH IS FREE\n", getTime());
  
  //RESERVE SHM
  sem_down0(sem_id);
  mem[1] = getpid();
  int side = argv[1][0];
  if (side == 'L') {
    skill(mem[0], SIGUSR1);
  } else {
    skill(mem[0], SIGUSR2);
  }

  printf("[%s] WAIT UNTIL THE SWITCH IS ON THE CORRECT SIDE\n", getTime());
  sigset_t set;
  ssigfillset(&set);
  ssigdelset(&set, SIGUSR1);
  sigsuspend(&set);

  //CROSS THE SWICH
  printf("[%s] START CROSSING THE SWITCH\n", getTime());
  sleep(CROSSING_TIME);
  printf("[%s] END CROSSING THE SWITCH\n", getTime());

  //FREE SHM
  sshmdt(mem);
  sem_up0(sem_id);
}
