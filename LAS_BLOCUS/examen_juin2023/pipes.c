#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>

#include "utils_v2.h"

#define MAX_CHAR 80

int main(int argc, char **argv)
{
	int fatherToChild[2];
	int childToFather[2];
	char line[MAX_CHAR + 1];
	int nbChar;
	int countChar;
	int nbLines;

	spipe(fatherToChild);
	spipe(childToFather);

	pid_t childId = sfork();

	if (childId != 0) { // Parent process
		sclose(fatherToChild[0]);
		sclose(childToFather[1]);

		printf("Ecrivez une ligne (CTRL-D pour terminer) :\n");

		while ((nbChar = sread(0, line, MAX_CHAR)) > 0) {
			line[nbChar - 1] = '\0';
			
			swrite(fatherToChild[1], line, strlen(line));
			
			sread(childToFather[0], &countChar, sizeof(int));

			printf("%s : %d\n", line, countChar);
		}

		sclose(fatherToChild[1]);

		sread(childToFather[0], &nbLines, sizeof(int));
		printf("Le programme a traité %d lignes\n", nbLines);

		sclose(childToFather[0]);

	} else { // Child process
		int nbLines = 0;
		
		sclose(fatherToChild[1]);
		sclose(childToFather[0]);
		dup2(fatherToChild[0], 0);
		dup2(childToFather[1], 1);
		
		while (1) {
			nbChar = sread(0, line, MAX_CHAR);
			
			if (nbChar == 0) {
				swrite(1, &nbLines, sizeof(int));
				break;
			}
			
			swrite(1, &nbChar, sizeof(int));
			nbLines++;
		}
		
		sclose(0);
		sclose(1);
		exit(0);
	}

	return 0;
}
