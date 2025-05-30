#include <stdlib.h>
#include <stdio.h>
#include <signal.h>
#include <errno.h>
#include <unistd.h>

#include "utils.h"

/// Déclaration de constantes

#define MAX_VAL   100000000   // 1 milliard
#define SEUIL     MAX_VAL/2
#define POURCENT  SEUIL/(double)MAX_VAL*100

int cnt1 = 0;
int cnt2 = 0;

int nbInf1 = 0;
int nbInf2 = 0;

void handler_ctrlc_child1() {
    double pourcent1 = (double)nbInf1 / cnt1 * 100;
    printf("\n Child 1: %d valeurs inférieures au seuil sur %d (%.2f%%)\n", 
           nbInf1, cnt1, pourcent1);
}

void handler_ctrlc_child2() {
    double pourcent2 = (double)nbInf2 / cnt2 * 100;
    printf("\n Child 2: %d valeurs inférieures au seuil sur %d (%.2f%%)\n", 
           nbInf2, cnt2, pourcent2);
}

void childProccess1(void* pipe){
    ssigaction(SIGINT, handler_ctrlc_child1);
    ssigaction(SIGTSTP, NULL);
    int *pipefc1 = pipe;
    sclose(pipefc1[1]);
    int number;

    while(1){
        read(pipefc1[0], &number, sizeof(int));

        cnt1++;

        int result = number % MAX_VAL;
        if (result < SEUIL){
            nbInf1++;
        }
    }


}

void childProccess2(void* pipe){
    ssigaction(SIGINT, handler_ctrlc_child2);
    ssigaction(SIGTSTP, NULL);
    int *pipefc2 = pipe;
    sclose(pipefc2[1]);
    int number;
    printf("Child2");
    while(1){
    sread(pipefc2[0], &number, sizeof(int));

    cnt2++;

    int result = (number / (RAND_MAX+1.0) * MAX_VAL);
    if (result < SEUIL){
        nbInf2++;
    }
    }
}


void handler_ctrlz(){
    printf("\n\nCTRL-Z reçu, on arrête le programme.\n");
    exit(EXIT_SUCCESS);
}

void handler_ctrlc() {

}

/// Programme principal

int main() 
{
    printf("RAND_MAX = %d\n", RAND_MAX);
    printf("MAX_VAL  = %d  ==> valeurs aléatoires entre %d et %d\n", MAX_VAL, 0, MAX_VAL-1);
    printf("SEUIL = MAX_VAL/2 = %d  ==> valeurs inférieures au seuil: %.0f%%\n\n", SEUIL, POURCENT);
    printf("Pour une suite uniformément répartie dans l'intervalle [0,MAX_VAL[, "\
        "le pourcentage de valeurs aléatoires inférieures "\
        "au seuil MAX_VAL/2 devrait correspondre à %.2f%%.\n", POURCENT);
    printf("Vérifions si c'est bien le cas.\n\n");

    ssigaction(SIGTSTP, handler_ctrlz);
    ssigaction(SIGINT, handler_ctrlc);

    int pipe_parent_to_child1[2];
    int pipe_parent_to_child2[2];

    spipe(pipe_parent_to_child1);
    spipe(pipe_parent_to_child2);

    fork_and_run1(childProccess1, (void*) pipe_parent_to_child1);
    fork_and_run1(childProccess2, (void*) pipe_parent_to_child2);


    sclose(pipe_parent_to_child1[0]);
    sclose(pipe_parent_to_child2[0]);

    while(1) {
        int number = rand();
        swrite(pipe_parent_to_child1[1], &number, sizeof(int));
        swrite(pipe_parent_to_child2[1], &number, sizeof(int));
    }
    int status;
    swait(&status);
    swait(&status);

}