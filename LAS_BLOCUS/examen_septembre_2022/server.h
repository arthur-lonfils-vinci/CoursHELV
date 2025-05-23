#define MAX_CLIENT 10
#define BACKLOG 5
#define KEY 1234
#define PERM 0666
#define MAX_SIZE 256

struct client
{
    int sockfd;
    char name[256];
} client;

struct clients
{
    struct client clients[MAX_CLIENT];
    int nbClients;
} clients;