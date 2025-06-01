#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/socket.h>
#include "utils_v2.h"

#define MAX_NAME_LEN 50

int main(int argc, char *argv[]) {
    if (argc != 4) {
        printf("Usage: %s <server_ip> <port> <name>\n", argv[0]);
        printf("Example: %s 127.0.0.1 8080 Alice\n", argv[0]);
        exit(EXIT_FAILURE);
    }
    
    char *server_ip = argv[1];
    int port = atoi(argv[2]);
    char *name = argv[3];
    
    // Vérifier la longueur du nom
    if (strlen(name) >= MAX_NAME_LEN) {
        printf("Erreur: Le nom ne doit pas dépasser %d caractères\n", MAX_NAME_LEN - 1);
        exit(EXIT_FAILURE);
    }
    
    printf("Connexion au serveur %s:%d avec le nom '%s'\n", server_ip, port, name);
    
    // Créer le socket
    int sockfd = ssocket();
    
    // Se connecter au serveur
    sconnect(server_ip, port, sockfd);
    
    // Envoyer le nom au serveur
    char message[MAX_NAME_LEN];
    snprintf(message, sizeof(message), "%s\n", name);
    ssize_t sent = write(sockfd, message, strlen(message));
    
    if (sent > 0) {
        printf("Nom envoyé avec succès au serveur\n");
    } else {
        printf("Erreur lors de l'envoi du nom\n");
    }
    
    // Fermer la connexion
    close(sockfd);
    printf("Connexion fermée\n");
    
    return 0;
}
