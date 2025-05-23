CFLAGS = gcc -std=c17 -pedantic -Wall -Wvla -Werror -Wno-unused-variable -D_DEFAULT_SOURCE

all: server client testmult

server: server.o utils_v1.o
	$(CFLAGS) -o server server.o utils_v1.o

server.o: server.c utils_v1.h server.h
	$(CFLAGS) -c server.c

client: client.o utils_v1.o
	$(CFLAGS) -o client client.o utils_v1.o

client.o : client.c utils_v1.h
	$(CFLAGS) -c client.c

testmult: testmult.o utils_v1.o
	$(CFLAGS) -o testmult testmult.o utils_v1.o

testmult.o: testmult.c utils_v1.h testmult.h
	$(CFLAGS) -c testmult.c

utils_v1.o: utils_v1.c utils_v1.h
	$(CFLAGS) -c utils_v1.c

clean: 
	rm -f *.o
	rm -f server client testmult			

	