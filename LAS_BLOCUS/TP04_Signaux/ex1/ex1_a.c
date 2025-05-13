#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>

#include "utils.h"

int main() {
	int child = sfork();

	if (child == 0) {
		while (1) {
			sleep(1);
		}
	}

	return 0;
}