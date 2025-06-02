#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <fcntl.h>

// Utils header
#include "utils_v3.h"

// Defined variables
#define KEY_SEM 2131
#define KEY_SHM 1213
#define NB_FILES 2
#define PERM 0666

int sem_id = -1;
int shm_id = -1;

// Function to check the usage of the script
void checkUsage(int argc, char* argv[]) {

	if (argc < 3) {
		printError("[Error Usage] ./imptot <fichierA> <fichierB>\n");
		exit(EXIT_FAILURE);
	}

}


// Function to handle the child process
void child_handler(void* given_file_name) {

	// Cast the param into a pointer to a char
	char* file_name = (char*) given_file_name;

	// "Open" the file to read it
	int filefd = sopen(file_name, O_RDONLY, 0);

	// Link variable to the shm
	int *value = sshmat(shm_id);

	// Read each character of the file
	int file_value;

	while (sread(filefd, &file_value, sizeof(unsigned short int)) > 0) {
		sem_down0(sem_id);	// Ask the access to the shared memory (sem value:  1 -> 0)
		if ((file_value % 2) != 0) {
			*value += file_value;
		}
		sem_up0(sem_id);	// Free the access to the shared memory (sem value: 0 -> 1, except if queue)
	}

	// Close and detach the variables
	sclose(filefd);
	sshmdt(value);

	exit(EXIT_SUCCESS);

}


// Function to clean up the IPCs
void cleanup_ressources() {

	sshmdelete(shm_id);
	sem_delete(sem_id);

}


// Main process
int main(int argc, char* argv[]) {

	// Check the correct usage
	checkUsage(argc, argv);

	// Initiate the IPCs
	shm_id = sshmget(KEY_SHM, sizeof(unsigned short int), IPC_CREAT | PERM);

	int *value = sshmat(shm_id);

	*value = 0;

	sem_id = sem_create(KEY_SEM, 1, PERM, 1);

	// Create the children (same number as given files) with the file's name in param
	for (int i = 1; i <= NB_FILES; i++) {
		fork_and_run1(child_handler, argv[i]);
	}

	// Wait each children to finish
	for (int i = 0; i < NB_FILES; i++) {
		swait(NULL);
	}

	// Print results
	colorOn(1, GREEN_TEXT);
	printf("La somme des deux fichiers %s et %s = %d\n", argv[1], argv[2], *value);
	colorOff();

	// Clean up IPCs
	sshmdt(value);
	cleanup_ressources();

	return EXIT_SUCCESS;
}