CFLAGS = -std=c17 -pedantic -Wall -Wextra -Werror -D_DEFAULT_SOURCE
CC = gcc


all: ex3

ex3: ex3.o utils.o
	$(CC) $(CFLAGS) -o ex3 ex3.o utils.o

ex3.o: ex3.c
	$(CC) $(CFLAGS) -c ex3.c

utils.o: utils.c
	$(CC) $(CFLAGS) -c utils.c

clean:
	rm -f ex3.o ex3 utils.o
	