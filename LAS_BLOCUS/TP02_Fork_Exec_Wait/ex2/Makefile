CFLAGS = -std=c17 -pedantic -Wall -Wextra -Werror -D_DEFAULT_SOURCE
CC = gcc


all: ex2_a

# The following targets are for the first exercise

ex2_a: ex2_a.o utils.o
	$(CC) $(CFLAGS) -o ex2_a ex2_a.o utils.o

ex2_a.o: ex2_a.c 
	$(CC) $(CFLAGS) -c ex2_a.c 

# The following targets are for the utils

utils.o: utils.c
	$(CC) $(CFLAGS) -c utils.c

clean:
	rm -f ex2_a.o ex2_a 
	