CFLAGS = -std=c17 -pedantic -Wall -Wextra -Werror -D_DEFAULT_SOURCE
CC = gcc


all: ex1_a ex1_b ex1_c

# ex1_a
ex1_a: ex1_a.o utils.o
	$(CC) $(CFLAGS) -o ex1_a ex1_a.o utils.o

ex1_a.o: ex1_a.c
	$(CC) $(CFLAGS) -c ex1_a.c

# ex1_b
ex1_b: ex1_b.o utils.o
	$(CC) $(CFLAGS) -o ex1_b ex1_b.o utils.o

ex1_b.o: ex1_b.c
	$(CC) $(CFLAGS) -c ex1_b.c

# ex1_c
ex1_c: ex1_c.o utils.o
	$(CC) $(CFLAGS) -o ex1_c ex1_c.o utils.o

ex1_c.o: ex1_c.c
	$(CC) $(CFLAGS) -c ex1_c.c

# utils
utils.o: utils.c
	$(CC) $(CFLAGS) -c utils.c

clean:
	rm -f ex1_a.o ex1_a ex1_b.o ex1_b ex1_c.o ex1_c utils.o && clear
	