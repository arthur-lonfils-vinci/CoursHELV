CC = gcc
CFLAGS = -std=c17 -pedantic -Wall -Wvla -Werror -Wno-unused-variable -D_DEFAULT_SOURCE

OPT_FLAGS = -O2 # optional for optimization

ALL = capteur imptot # the binary name needed
UTILS = utils_v3 # the utils files needed

all: $(ALL)

# Generic pattern: executables with their objects dependecies
%: %.o $(UTILS).o 
	$(CC) $(OPT_FLAGS) $(CFLAGS) -o $@ $^


capteur.o: capteur.c $(UTILS).h
imptot.o: imptot.c $(UTILS).h
$(UTILS).o: $(UTILS).c $(UTILS).h

# Generic pattern: link objects with their dependecies
%.o: %.c 
	$(CC) $(OPT_FLAGS) $(CFLAGS) -c $< -o $@

clean:
	rm -f $(ALL) *.o 

.PHONY: all clean # optional