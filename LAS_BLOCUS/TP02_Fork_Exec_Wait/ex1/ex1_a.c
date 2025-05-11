#include "stdlib.h"
#include "stdio.h"
#include "string.h"
#include "stdbool.h"	
#include "sys/types.h"
#include "unistd.h"


int main() {

	int a = 5;

	int child = fork();

	if (child == 0) {
		// Child process
		printf("Child process %d\n", child);
		int b = a * 2;
		printf("Child process: a = %d and b = %d\n", a, b);
	} else {
		// Parent process
		int b =  a * 5;
		printf("Parent process: a = %d and b = %d\n", a, b);
	}
	
}