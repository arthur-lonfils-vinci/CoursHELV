#include <stdio.h>
#include <stdbool.h>
#include <unistd.h>
#include <sys/types.h>

#include "ipc_conf.h"
#include "utils_v2.h"

#define SLEEPING_TIME  5

volatile int end = false;
volatile int signum;

void last_handler(int num) {
  end = true;
}

void cmd_handler(int num) {
  signum = num;
}


//******************************************************************************//
// MAIN FUNCTION
//******************************************************************************//

int main (int argc, char *argv[]) {
  //GET SHARED RESSOURCES
  int shm_id = sshmget(TRAIN_SHM_KEY, 2 * sizeof(pid_t), 0);
  int *mem = sshmat(shm_id);
  int sem_id = sem_get(TRAIN_SEM_KEY, 1);

  printf("[%s] SWITCH UP ON THE LEFT SIDE\n", getTime());

  int side = SIGUSR1;

  //INIT SIGNALS
  sigset_t set1;
  ssigemptyset(&set1);
  ssigaddset(&set1,SIGINT);
  ssigaddset(&set1,SIGUSR1);
  ssigaddset(&set1,SIGUSR2);
  ssigprocmask(SIG_BLOCK, &set1, NULL);
  ssigaction(SIGINT, last_handler);
  ssigaction(SIGUSR1, cmd_handler);
  ssigaction(SIGUSR2, cmd_handler);

  //INIT SWITCH ID
  mem[0] = getpid();
  sem_up0(sem_id);
  
  //PREPARE TO ACCEPT SIGINT and SIGUSR1
  sigset_t set;
  ssigfillset(&set);
  ssigdelset(&set, SIGINT);
  ssigdelset(&set, SIGUSR1);
  ssigdelset(&set, SIGUSR2);
    
  sigsuspend(&set);
  while(!end) {
    if (signum == SIGUSR1) {
      printf("[%s] A TRAIN WANTS TO GO LEFT\n", getTime());
    } else {
      printf("[%s] A TRAIN WANTS TO GO RIGHT\n", getTime());
    }
    if (side == SIGUSR1) {
      printf("[%s] SWITCH UP ON THE LEFT SIDE\n", getTime());
    } else {
      printf("[%s] SWITCH UP ON THE RIGHT SIDE\n", getTime());
    }
    if (side != signum) {
      printf("[%s] MODIFYING SWITCH SIDE\n", getTime());
      sleep(SLEEPING_TIME);
      side = signum;
    } 
    
    printf("[%s] SWITCH OK\n", getTime());
    
    skill(mem[1], SIGUSR1);
    sigsuspend(&set);
  }

  //FREE RESSOURCES
  sshmdt(mem);
  sem_down0(sem_id);
  printf("[%s] SWITCH DOWN\n", getTime());
}