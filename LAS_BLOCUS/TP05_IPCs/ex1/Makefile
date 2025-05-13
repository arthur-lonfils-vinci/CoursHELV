CFLAGS = -std=c17 -pedantic -Wall -Wextra -Werror -D_DEFAULT_SOURCE
CC = gcc

all: radar dashboard

# radar
radar: radar.o utils.o
	$(CC) $(CFLAGS) -o radar radar.o utils.o

radar.o: radar.c
	$(CC) $(CFLAGS) -c radar.c

# dashboard
dashboard: dashboard.o utils.o
	$(CC) $(CFLAGS) -o dashboard dashboard.o utils.o

dashboard.o: dashboard.c
	$(CC) $(CFLAGS) -c dashboard.c

# utils
utils.o: utils.c
	$(CC) $(CFLAGS) -c utils.c

clean:
	rm -f radar.o dashboard.o utils.o radar dashboard && clear
