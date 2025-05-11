CFLAGS = -std=c17 -pedantic -Wall -Wextra -Werror -D_DEFAULT_SOURCE
CC = gcc

utils.h = ../utils_v1.h
utils.c = ../utils_v1.c

all: lire clean

utils.o: $(utils.c) $(utils.h)
	$(CC) $(CFLAGS) -c $(utils.c) -o utils.o

lire.o: lire.c $(utils.h)
	$(CC) $(CFLAGS) -c lire.c

lire: lire.o utils.o
	$(CC) $(CFLAGS) -o lire lire.o utils.o
	@echo "Executable 'lire' created successfully."
	@echo "You can now run the program with ./lire"

clean:
	rm -f lire.o utils_v1.o
	@echo "Cleaned up the build files."