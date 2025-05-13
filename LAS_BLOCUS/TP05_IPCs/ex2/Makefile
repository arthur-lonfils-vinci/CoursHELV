CFLAGS = -std=c17 -pedantic -Wall -Wextra -Werror -D_DEFAULT_SOURCE
CC = gcc

all: famille1 famille2

# famille1
famille1: famille1.o utils.o
	$(CC) $(CFLAGS) -o famille1 famille1.o utils.o

famille1.o: famille1.c
	$(CC) $(CFLAGS) -c famille1.c

# famille2
famille2: famille2.o utils.o
	$(CC) $(CFLAGS) -o famille2 famille2.o utils.o

famille2.o: famille2.c
	$(CC) $(CFLAGS) -c famille2.c

# utils
utils.o: utils.c
	$(CC) $(CFLAGS) -c utils.c

clean:
	rm -f famille1.o famille2.o utils.o famille1 famille2 && clear
