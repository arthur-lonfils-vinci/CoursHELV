#include <stdlib.h>
#include <stdio.h>

int main() {
    
    char ch;

    for (ch = 'A'; ch <= 'Z'; ch++) {
        printf("Caractère = '%c' code déc. = %d code hexa. = %X\n", ch, ch, ch);
    }

    for (ch = '0'; ch <= '9'; ch++) {
        printf("Caractère = '%c' code déc. = %d code hexa. = %X\n", ch, ch, ch);
    }

    exit(0);
}

