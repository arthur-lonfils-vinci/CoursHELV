#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int main() {
	int h, l, i, c = 1, val;
	bool jX = true;
	

	printf("Hauteur = ? : ");
	scanf("%d", &h);
	printf("Largeur = ? : ");
	scanf("%d", &l);

	char **m;
	m = (char**) malloc(h*sizeof(char*));
	
	if (m==NULL) exit(1);
	
	for (i = 0; i < h; i++) {
		m[i] = (char*) malloc(l*sizeof(char));
		if (m[i]==NULL) exit(1);
	}

	for (i = 0; i < h; i++) {
		for (int j = 0; j < l; j++) {
			m[i][j] = '.';
			}
	}

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
		for (i = h-1; i >= 0; i--){
			if (m[i][c-1] == '.') {
				m[i][c-1] = val;
				break;
			}
		}

		for (i = 0; i < h; i++) {
			for (int j = 0; j < l; j++) {
				printf("%c\t", m[i][j]);
			}
			printf("\n");
		}

	}
	




}
