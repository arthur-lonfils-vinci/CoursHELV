CC = gcc

CFLAGS = -O2 -std=c17 -pedantic -Wall -Wvla -Werror -Wno-unused-variable -D_DEFAULT_SOURCE

ALL = server client testmult
UTILS = utils_v1
INSTALL_DIR = /usr/local/bin/ # optional

all: $(ALL)

# Pattern rule: each executable depends on its .o file + utils
%: %.o $(UTILS).o 
	$(CC) $(CFLAGS) -o $@ $^


# Object file dependencies with headers
server.o: server.c $(UTILS).h server.h 
client.o: client.c $(UTILS).h
testmult.o: testmult.c $(UTILS).h testmult.h 
$(UTILS).o: $(UTILS).c $(UTILS).h

# Generic compilation rule
%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

clean:
	rm -f $(ALL) *.o 

uninstall: # optional
	rm -f $(addprefix $(INSTALL_DIR), $(ALL))

install: $(ALL) # optional
	cp $(ALL) $(INSTALL_DIR)

.PHONY: all clean uninstall install