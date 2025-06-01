#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>
#include <sys/socket.h>
#include "utils_v2.h"

#define MAX_CLIENTS 10
#define MAX_NAME_LEN 50
#define KEY_SHM 1244
#define KEY_SEM 3213
#define PERM 0666
#define BACKLOG 5

struct client {
    char name[MAX_NAME_LEN];
    int sockfd;
} client;

struct clients {
    struct client clients[MAX_CLIENTS];
    int nb_clients;
} clients;

volatile sig_atomic_t end = 0;
int shm_id = -1;
int sem_id = -1;

void sigint_handler() {
		end = 1;
}

void cleanup_resources() {
		printf("Nettoyage des ressources...\n");
		sshmdelete(shm_id);
		sem_delete(sem_id);
}

void show_clients(struct clients *data) {
		printf("\n=== Liste de clients enregistrés ===\n");
		printf("Nombre de clients : %d\n", data->nb_clients);
		for (int i = 0; i < data->nb_clients; i++) {
			printf("Client %d : %s (socket %d)\n", i, data->clients[i].name, data->clients[i].sockfd);
		}
}

void client_handler(void *sockfd_arg) {
		int sockfd = *((int*) sockfd_arg);

		char name[MAX_NAME_LEN];
		
		ssize_t bytes_read = sread(sockfd, name, sizeof(name));
		name[bytes_read-1] = '\0';

		struct clients *data;
		data = sshmat(shm_id);

		sem_down0(sem_id);

		data->nb_clients++;
		strcpy(data->clients[data->nb_clients].name, name);
		data->clients[data->nb_clients].sockfd = sockfd;

		printf("Client %s enregistré avec le socket %d\n", name, sockfd);

		sem_up0(sem_id);
		sshmdt(data);

		exit(EXIT_SUCCESS);
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Usage: %s <port>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

		ssigaction(SIGINT, sigint_handler);

		int port = atoi(argv[1]);
		int nbClients = 0;

		struct clients *ptrclients;

		shm_id = sshmget(KEY_SHM, sizeof(clients), IPC_CREAT | PERM);
		ptrclients = sshmat(shm_id);

		ptrclients->nb_clients = nbClients;

		sem_id = sem_create(KEY_SEM, 1, PERM, 1);

		int sockfd = ssocket();

		sbind(port, sockfd);

		slisten(sockfd, BACKLOG);

    while (1) {

			int new_connection = accept(sockfd, NULL, NULL);

			if (new_connection > 0) {
				fork_and_run1(client_handler, &new_connection);
				nbClients++;
			}

			if (end == 1 || nbClients == MAX_CLIENTS+1) {
				show_clients(ptrclients);
				cleanup_resources();
				break;
			}
		}

		sclose(sockfd);
		printf("Server stopped\n");
    return 0;
}
