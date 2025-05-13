#include <stdlib.h>
#include <stdio.h>

/// Déclaration de constantes

#define MAX_VAL   1000000000   // 1 milliard
#define SEUIL     MAX_VAL/2
#define POURCENT  SEUIL/(double)MAX_VAL*100


/// Programme principal

int main(int argc, char** argv) 
{
    printf("RAND_MAX = %d\n", RAND_MAX);
    printf("MAX_VAL  = %d  ==> valeurs aléatoires entre %d et %d\n", MAX_VAL, 0, MAX_VAL-1);
    printf("SEUIL = MAX_VAL/2 = %d  ==> valeurs inférieures au seuil: %.0f%%\n\n", SEUIL, POURCENT);
    printf("Pour une suite uniformément répartie dans l'intervalle [0,MAX_VAL[, "\
        "le pourcentage de valeurs aléatoires inférieures "\
        "au seuil MAX_VAL/2 devrait correspondre à %.2f%%.\n", POURCENT);
    printf("Vérifions si c'est bien le cas.\n\n");

    // TODO

}
