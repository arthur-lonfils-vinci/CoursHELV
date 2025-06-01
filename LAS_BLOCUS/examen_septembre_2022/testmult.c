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



int timer = 0; // Timer for the questionnaire
int questionCount = 0; // Counter for the number of questions answered
int correctCount = 0; // Counter for the number of correct answers
int errorCount = 0; // Counter for the number of incorrect answers
bool timerOn = false; // Bool to know if the alarm was launched
int pipe_parent_to_child[2]; // Pipe for communication from parent to child
int pipe_child_to_parent[2]; // Pipe for communication from child to parent

/* Return a random number between 1 and 9 */
int alea()
{
    int c = (int)((double)rand() / (RAND_MAX) * (10 - 1)) + 1;
    return c;
}


void child_handler(){
	sclose(pipe_child_to_parent[0]);
	sclose(pipe_parent_to_child[1]);

	int result;
	struct mult m;

	sread(pipe_parent_to_child[0], &m, sizeof(m));
	printf("\nQuestion n°%d) %d * %d = ", questionCount + 1, m.a, m.b);
	fflush(NULL);

	if (scanf("%d", &result) != 1) {
		result = -1; // Indicate error with invalid result
	}	

	swrite(pipe_child_to_parent[1], &result, sizeof(result));

	exit(EXIT_SUCCESS);
}

void handle_sig_alarm() {
	fflush(NULL);
  printf("\n\n---- Temps dépassé pour le questionnaire ----\n\n");
	timerOn = true;
}


int main(int argc, char **argv)
{

	if (argc != 2) {
		fprintf(stderr, "Usage: %s <timer_for_questionnaire>\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	timer = atoi(argv[1]);

	if (timer <= 0) {
		fprintf(stderr, "Timer must be a positive integer.\n");
		exit(EXIT_FAILURE);
	} else {
		printf("Le timer pour ce questionnaire est de %d secondes\n", timer);
	}

  ssigaction(SIGALRM, handle_sig_alarm);

	srand(time(NULL)); // Initialize random seed

	printf("\n\n---- Bienvenue dans cet exercice de multiplication ----\n");
  printf("--> Vous devez répondre à %d questions \n\n", NB_QUEST);

	alarm(timer);

	while (questionCount < NB_QUEST) {
		spipe(pipe_parent_to_child);
		spipe(pipe_child_to_parent);

		fork_and_run0(child_handler);

		sclose(pipe_parent_to_child[0]);
		sclose(pipe_child_to_parent[1]);

		struct mult m;
		m.a = alea();
		m.b = alea();

		questionCount++;
		swrite(pipe_parent_to_child[1], &m, sizeof(m));

		int result;
		read(pipe_child_to_parent[0], &result, sizeof(int));

		if (timerOn || result == EOF) {
			sclose(pipe_parent_to_child[1]);
			sclose(pipe_child_to_parent[0]);
			break;
		}

		if(result == m.a * m.b) {
			correctCount++;
		} else {
			errorCount++;
		}

		sclose(pipe_parent_to_child[1]);
		sclose(pipe_child_to_parent[0]);
	}
	alarm(0);

	fflush(NULL);
  printf("\n---- Résultats ----\n");
  printf("Vous avez réussi %d sur %d\n", correctCount, NB_QUEST);

	if (errorCount == NB_QUEST) {
    printf("Échec total: un poste vient de se libérer pour le cours de DevOps\n\n\n");
	}
	else if (correctCount == NB_QUEST) {
    printf("Réussite total: un poste viens de se libérer pour le cours de LAS\n\n\n");
	}
	else if (timerOn) {
    printf("Peut faire mieux: un poste vient de se libérer pour le cours de BD avancé\n\n\n");
	}
		
	return 0;
}