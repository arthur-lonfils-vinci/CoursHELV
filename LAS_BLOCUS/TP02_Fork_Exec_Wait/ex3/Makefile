CFLAGS = -std=c17 -pedantic -Wall -Wextra -Werror -D_DEFAULT_SOURCE
CC = gcc


all: ex3_a

# The following targets are for the first exercise

ex3_a: ex3_a.o utils.o
	$(CC) $(CFLAGS) -o ex3_a ex3_a.o utils.o

ex3_a.o: ex3_a.c 
	$(CC) $(CFLAGS) -c ex3_a.c 

# The following targets are for the utils

utils.o: utils.c
	$(CC) $(CFLAGS) -c utils.c

clean:
	rm -f ex3_a.o ex3_a 
	