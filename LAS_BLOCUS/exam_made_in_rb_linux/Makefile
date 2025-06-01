CC=gcc

CCFLAGS=-std=c11 -pedantic -Wall -Wvla -Werror -Wno-unused-variable -D_DEFAULT_SOURCE -D_XOPEN_SOURCE -D_BSD_SOURCE

ALL=messenger shared_counter

all: $(ALL)

messenger: messenger.o utils_v2.o
	$(CC) $(CCFLAGS) -o messenger messenger.o utils_v2.o

messenger.o: messenger.c utils_v2.h
	$(CC) $(CCFLAGS) -c messenger.c

shared_counter: shared_counter.o utils_v2.o
	$(CC) $(CCFLAGS) -o shared_counter shared_counter.o utils_v2.o

shared_counter.o: shared_counter.c utils_v2.h
	$(CC) $(CCFLAGS) -c shared_counter.c

utils_v2.o: utils_v2.c utils_v2.h
	$(CC) $(CCFLAGS) -c utils_v2.c

clean:
	rm -f *.o
	rm -f $(ALL)
