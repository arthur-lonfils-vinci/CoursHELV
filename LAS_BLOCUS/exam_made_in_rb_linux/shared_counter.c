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

	// TODO

}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        fprintf(stderr, "Usage: %s <number_of_processes> <increments_per_process>\n", argv[0]);
        return 1;
    }

		// TODO
		
}
