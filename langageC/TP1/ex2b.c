#include <stdlib.h>
#include <stdio.h>

int main() {
    
    double a,b;

    printf("Premier entier : ");
    scanf("%lf", &a);

    printf("Deuxième entier : ");
    scanf("%lf", &b);
    
    printf("le produit de %lf par %lf vaut %lf\n", a, b, a*b);

    exit(0);
}

