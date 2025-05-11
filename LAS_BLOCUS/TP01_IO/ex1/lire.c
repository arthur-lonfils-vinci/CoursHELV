#include "stdlib.h"
#include "stdio.h"
#include "string.h"
#include "unistd.h"
#include "fcntl.h"
#include "sys/types.h"

#include "../utils_v1.h"

// This program reads from the standard input (read) max 10 characters ('\n' included) and prints it to the standard output (write) with a maximum of 10 characters (including '\n').
// The program uses the standard library functions: read, write, close.
// The program will throw errors if the "check" method given in "../utils_v1.h" returns -1.
// repeat until Ctrl-D pressed

#define BUFFER_SIZE 10

void lire();

int main() {
		lire();
		return 0;
}

void lire() {
		char buffer[BUFFER_SIZE];
		int bytesRead;

		while (1) {
				bytesRead = sread(STDIN_FILENO, buffer, BUFFER_SIZE - 1);
				if (bytesRead <= 0) {
						break;
				}

				buffer[bytesRead] = '\0';
				swrite(STDOUT_FILENO, buffer, bytesRead);
		}
}