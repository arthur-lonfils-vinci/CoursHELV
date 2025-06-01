CC=gcc
CFLAGS=-std=c17 -pedantic -Wall -Wvla -Werror -Wno-unused-variable -D_DEFAULT_SOURCE

ALL=messenger shared_counter registration_server registration_client


all: $(ALL)

%: %.c utils_v2.c utils_v2.h
	$(CC) $(CFLAGS) -o $@ $< utils_v2.c

clean:
	rm -f $(ALL)
