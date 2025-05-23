#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

#include "utils_v2.h"

#define TABSIZE 1000
#define KEY_SHM 324
#define KEY_SEM 865
#define PERM 0666

int pipefd[2];

void childHandler() {
    sclose(pipefd[1]);
    int shm_id = sshmget(KEY_SHM, TABSIZE * sizeof(int), 0);
    int sem_id = sem_get(KEY_SEM, 1);
    int *tab = sshmat(shm_id);
    int value;
    int res = sread(pipefd[0], &value, sizeof(int));
    while(res == sizeof(int)) {
      sem_down0(sem_id);
      tab[value] += 1;
      printf("%d %d\n", value, tab[value]);
      sem_up0(sem_id);
      res = sread(pipefd[0], &value, sizeof(int));
    }
    sclose(pipefd[0]);
    sshmdt(tab);
}

int main(int argc, char *argv[]) {
    //1. Memoire partagé
    int n = atoi(argv[2]);
    int shmid = sshmget(KEY_SHM, TABSIZE * sizeof(int), IPC_CREAT | PERM);
    int semid = sem_create(KEY_SEM, 1, PERM, 1);

    //2.Pipe
    spipe(pipefd);

    //3.Children
    int nbrChildren = atoi(argv[1]);
    for (int i = 0; i != nbrChildren; i++) {
        fork_and_run0(childHandler);
    }
    
    sclose(pipefd[0]);  
    for (int i = 3; i < argc; i++) {
        //Creer fils et ecrire sur pipe
        int value = atoi(argv[i]);
        swrite(pipefd[1], &value, sizeof(int));
    }
    sclose(pipefd[1]);

    //4.wait
    for (int i = 0; i != nbrChildren; i++) {
        swaitpid(-1, NULL, 0);
    }

    int shm_id = sshmget(KEY_SHM, TABSIZE * sizeof(int), 0);
    int sem_id = sem_get(KEY_SEM, 1);
    int *tab = sshmat(shm_id);
    sem_down0(sem_id);

    for (int i = 0; i != TABSIZE; i++) {
      for (int j = 0; j != tab[i]; j++) {
          printf("%d ", i);
      }
    }
    
    sem_up0(sem_id);
    sshmdt(tab);
    sshmdelete(shmid);
    sem_delete(semid);
}