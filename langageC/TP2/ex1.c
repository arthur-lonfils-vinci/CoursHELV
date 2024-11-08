#include <stdlib.h>
#include <stdio.h>
#include <limits.h>
#include <stdbool.h>

#define MAX_ETUDIANTS 50

int main(){

  int i = 0;
  double tab[MAX_ETUDIANTS];
  double var;
  double total = 0;
  int nbrEtudiants = 0;

  printf("Entrez les notes des étudiants (max 50) (Entrez -1 pour terminer)\n");

  do{
    printf("Etudiant n°%d : ",i+1);
    scanf("%lf",&var);
    printf("\n");
    if(var == -1){
      break;
    }
    tab[i] = var;
    total += var;
    nbrEtudiants = i+1;
    i++;
  }while(i<MAX_ETUDIANTS);

  double moyenne = (double) total/nbrEtudiants;

  printf("Moyenne = %0.2f\n", moyenne);

  printf("Ecarts des etudiants par rapport a la moyenne:\n");

  double ecart = 0;
  
  for (i=0; i<nbrEtudiants; i++){
    ecart = tab[i]-moyenne;
    printf("Etudiant %d: %0.2f - %0.2f = %0.2f \n", i+1, tab[i], moyenne, ecart);  
  }
  
  exit(0);

}
