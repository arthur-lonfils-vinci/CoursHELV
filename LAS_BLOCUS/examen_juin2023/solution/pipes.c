#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include "utils_v2.h"

#define BUFFER_SIZE 80

void child(void *pipe1, void *pipe2);

int main(int argc, char **argv)
{

    int fatherToChild[2];
    int childToFather[2];
    char line[BUFFER_SIZE + 1];
    int nbChar;
    int countChar;
    int nbLines;

    spipe(fatherToChild);
    spipe(childToFather);

    fork_and_run2(child, fatherToChild, childToFather);

    // parent
    sclose(fatherToChild[0]);
    sclose(childToFather[1]);

    while ((nbChar = sread(0, line, BUFFER_SIZE)) > 0)
    {
        // neccesary to remove \n
        line[nbChar - 1] = '\0';
        swrite(fatherToChild[1], line, strlen(line));
        /* pere attend la ligne renvoyee par son fils */
        sread(childToFather[0], &countChar, sizeof(int));

        // swrite(1, line, countChar);  ou  printf
        printf("%s", line);
        // pas de (s)write ici ou alors il faut convertir l'entier en chaine de caractères.
        printf(" : %d\n", countChar);
    }

    // propager le ctrl-d
    sclose(fatherToChild[1]);

    nbChar = sread(childToFather[0], &nbLines, sizeof(int));
    printf("Le programme a traité %d lignes\n", nbLines);

    sclose(childToFather[0]);
}

void child(void *pipe1, void *pipe2)
{
    int *fatherToChild = (int *)pipe1;
    int *childToFather = (int *)pipe2;
    int nbLines = 0;
    int nbChar;
    char line[BUFFER_SIZE];
    sclose(fatherToChild[1]);
    sclose(childToFather[0]);
    dup2(fatherToChild[0], 0);
    dup2(childToFather[1], 1);

    while (1)
    {

        nbChar = sread(0, line, BUFFER_SIZE);

        if (nbChar == 0)
        {
            swrite(1, &nbLines, sizeof(int));
            break;
        }
        swrite(1, &nbChar, sizeof(int));
        nbLines++;
    }

    sclose(0);
    sclose(1);
}