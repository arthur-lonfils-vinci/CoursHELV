#include "stdio.h"
#include "stdlib.h"
#include "string.h"

#include "../utility.h"

#define MAX_CHAR 27

int main(int argc, char *argv[]) {


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

	for (int i = 1; i < argc; i++) {
		printf("Param n°%d: %s\n", i, argv[i]);
        tab[i-1] = strToLower(tab[i-1]);
	}

	int countNonLu = 0;
	printf("Entrez des lignes de texte (Ctrl+D pour terminer):\n");

	while (1) {
        
        printf("Entrez votre ligne : ");
		char* ligne = strReadLine("Script terminé", MAX_CHAR);

		if (ligne == NULL) {
			break;
		}


		int taille = strLength(ligne);

		printf("Ligne: %s\n", ligne);
		printf("Taille: %d\n", taille);

        ligne = strToLower(ligne);

		int isFound = strFindAndCountString(tab, argc, ligne);
		if (isFound == -1) {
			countNonLu++;
		}
		printf("%s est %s\n", ligne, (isFound != -1) ? "Présent" : "Asbsent");
		printf("Trouvé %d fois\n", (isFound != -1) ? isFound : 0);

        free(ligne);
	}

    matFreeMatrice(tab, argc-1);

	printf("Nombre de non lu: %d\n", countNonLu);


	return 0;
}
