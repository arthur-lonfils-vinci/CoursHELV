#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>


//Function for Matrice with pointers

/*Function to free the memory of a matrice of pointer
 * char m = the matrice
 * int h = the number of lignes
 * */
void freeMatrice (char** m, int h) {
	for (int i = 0; i < h; i++) {
		free(m[i]);
	}
	free(m);
}

/*Function to create a matrice of pointer
 * int h = the number of lignes
 * int l = the number of columns
 * char var = the character that will complete the blank
 * @return char** m = matrice that return char pointer  
 * */
char** createMatrice (int h, int l, char var) {
	char **m;
	m = (char**) malloc(h*sizeof(char*));

	if (m==NULL) exit(1);

	for (int i = 0; i < h; i++){
		m[i] = (char*) malloc(l*sizeof(char));
		if (m[i]==NULL) exit(1);
	}

	for (int i = 0; i < h; i++) {
		for (int j = 0; j < l; j++) {
			m[i][j] = var;
		}
	}

	return m;
}


/*Function to update the number of column of matrice of pointer
 * char** m = matrice of pointer to update
 * int h = the number of lignes
 * int l = the number of columns
 * int newL = the new number of column
 * char var = the charater that will complete the blank
 * @return char** m = the updated version of the matrice of pointer
 * */
char** updateMatrice(char** m, int h, int l, int newL, char var) {
    int lBase = (newL < 0) ? (-newL) : newL;

    for (int i = 0; i < h; i++) {
        char* temp = realloc(m[i], lBase * sizeof(char));
        if (!temp) {
            // Handle realloc failure
        	freeMatrice(m,i);
    		return NULL;
        }
        m[i] = temp;

        // Fill new elements with 'var' if expanding the matrix
        for (int j = l; j < lBase; j++) {
            m[i][j] = var;
        }
    }

    return m;
}


/*Function to print a matrice of pointer
 * char** m = the matrice of pointer to print
 * int h = the number of lignes
 * int l = the number of columns
 * */
void printMatrice (char** m, int h, int l) {
	for (int i = 0; i < h; i++) {
		for (int j = 0; j < l; j++) {
			printf("%c\t", m[i][j]);
		}
		printf("\n");
	}
}
