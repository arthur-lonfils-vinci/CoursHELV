CC=gcc

CCFLAGS=-std=c11 -pedantic -Wall -Wvla -Werror -Wno-unused-variable -D_DEFAULT_SOURCE -D_XOPEN_SOURCE -D_BSD_SOURCE

ALL=messenger shared_counter registration_server registration_client

all: $(ALL)

messenger: messenger.o utils_v2.o
	$(CC) $(CCFLAGS) -o messenger messenger.o utils_v2.o

messenger.o: messenger.c utils_v2.h
	$(CC) $(CCFLAGS) -c messenger.c

shared_counter: shared_counter.o utils_v2.o
	$(CC) $(CCFLAGS) -o shared_counter shared_counter.o utils_v2.o

shared_counter.o: shared_counter.c utils_v2.h
	$(CC) $(CCFLAGS) -c shared_counter.c

registration_server: registration_server.o utils_v2.o
	$(CC) $(CCFLAGS) -o registration_server registration_server.o utils_v2.o

registration_server.o: registration_server.c utils_v2.h
	$(CC) $(CCFLAGS) -c registration_server.c

registration_client: registration_client.o utils_v2.o
	$(CC) $(CCFLAGS) -o registration_client registration_client.o utils_v2.o

registration_client.o: registration_client.c utils_v2.h
	$(CC) $(CCFLAGS) -c registration_client.c

utils_v2.o: utils_v2.c utils_v2.h
	$(CC) $(CCFLAGS) -c utils_v2.c

clean:
	rm -f *.o
	rm -f $(ALL)
