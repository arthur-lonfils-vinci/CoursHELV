#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "../utility.h"


int main() {
	int h, l, i, c = 1, val;
	bool jX = true;
	

	printf("Hauteur = ? : ");
	scanf("%d", &h);
	printf("Largeur = ? : ");
	scanf("%d", &l);
	
	
	char **m = createMatrice(h, l, '.');

	//add 'x' and 'o'
	
	while (c != 0){

		if (jX){
		       	val = 'X';
			jX = false;
		} else {
			val = 'O';
			jX = true;
		}


		printf("Colonne joueur %c = ? : ", val);
		scanf("%d", &c);
		if (c == 0) break;

		if (c < 0 || c > l) {
		       	m = updateMatrice(m, h, l, c, '.');
			if (c < 0) {
				l = l+c;
			}
			else l = c;
		}
	

		for (i = h-1; i >= 0; i--){
			if (m[i][c-1] == '.') {
				m[i][c-1] = val;
				break;
			}
		}

		printMatrice(m, h, l);

	}

	freeMatrice(m, h);
}
