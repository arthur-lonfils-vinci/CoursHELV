CFLAGS= -std=c17 -pedantic -Wall -Wvla -Werror -Wno-unused-variable -D_DEFAULT_SOURCE
CC = gcc

all: pipes countingsort

pipes: pipes.o utils_v2.o
	$(CC) $(CCFLAGS) -o pipes pipes.o utils_v2.o

pipes.o: pipes.c utils_v2.h
	$(CC) $(CCFLAGS) -c pipes.c

countingsort: countingsort.o utils_v2.o
	$(CC) $(CCFLAGS) -o countingsort countingsort.o utils_v2.o

countingsort.o: countingsort.c utils_v2.h
	$(CC) $(CCFLAGS) -c countingsort.c

utils_v2.o: utils_v2.c utils_v2.h
	$(CC) $(CCFLAGS) -c utils_v2.c

clean:
	rm -f *.o 
	rm -f pipes