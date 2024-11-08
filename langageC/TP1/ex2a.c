#include <stdlib.h>
#include <stdio.h>

int main() {
    
    int a,b;

    printf("Premier entier : ");
    scanf("%d", &a);

    printf("Deuxième entier : ");
    scanf("%d", &b);
    
    printf("le produit de %d par %d vaut %d\n", a, b, a*b);

    exit(0);
}

