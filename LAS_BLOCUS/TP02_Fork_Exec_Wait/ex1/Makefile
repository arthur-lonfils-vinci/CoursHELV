CFLAGS = -std=c17 -pedantic -Wall -Wextra -Werror -D_DEFAULT_SOURCE
CC = gcc


all: ex1_a ex1_b

ex1_a: ex1_a.o
	$(CC) $(CFLAGS) -o ex1_a ex1_a.o

ex1_a.o: ex1_a.c
	$(CC) $(CFLAGS) -c ex1_a.c

ex1_b: ex1_b.o
	$(CC) $(CFLAGS) -o ex1_b ex1_b.o

ex1_b.o: ex1_b.c
	$(CC) $(CFLAGS) -c ex1_b.c

clean:
	rm -f ex1_a.o ex1_a ex1_b.o ex1_b
	