CFLAGS = -std=c17 -pedantic -Wall -Wvla -Werror -Wno-unused-variable -D_DEFAULT_SOURCE
CC = gcc

PROGRAMS = pipes countingsort
COMMON_OBJS = utils_v2.o

all: $(PROGRAMS)

# Generic pattern rule for building programs
%: %.o $(COMMON_OBJS)
	$(CC) $(CFLAGS) -o $@ $^

# Generic pattern rule for building object files
%.o: %.c utils_v2.h
	$(CC) $(CFLAGS) -c $<

clean:
	rm -f *.o $(PROGRAMS)