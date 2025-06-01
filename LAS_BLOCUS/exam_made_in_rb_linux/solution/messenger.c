#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include "utils_v2.h"

#define BUFFER_SIZE 100

int pipe_father_child[2];
int pipe_child_father[2];


int countWords(char *buffer, ssize_t size) {

	int count_words = 0;
	bool start_word = false;

	for (int i = 0; i < (int) size; i++) {
		if ((buffer[i] == ' ' || buffer[i] == '\0') && start_word) {
			count_words++;
			start_word = false;
		} else if (buffer[i] != ' ' && buffer[i] != '\0') {
			start_word = true;
		}
	}

	return count_words;
}


void childhandler()
{
	sclose(pipe_father_child[1]);
	sclose(pipe_child_father[0]);

	int nbr_request = 0;

	char request[BUFFER_SIZE];

	ssize_t bytes_read;

	while ((bytes_read = sread(pipe_father_child[0], request, sizeof(request))) > 0)
	{
		nbr_request++;

		request[bytes_read] = '\0';
		int word_count = countWords(request, bytes_read);

		char sendBuffer[BUFFER_SIZE];

		ssize_t size = sprintf(sendBuffer, "[CHILD] Message has %d words", word_count);

		sendBuffer[size] = '\0';

		swrite(pipe_child_father[1], sendBuffer, sizeof(sendBuffer));
	}

	char finalBuffer[BUFFER_SIZE];
	ssize_t size = sprintf(finalBuffer, "[CHILD] Total messages processed: %d", nbr_request);
	finalBuffer[size] = '\0';

	swrite(pipe_child_father[1], finalBuffer, sizeof(finalBuffer));

	sclose(pipe_child_father[1]);
	sclose(pipe_father_child[0]);
	
	exit(EXIT_SUCCESS);

}

int main()
{
	// TODO: Implement the messenger program
	// 1. Create two pipes for bidirectional communication
	// 2. Fork a child process
	// 3. Implement parent logic: read from stdin, send to child, receive responses
	// 4. Implement child logic: receive messages, count words, send back responses

	spipe(pipe_child_father);
	spipe(pipe_father_child);

	fork_and_run0(childhandler);

	sclose(pipe_father_child[0]);
	sclose(pipe_child_father[1]);

	char buffer[BUFFER_SIZE];

	ssize_t bytes_read;

	while ((bytes_read = sread(STDIN_FILENO, buffer, sizeof(buffer))) > 0)
	{
		buffer[bytes_read] = '\0';

		char *newline = strchr(buffer, '\n');
		if (newline) {
			*newline = '\0';
		}

		printf("user sent -> %s for a size of %d\n", buffer, (int) bytes_read);

		swrite(pipe_father_child[1], buffer, sizeof(char) * bytes_read);

		char answer[BUFFER_SIZE];

		bytes_read = sread(pipe_child_father[0], answer, sizeof(answer));

		answer[bytes_read] = '\0';

		printf("[PARENT] %s\n", buffer);
		printf("%s\n", answer);

	}

	sclose(pipe_father_child[1]);

	bytes_read = sread(pipe_child_father[0], buffer, sizeof(buffer));

	buffer[bytes_read] = '\0';

	printf("FIN\n");
	printf("%s\n", buffer);

	sclose(pipe_child_father[0]);

	return 0;
}
