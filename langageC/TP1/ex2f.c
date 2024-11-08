#include <stdlib.h>
#include <stdio.h>

int main() {
    
    int a, b, quotient = 0, reste, dividend, divisor, verif = 0;

    // Lire deux entiers positifs a et b depuis le clavier
    do {
        printf("Entrez deux entiers positifs (a et b), avec b non nul :\n");
        scanf("%d %d", &a, &b);

        if (a <= 0 || b <= 0) {
            verif = 1;
            printf("Erreur: Les nombres doivent être strictement positifs et b ne doit pas être égal à 0.\n");
        }
    } while (verif);

    // Déterminer le plus grand et le plus petit pour la division
    if (a > b) {
        dividend = a;
        divisor = b;
    } else {
        dividend = b;
        divisor = a;
    }

    // Effectuer la division entière
    reste = dividend;
    while (reste >= divisor) {
        reste -= divisor;
        quotient++;
    }

    // Afficher le quotient et le reste
    printf("Quotient: %d\n", quotient);
    printf("Reste: %d\n", reste);

    return 0;

}

