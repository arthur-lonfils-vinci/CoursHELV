CFLAGS = -std=c17 -pedantic -Wall -Wextra -Werror -D_DEFAULT_SOURCE
CC = gcc


all: ex2

ex2: ex2.o utils.o
	$(CC) $(CFLAGS) -o ex2 ex2.o utils.o

ex2.o: ex2.c
	$(CC) $(CFLAGS) -c ex2.c

utils.o: utils.c
	$(CC) $(CFLAGS) -c utils.c

clean:
	rm -f ex2.o ex2 utils.o
	