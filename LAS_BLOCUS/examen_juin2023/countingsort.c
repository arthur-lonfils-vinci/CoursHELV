#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

#include "utils_v2.h"

#define TABSIZE 1000
#define KEY_SHM 321
#define KEY_SEM 230
#define PERM 0666

int pipefd[2];
int sem_id = -1;
int shm_id = -1;

void childHandler() {
	sclose(pipefd[1]);

	int val;

	shm_id = sshmget(KEY_SHM, sizeof(int[TABSIZE]), 0);
	sem_id = sem_get(KEY_SEM, 1);

	int *tab = sshmat(shm_id);

	while((sread(pipefd[0], &val, sizeof(int))) > 0) {
		sem_down0(sem_id);
		tab[val]++;
		sem_up0(sem_id);
	}

	sshmdt(tab);
	sclose(pipefd[0]);

	exit(EXIT_SUCCESS);
}

int main(int argc, char *argv[]) {

	if (argc < 4) {
		printf("Manque des trucs chef\n");
		exit(EXIT_FAILURE);
	}

	int nbFils = atoi(argv[1]);
	int n = atoi(argv[2]);

	shm_id = sshmget(KEY_SHM, sizeof(int[TABSIZE]), IPC_CREAT | PERM);

	spipe(pipefd);

	sem_id = sem_create(KEY_SEM, 1, PERM, 1);

	for (int i = 0; i < nbFils; i++) {
		fork_and_run0(childHandler);
	}

	sclose(pipefd[0]);

	for (int i = 0; i < n; i++) {
		int val = atoi(argv[i+3]);
		swrite(pipefd[1], &val, sizeof(val));
	}

	sclose(pipefd[1]);

	for (int i = 0; i < nbFils; i++)
	{
		swait(NULL);
	}
	

	int *tab;
	tab = sshmat(shm_id);

	sem_down0(sem_id);

	for (int i = 0; i < TABSIZE; i++) {
		for (int j = 0; j < tab[i]; j++) {
			printf("%d", i);
		}
	}
	printf("\n");

	sem_up0(sem_id);

	sshmdelete(shm_id);
	sem_delete(sem_id);

	return 0;
}