#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>
#include <signal.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <time.h>
#include "testmult.h"
#include "utils_v1.h"

/* Return a random number between 1 and 9 */
int alea()
{
    int c = (int)((double)rand() / (RAND_MAX) * (10 - 1)) + 1;
    return c;
}

// TODO

// Global Variable
int father_to_child[2] = {-1, -1};
int child_to_father[2] = {-1, -1};
int nbQPasse = 0;
int nbQuestion = 0;

void childHandler(void* timer)
{
    int t = *(int *) timer;

    sclose(father_to_child[1]);
    sclose(child_to_father[0]);

    struct mult calcul;
    sread(father_to_child[0], &calcul, sizeof(mult));

    alarm(t);
    printf("%d) %d * %d = ?\n", nbQuestion, calcul.a, calcul.b);

    int result;
    fflush(NULL);
    scanf("%d", &result);
    // dup2(child_to_father[1], STDIN_FILENO);
    alarm(0);

    swrite(child_to_father[1], &result, sizeof(int));
    // sclose(STDIN_FILENO);
    exit(EXIT_SUCCESS);
}

void handleSignals()
{
    printf("---- Temps dépassé pour la question %d ----\n\n", nbQuestion);
}

void setup_signals()
{
    ssigaction(SIGALRM, handleSignals);
}

int main(int argc, char **argv)
{
    // TODO
    setup_signals();

    int error = 0;
    int success = 0;

    int timer;

    if (argc > 1)
    {
        timer = atoi(argv[1]);
    }

    printf("\n\n---- Bienvenue dans cet exercice de multiplication ----\n");
    printf("--> Vous devez répondre à %d questions \n\n", NB_QUEST);


    while (nbQuestion < NB_QUEST)
    {
        nbQuestion++;

        spipe(father_to_child);
        spipe(child_to_father);

        fork_and_run1(childHandler, &timer);

        sclose(father_to_child[0]);
        sclose(child_to_father[1]);

        struct mult calcul;

        calcul.a = alea();
        calcul.b = alea();

        swrite(father_to_child[1], &calcul, sizeof(mult));

        int result = calcul.a * calcul.b;

        int resultFromChild;

        sread(child_to_father[0], &resultFromChild, sizeof(int));

        if (resultFromChild < 0) {
            printf("Fin Forcée du programme\n");
            break;
        }

        if (result == resultFromChild)
        {
            success++;
        } else {
            error++;
        }

        sclose(father_to_child[1]);
        sclose(child_to_father[0]);
    }

    printf("\n---- Résultats ----\n");
    printf("Vous avez réussi %d sur %d\n", success, NB_QUEST);
    printf("Vous avez passé %d question(s)\n\n", nbQPasse);

    if (error == NB_QUEST)
    {
        printf("Échec total: un poste vient de se libérer pour le cours de DevOps\n\n\n");
    }
    else if (error == 0)
    {
        printf("Réussite total: un poste viens de se libérer pour le cours de LAS\n\n\n");
    }
    else
    {
        printf("Peut faire mieux: un poste vient de se libérer pour le cours de BD avancé\n\n\n");
    }

    exit(EXIT_SUCCESS);
}
