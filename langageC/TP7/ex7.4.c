#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char **copierArgs(char **tab, int n, int *ncp);
void trier(char **tab, int n);
void afficher(char **tab, int n);

int main(int argc, char *argv[]) {
  // Copie profonde des arguments du programme
  int *ncp = &argc;

  char **tab = copierArgs(argv, argc, ncp);
  if (tab == NULL) {
    return 1;
  }

  // Affichage du tableau
  printf("Tableau initial:\n");
  afficher(tab, *ncp);
  
  printf("\n");
  // Tri du tableau
  trier(tab, *ncp);  

  // Affichage du tableau trié
  printf("Tableau trié:\n");
  afficher(tab, *ncp);

  // Libération de la mémoire dynamique
  for (int i = 0; i < *ncp; i++) {
    free(tab[i]);
  }
  free(tab);
}

/**
 * PRE: tab: tableau de n chaînes de caractères
 * POST: *ncp: nombre de chaînes dans le tableau renvoyé
 * RES: renvoie un tableau contenant les chaînes de tab sans doublon
 *      (taille logique = taille physique); NULL si une erreur s'est produite
 */
char **copierArgs(char **tab, int n, int *ncp) {
  char **cpy = malloc(n * sizeof(char *));
  if (cpy == NULL) {
    return NULL;
  }

  int j = 0;

  for (int i = 1; i < n; i++) {
    
      int is_duplicate = 0;
    for (int k = 0; k < j; k++) {
      if (strcmp(cpy[k], tab[i]) == 0) {
        is_duplicate = 1;
        break;
      }
    }

    if (!is_duplicate) {
        cpy[j] = malloc((strlen(tab[i]) + 1) * sizeof(char));
      if (cpy[j] == NULL) {
          for (int l = 0; l < j; l++) {
          free(cpy[l]);
        }
        free(cpy);
        return NULL;
      }
      strcpy(cpy[j], tab[i]);
      j++;
    }
  }

  *ncp = j;

  
  cpy = realloc(cpy, j * sizeof(char *));
  if (cpy == NULL) {
    for (int l = 0; l < j; l++) {
      free(cpy[l]);
    }
    return NULL;
  }

  return cpy;
}

/**
 * PRE: tab: tableau de n chaînes de caractères
 * POST: les n chaînes de tab sont triées par ordre alphabétique
 *       (algorithme: tri par sélection)
 */
void trier(char **tab, int n) {
  if (n < 0) {
        printf("Erreur: n < 0\n");
        return;
    }

    for (int i = 0; i < n - 1; i++) {
        int min = i;
        for (int j = i + 1; j < n; j++) {
            if (strcmp(tab[j], tab[min]) < 0) {
                min = j;
            }
        }
        if (min != i) {
            char *tmp = tab[i];
            tab[i] = tab[min];
            tab[min] = tmp;
        }
    }
}

/**
 * PRE: tab: tableau de n chaînes de caractères
 * POST: affiche les chaînes de tab (à raison d'une par ligne,
 *       précédée par leur numéro)
 */
void afficher(char **tab, int n) {
    if (n == 0) {
        printf("Tableau vide\n");
        return;
    }
    for (int i = 0; i < n; i++) {
        printf("%d: %s\n", i, tab[i]);
    }
}
