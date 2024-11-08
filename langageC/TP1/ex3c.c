#include <stdlib.h>
#include <stdio.h>
#include <limits.h>

int main(){

  int i, verif = 1, max_int = INT_MAX;
  double result = 1;

  printf("Voici l'entier (int) max possible : %d\n", max_int);


  for (i = 2; i <= max_int; i++){
      printf("%lf * %d = %lf\n", result, i, result*i);
      result *= i;
      if(result < 0){
      printf("Voici le resultat en double : %.0f\n", result);
      break;
      }
  }
  
  printf("Voici la plus grande factorielle possible en int => %d\n", i);

  exit(0);

}
