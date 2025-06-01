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
    
		// TODO: Nettoyer les ressources (sémaphore et mémoire partagée)

}

void show_clients(struct clients *data) {
		printf("\n=== Liste de clients enregistrés ===\n");
    
		// TODO: Afficher la liste des clients enregistrés

}

void client_handler(void *sockfd_arg) {

    // TODO: Gérer un client (lire son nom, l'ajouter en mémoire partagée)
		
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Usage: %s <port>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

		int sockfd = ssocket();

    // TODO: Implémenter le serveur d'enregistrement


    while (1) {

			int new_connection = accept(sockfd, NULL, NULL);

			if (new_connection > 0) {

				// TODO

			}

			if (end == 1) {

				// TODO

			}
		}

		sclose(sockfd);
		printf("Server stopped\n");
    return 0;
}
