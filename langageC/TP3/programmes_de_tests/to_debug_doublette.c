#include <stdio.h>
#include <stdlib.h>

#define SIZE 10

int main(int argc, char **args)
{
    int *t1, *t2, *t3;

    t1 = (int*)malloc(SIZE*sizeof(int));
    t2 = (int*)malloc(SIZE*sizeof(int));
    t3 = (int*)malloc(SIZE*sizeof(int));
    if (t1 == NULL || t2 == NULL || t3 == NULL){
        perror("out of mem\n");
        exit(1);
    }
    
    for (int i=0; i<SIZE; i++){
        t1[i] = t2[i] = t3[i] = i;
    }
	
    free(t1);
    free(t2);
    free(t1);
}