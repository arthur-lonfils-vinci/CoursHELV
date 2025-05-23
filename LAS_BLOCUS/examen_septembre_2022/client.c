#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <sys/types.h>

#include "utils_v1.h"

#define LOCAL_HOST "127.0.0.1"
#define SERVER_PORT 9090
#define MAX_SIZE 256

int main(int argc, char *arg[])
{
    int sockfd = ssocket();

    sconnect(LOCAL_HOST, SERVER_PORT, sockfd);

    char name[MAX_SIZE] = "test";
    swrite(sockfd, name, MAX_SIZE);
    sclose(sockfd);

    return 0;
}