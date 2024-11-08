#include <stdlib.h>
#include <stdio.h>

int main() {
    
    int input;
    int verif = 0;

    do {
        printf("Entrez l'entier strictement positif : ");
        scanf("%d", &input);
        if(input <= 0){
            verif = 1;
        }
    } while(verif);
    

    int i;
    for ( i = 1; i <= input+1; i++) {
        if(input % i == 0){
            printf("diviseur : %d\n", i);
        }
    }

    printf("\n");

    exit(0);
}

