#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#include "utils_v2.h"

#define KEY_SHM 123
#define KEY_SEM 456
#define PERM 0666

int pipefd[2];
int shm_id = -1;
int sem_id = -1;

void child_process(void* increments_ptr) {
		sclose(pipefd[0]);

		int increments = *((int*) increments_ptr);
		pid_t pid = getpid();

		shm_id = sshmget(KEY_SHM, sizeof(int), 0);
		int *counter = sshmat(shm_id);

		sem_id = sem_get(KEY_SEM, 1);

		for (int i = 0; i < (int) increments; i++) {
			sem_down0(sem_id);
			(*counter)++;
			printf("Process %d: counter = %d\n", pid, (int) *counter);
			sem_up0(sem_id);
		}

		swrite(pipefd[1], &pid, sizeof(pid));

		sclose(pipefd[1]);

		exit(EXIT_SUCCESS);
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        fprintf(stderr, "Usage: %s <number_of_processes> <increments_per_process>\n", argv[0]);
        return 1;
    }

		int nb_fils = atoi(argv[1]);
		int counter_fils = atoi(argv[2]);

		shm_id = sshmget(KEY_SHM, sizeof(int), IPC_CREAT | PERM);

		int *counter = sshmat(shm_id);

		(*counter) = 0;

		sem_id = sem_create(KEY_SEM, 1, PERM, 1);

		spipe(pipefd);

		for (int i = 0; i < nb_fils; i++) {
			fork_and_run1(child_process, &counter_fils);
		}

		sclose(pipefd[1]);

		for (int i = 0; i < nb_fils; i++) {
			pid_t child_pid;
			sread(pipefd[0], &child_pid, sizeof(pid_t));
			printf("Process %d finished\n", child_pid);
			wait(NULL);
		}

		sem_down0(sem_id);
		printf("Final counter value: %d\n", (int) *counter);
		printf("Expected: %d - %s\n", (int) *counter, (int) *counter == (nb_fils * counter_fils) ? "CORRECT" : "FALSE");
    sem_up0(sem_id);

		sshmdelete(shm_id);
		sem_delete(sem_id);
		sclose(pipefd[0]);

    return 0;
}
