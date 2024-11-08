#include <stdlib.h>
#include <stdio.h>

int main() {
    
    int a,b;

    printf("Entier de la variable a : ");
    scanf("%d", &a);

    printf("Entier de la variable b : ");
    scanf("%d", &b);

    int c = a;
    a = b;
    b = c;
    
    printf("Après échange : Entier de la variable a %d, entier de la variable b %d\n", a, b);

    exit(0);
}

