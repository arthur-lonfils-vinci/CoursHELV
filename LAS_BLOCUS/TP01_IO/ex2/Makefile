CFLAGS = -std=c17 -pedantic -Wall -Wextra -Werror -D_DEFAULT_SOURCE
CC = gcc

utils.h = ../utils_v1.h
utils.c = ../utils_v1.c

all: lireEtEcrire clean

utils.o: $(utils.c) $(utils.h)
	$(CC) $(CFLAGS) -c $(utils.c) -o utils.o

lireEtEcrire.o: lireEtEcrire.c $(utils.h)
	$(CC) $(CFLAGS) -c lireEtEcrire.c

lireEtEcrire: lireEtEcrire.o utils.o
	$(CC) $(CFLAGS) -o lireEtEcrire lireEtEcrire.o utils.o
	@echo "Executable 'lireEtEcrire' created successfully."
	@echo "You can now run the program with ./lireEtEcrire"

clean:
	rm -f lireEtEcrire.o utils_v1.o
	@echo "Cleaned up the build files."