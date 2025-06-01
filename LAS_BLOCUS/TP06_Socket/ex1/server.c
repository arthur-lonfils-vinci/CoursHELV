#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/types.h>
#include <sys/socket.h>

#include "messages.h"
#include "utils.h"

#define MAX_PLAYERS 2
#define BACKLOG 5

typedef struct Player
{
  char pseudo[MAX_PSEUDO];
  int sockfd;
} Player;

volatile sig_atomic_t end = 0;

void endServerHandler(int sig)
{
  end = 1;
}

void terminate(Player *tabPlayers, int nbPlayers)
{
  printf("\nJoueurs inscrits : \n");
  for (int i = 0; i < nbPlayers; i++)
  {
    printf("  - %s inscrit\n", tabPlayers[i].pseudo);
  }
  exit(0);
}

/**
 * PRE:  serverPort: a valid port number
 * POST: on success, binds a socket to 0.0.0.0:serverPort and listens to it ;
 *       on failure, displays error cause and quits the program
 * RES: return socket file descriptor
 */
int initSocketServer(int serverPort)
{
  int sockfd = ssocket();

  sbind(serverPort, sockfd);

  slisten(sockfd, BACKLOG);

  return sockfd;
}

int main(int argc, char **argv)
{
  StructMessage msg;
  Player tabPlayers[MAX_PLAYERS];
  int nbPlayers = 0;

  ssigaction(SIGTERM, endServerHandler);
  ssigaction(SIGINT, endServerHandler);

  int sockfd = initSocketServer(SERVER_PORT);
  printf("Le serveur tourne sur le port : %i \n", SERVER_PORT);

  while (!end)
  {
    /* client trt */
    int newsockfd = saccept(sockfd);
    if (end)
    {
      terminate(tabPlayers, nbPlayers);
    }

    sread(newsockfd, &msg, sizeof(msg));
    if (end)
    {
      terminate(tabPlayers, nbPlayers);
    }

    printf("Inscription demandée par le joueur : %s\n", msg.messageText);
    if (nbPlayers < MAX_PLAYERS && strlen(msg.messageText) > 0)
    {
      msg.code = INSCRIPTION_OK;
      strcpy(tabPlayers[nbPlayers].pseudo, msg.messageText);
      tabPlayers[nbPlayers].sockfd = newsockfd;
      nbPlayers++;
    }
    else
    {
      msg.code = INSCRIPTION_KO;
    }

    nwrite(newsockfd, &msg, sizeof(msg));
    printf("Nb Inscriptions : %i\n", nbPlayers);
  }
  sclose(sockfd);
}
