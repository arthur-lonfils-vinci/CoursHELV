CC=gcc
CFLAGS=-std=c17 -Werror=vla -pedantic -Wall -Wno-unused-variable -D_DEFAULT_SOURCE

PRG = admin3 switch train

all: $(PRG)

admin3: ipc_conf.h utils_v2.o admin3.c
	$(CC) $(CFLAGS) -o admin3 admin3.c utils_v2.o

switch: ipc_conf.h utils_v2.o switch.c
	$(CC) $(CFLAGS) -o switch switch.c utils_v2.o

train: ipc_conf.h utils_v2.o train.c
	$(CC) $(CFLAGS) -o train train.c utils_v2.o

utils_v2.o : utils_v2.h utils_v2.c
	$(CC) $(CFLAGS) -c utils_v2.c

clean:
	rm -f *.o
	rm -f $(PRG)

sweep: clean
	ipcrm -a
