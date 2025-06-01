CC=gcc
CFLAGS=-std=c11 -pedantic -Wall -Wvla -Werror -Wno-unused-variable -D_DEFAULT_SOURCE

ALL=messenger shared_counter


all: $(ALL)

%: %.c utils_v2.c utils_v2.h
	$(CC) $(CFLAGS) -o $@ $< utils_v2.c

clean:
	rm -f $(ALL)
