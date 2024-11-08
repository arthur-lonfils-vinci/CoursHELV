#include <stdlib.h>
#include <stdio.h>
#include <limits.h>

int main(){

  int i, result = 1, verif = 1, max_int = INT_MAX;
  
  printf("Voici l'entier max possible : %d\n", max_int);


  for (i = 2; i <= max_int; i++){
      printf("%d *%d = %d\n", result, i, result*i);
      result *= i;
      if(result < 0){
      printf("C'est fini : %d\n", result);
      result = i;
      break;
      }
  }
  
  printf("Voici la plus grande factorielle possible en int => %d\n", result);

  exit(0);

}
