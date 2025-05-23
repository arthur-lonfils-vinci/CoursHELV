CC=gcc

CCFLAGS=-std=c11 -pedantic -Wall -Wvla -Werror -Wno-unused-variable -D_DEFAULT_SOURCE -D_XOPEN_SOURCE -D_BSD_SOURCE

ALL=pipes countingsort

all: $(ALL)

countingsort: countingsort.o utils_v2.o
	$(CC) $(CCFLAGS) -o countingsort countingsort.o utils_v2.o

countingsort.o: countingsort.c utils_v2.h
	$(CC) $(CCFLAGS) -c countingsort.c

pipes: pipes.o utils_v2.o
	$(CC) $(CCFLAGS) -o pipes pipes.o utils_v2.o

pipes.o: pipes.c utils_v2.h
	$(CC) $(CCFLAGS) -c pipes.c

utils_v2.o: utils_v2.c utils_v2.h
	$(CC) $(CCFLAGS) -c utils_v2.c

clean:
	rm -f *.o
	rm -f $(ALL)		
