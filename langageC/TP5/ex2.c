#include "stdio.h"
#include "stdlib.h"
//#include "string.h"

#include "../utility.h"

#define MAX_CHAR 27

int main(int argc, char *argv[]) {

	for (int i = 1; i < argc; i++) {
		printf("Param n°%d: %s\n", i, argv[i]);
        argv[i] = strToLower(argv[i]);
	}

	int countNonLu = 0;
	printf("Entrez des lignes de texte (Ctrl+D pour terminer):\n");

	while (1) {

		char* ligne = strReadLine("Script terminé", MAX_CHAR);
		if (ligne == NULL) {
			break;
		}


		int taille = strLength(ligne);

		printf("Ligne: %s\n", ligne);
		printf("Taille: %d\n", taille);

        ligne = strToLower(ligne);

		int isFound = strFindAndCountString(argv, argc, ligne);
		if (isFound == -1) {
			countNonLu++;
		}
		printf("%s est %s\n", ligne, (isFound != -1) ? "Présent" : "Asbsent");
		printf("Trouvé %d fois\n", (isFound != -1) ? isFound : 0);

        free(ligne);
	}

	printf("Nombre de non lu: %d\n", countNonLu);


	return 0;
}
