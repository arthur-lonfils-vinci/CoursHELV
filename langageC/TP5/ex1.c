#include "stdio.h"
#include "stdlib.h"
#include "string.h"

#include "../utility.h"


/* this programm will read a ligne from the user and print it in uppercase then in lowercase */

int main() {
	char *ligne = NULL;
	size_t len = 0;
	ssize_t read;

	printf("Enter a line: ");
	ligne = readLine();
	
	ligne = toUpper(ligne);
	printf("Uppercase: %s", ligne);

	ligne = toLower(ligne);
	printf("Lowercase: %s", ligne);

	free(ligne);
	
	return 0;
}
