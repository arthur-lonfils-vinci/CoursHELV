CFLAGS = -std=c17 -pedantic -Wall -Wextra -Werror -D_DEFAULT_SOURCE
CC = gcc


all: ex1

ex1: ex1.o utils.o
	$(CC) $(CFLAGS) -o ex1 ex1.o utils.o

ex1.o: ex1.c
	$(CC) $(CFLAGS) -c ex1.c

utils.o: utils.c
	$(CC) $(CFLAGS) -c utils.c

clean:
	rm -f ex1.o ex1 utils.o
	