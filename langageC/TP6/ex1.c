#include "stdbool.h"
#include "stdio.h"
#include "stdlib.h"

bool isPrime(int n);
int *primeNumbers(int n, int *sz);
bool first_prime_numbers(int *t, int sz);

int main() {

  // Test isPrime
  int n;
  printf("Entrez un nombre entier positif : ");
  scanf("%d", &n);
  if (isPrime(n)) {
    printf("%d est un nombre premier\n", n);
  } else {
    printf("%d n'est pas un nombre premier\n", n);
  }

  printf("Entrez le nombre n : ");
  scanf("%d", &n);

  // Test primeNumbers
  int taille;
  int *tab = primeNumbers(n, &taille);
  if (tab == NULL) {
    printf("Erreur lors de l'allocation mémoire\n");
    return 1;
  }

  printf("Les nombres premiers plus petits que %d sont : ", n);
  for (int i = 0; i < taille; i++) {
    printf("%d ", tab[i]);
  }
  printf("\n");
  printf("Total : %d\n", taille);

  free(tab);

  // Test first_prime_numbers
  printf("Entrez le nombre de premiers nombres premiers à afficher : ");
  scanf("%d", &n);

  int sz = n;
  int *t = malloc(sz * sizeof(int));
  first_prime_numbers(t, sz);

  printf("Les %d premiers nombres premiers sont : ", sz);
  for (int i = 0; i < sz; i++) {
    printf("%d ", t[i]);
  }
  printf("\n");
  printf("Total : %d\n", sz);

  free(t);

  return 0;
}

/*
 * PRE : n : nombre entier positif
 * RES : vrai si n est premier ; faux sinon
 */
bool isPrime(int n) {
  if (n < 2)
    return false;
  for (int i = 2; i <= n / 2; i++) {
    if (n % i == 0)
      return false;
  }
  return true;
}

/*• PRE : n >= 2
*• POST : sz est égal à la taille (physique et logique) du tableau renvoyé
*• RES : un tableau trié contenant tous les nombres premiers plus petits que n
si aucune erreur n’est survenue ; NULL si une erreur est survenue
*/

int *primeNumbers(int n, int *sz) {
  if (n < 2) {
    return NULL;
  }
  int *tab = malloc(n * sizeof(int));
  if (tab == NULL) {
    return NULL;
  }

  int j = 0;
  for (int i = 2; i < n; i++) {
    if (isPrime(i)) {
      tab[j] = i;
      j++;
    }
  }

  *sz = j;
  printf("sz = %d\n", *sz);
  if (j == 0) {
    free(tab);
    return NULL;
  }
  tab = realloc(tab, j * sizeof(int));
  return tab;
}

/*PRE : t : tableau de longueur sz
*• POST : t représente un tableau trié contenant les sz premiers nombres
premiers.
*• RES : vrai en cas de succès ; faux sinon
*• bool first_prime_numbers (int* t, int sz)
*/

bool first_prime_numbers(int *t, int sz) {

  if (t == NULL || sz < 1) {
    return false;
  }

  int n = 2;
  int szr;
  int *pn = primeNumbers(n, &szr);

  while (szr < sz) {
    n = n * 2;
    free(pn);
    pn = primeNumbers(n, &szr);
  }

  // copier les sz premiers entiers de pn dans t
  for (int i = 0; i < sz; i++) {
    t[i] = pn[i];
  }

  free(pn);
  return true;
}
