#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include "utils_v2.h"

#define BUFFER_SIZE 100

int pipe_father_child[2];
int pipe_child_father[2];


int countWords(char *buffer, ssize_t size) {

	// TODO

	return -1;
}


void childhandler()
{

	// TODO

}

int main()
{
	// TODO: Implement the messenger program
	// 1. Create two pipes for bidirectional communication
	// 2. Fork a child process
	// 3. Implement parent logic: read from stdin, send to child, receive responses
	// 4. Implement child logic: receive messages, count words, send back responses

	return 0;
}
