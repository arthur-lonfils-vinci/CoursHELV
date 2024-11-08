#include <stdio.h>
#include <stdlib.h>

int main() {
    int m;

    printf("Entrez le nombre d'entier à insérer\n");
    scanf("%d",&m);
    
    // Première table
    int* tab = (int *)malloc(m * sizeof(int));

    int entier, n = 0, p = 0;

    printf("Entrez les entiers à insérer dans la table :\n");
    for (int i = 0; i < m; i++){    
    	scanf("%d", &entier);	
	if (entier < 0) 
		n++;
	else
		p++;

	tab[i] = entier;
    }
    


    // Initialiser les tables positifs/négatifs
    int* tabPositif = (int *)malloc(p * sizeof(int ));
    int* tabNegatif = (int *)malloc(n * sizeof(int ));
    n = 0, p = 0;
    
    for (int *j = tab; j-tab< m; j++) {
     	if (*j < 0) {
		tabNegatif[n++] = *j;
	} else {
		tabPositif[p++] = *j;
	}

    }

    //Libérer mémoire 1ère table
    free(tab);

    //Afficher les deux tables
    printf("Résultats :\n");
    if (n > 0) {
    	printf("Entiers Négatifs : ");
    	for (int i = 0; i < n; i++){
		printf("%d ", tabNegatif[i]);
	}
    }

    free(tabNegatif);

    printf("\n ");
    
    if (p > 0) {
	printf("Entiers Positifs : ");
	for (int i = 0; i < p; i++){
		printf("%d ", tabPositif[i]);
	}
    }

    printf("\n");
	
    free(tabPositif);

    return 0;
}
