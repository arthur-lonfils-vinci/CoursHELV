#include "stdlib.h"
#include "stdio.h"
#include "string.h"
#include "stdbool.h"	
#include "sys/types.h"
#include "unistd.h"


int main() {

	//write(STDOUT_FILENO, "trois... deux... un...\n", 24); // ex1 b
	printf("trois... deux... un..."); // ex1 d
	fflush(stdout); // ex1 d

	int child = fork();

	if (child == 0) {
		//write(STDOUT_FILENO, "partez !\n", 10); // ex1 b
		printf("partez !"); // ex1 d
	}
}