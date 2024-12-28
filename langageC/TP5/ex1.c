#include "stdio.h"
#include "stdlib.h"
#include "string.h"

#include "../utility.h"


/* this programm will read a ligne from the user and print it in uppercase then in lowercase */

int main(int argc, char* argv[]) {

    char** tab = (char**)malloc((argc-1) * sizeof(char*));

    if (tab == NULL) {
        return 0;
    }

    for (int i = 1; i < argc; i++) {
        tab[i-1] = (char*)malloc((strLength(argv[i])+1) * sizeof(char));
        if (tab[i-1] == NULL) {
            matFreeMatrice(tab, argc-1);
            return 0;
        }
        strcpy(tab[i-1], argv[i]);
    }

    printf("Lowercase: ");

    for (int i = 0; i < argc-1; i++) {
        printf("%s ", strToLower(tab[i]));
    }

    printf("\n");

    printf("Uppercase: ");

    for (int i = 0; i < argc-1; i++) {
        printf("%s ", strToUpper(tab[i]));
    }

    printf("\n");

    printf("Original: ");

    for (int i = 1; i < argc; i++) {
        printf("%s ", argv[i]);
    }

    printf("\n");

    matFreeMatrice(tab, argc-1); 
	
	return 0;
}
