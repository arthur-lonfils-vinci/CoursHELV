CFLAGS = -std=c17 -pedantic -Wall -Wextra -Werror -D_DEFAULT_SOURCE
CC = gcc


all: clean random

random: random.o utils.o
	$(CC) $(CFLAGS) -o random random.o utils.o

random.o: random.c
	$(CC) $(CFLAGS) -c random.c

utils.o: utils.c
	$(CC) $(CFLAGS) -c utils.c

clean:
	rm -f random.o random utils.o
	