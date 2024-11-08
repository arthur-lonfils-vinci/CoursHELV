#include <stdlib.h>
#include <stdio.h>

int main(){

  int input, i, result = 1;

  printf("Entrez l'entier : ");
  scanf("%d", &input);

  for(i= 1; i <= input; i++){
    result *= i;
  }

  printf("Voici le résultat => %d\n", result);

}
