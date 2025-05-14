CC=gcc

CCFLAGS=-std=c17 -pedantic -Wall -Wvla -Werror -Wno-unused-variable -D_DEFAULT_SOURCE

ALL= ex2

all: $(ALL)

ex2: ex2.o utils.o
	$(CC) $(CCFLAGS) -o ex2 ex2.o utils.o

ex2.o: ex2.c utils.h
	$(CC) $(CCFLAGS) -c ex2.c

utils.o: utils.c utils.h
	$(CC) $(CCFLAGS) -c utils.c

clean:
	rm -f *.o
	rm -f $(ALL)
	rm -f *.html
