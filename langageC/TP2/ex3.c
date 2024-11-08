#include <stdio.h>
#include <stdlib.h>

int main() {
    int m, n;

    scanf("%d",&m);
    scanf("%d", &n);

    // Première table
    char **tab = (char **)malloc(m * sizeof(char *));
    for (int i = 0; i < m; i++) {
        tab[i] = (char *)malloc(n * sizeof(char));
    }

    char c = 'A';

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            tab[i][j] = c;
            
            if(c != 'Z') {
                c += 1;
            } else {
                c = 'A';
            } 

            printf("%c ", tab[i][j]);

        }
        printf("\n");
    }

    // Seconde table
    char **tab2 = (char **)malloc(m * sizeof(char *));
    for (int i = 0; i < m; i++) {
        tab2[i] = (char *)malloc(n * sizeof(char));
    }

    char min = 'A';
    char max = 'Z';
    
    for (int j = 0; j < n; j++) {
        for (int i = 0; i < m; i++) {
            tab2[i][j] = min + (int) (rand() / (RAND_MAX+1.0)*(max-min+1));
            printf("%c ", tab2[i][j]);

        }
        printf("\n");
    }

    // Comparer les deux tables et afficher les lettres qui se trouvent au même endroit
    printf("\n");
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (tab[i][j] == tab2[i][j]) {
                printf("%c en ligne %d et colonne %d\n", tab[i][j], i, j);
            }
        }
    }

    // Libérer la mémoire de la première table
    for (int i = 0; i < m; i++) {
        free(tab[i]);
    }
    free(tab);

    // Libérer la mémoire de la seconde table
    for (int i = 0; i < m; i++) {
        free(tab2[i]);
    }
    free(tab2);

    return 0;
}