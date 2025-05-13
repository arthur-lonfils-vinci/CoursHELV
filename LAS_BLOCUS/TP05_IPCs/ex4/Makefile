CFLAGS = -std=c17 -pedantic -Wall -Wextra -Werror -D_DEFAULT_SOURCE
CC = gcc

all: client server admin

# client
client: client.o utils.o
	$(CC) $(CFLAGS) -o client client.o utils.o

client.o: client.c
	$(CC) $(CFLAGS) -c client.c

# server
server: server.o utils.o
	$(CC) $(CFLAGS) -o server server.o utils.o

server.o: server.c
	$(CC) $(CFLAGS) -c server.c

# admin
admin: admin.o utils.o
	$(CC) $(CFLAGS) -o admin admin.o utils.o

admin.o: admin.c
	$(CC) $(CFLAGS) -c admin.c

# utils
utils.o: utils.c
	$(CC) $(CFLAGS) -c utils.c

clean:
	rm -f *.o client server admin
