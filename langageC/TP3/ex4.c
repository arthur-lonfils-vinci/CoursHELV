#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int main() {
	
	do {
    int m;

    printf("Entrez le nombre d'entier à insérer (n > 0)\n");
    scanf("%d",&m);

    if (m <= 0)
	    break;
    
    // Première table
    int* tab = (int *)malloc(m * sizeof(int));

    int entier, n = 0, p = 0;
	
    
    printf("Entrez les entiers à insérer dans la table :\n");
    for (int *ptr = tab; ptr-tab < m; ptr++){    
    	scanf("%d", &entier);	
	if (entier < 0) 
		n++;
	else
		p++;

	*ptr = entier;
    }
    


    // Initialiser les tables positifs/négatifs
    int* tabPositif = (int *)malloc(p * sizeof(int ));
    int* tabNegatif = (int *)malloc(n * sizeof(int ));
    int *ptrP = tabPositif, *ptrN = tabNegatif;
    
    for (int *j = tab; j-tab< m; j++) {
     	if (*j < 0) {
		*ptrN++ = *j;
	} else {
		*ptrP++ = *j;
	}

    }

    //Libérer mémoire 1ère table
    free(tab);

    //Afficher les deux tables
    printf("Résultats :\n");
    if (n > 0) {
    	printf("Entiers Négatifs : ");
    	for (int *ptr = tabNegatif; ptr - tabNegatif < n; ptr++){
		printf("%d ", *ptr);
	}
	printf("\n");
    }

    free(tabNegatif);
    
    if (p > 0) {
	printf("Entiers Positifs : ");
	for (int *ptr = tabPositif; ptr - tabPositif < p; ptr++){
		printf("%d ", *ptr);
	}
    }

    printf("\n\n");
	
    free(tabPositif);

    printf("Please continue as long as n is > 0\n");
	}while (true);

    return 0;
}
