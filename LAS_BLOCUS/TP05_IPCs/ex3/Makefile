CFLAGS = -std=c17 -pedantic -Wall -Wextra -Werror -D_DEFAULT_SOURCE
CC = gcc

all: distributeur ecran admin

# distributeur
distributeur: distributeur.o utils.o
	$(CC) $(CFLAGS) -o distributeur distributeur.o utils.o

distributeur.o: distributeur.c
	$(CC) $(CFLAGS) -c distributeur.c

# ecran
ecran: ecran.o utils.o
	$(CC) $(CFLAGS) -o ecran ecran.o utils.o

ecran.o: ecran.c
	$(CC) $(CFLAGS) -c ecran.c

# admin
admin: admin.o utils.o
	$(CC) $(CFLAGS) -o admin admin.o utils.o

admin.o: admin.c
	$(CC) $(CFLAGS) -c admin.c

# utils
utils.o: utils.c
	$(CC) $(CFLAGS) -c utils.c

clean:
	rm -f *.o distributeur ecran admin
