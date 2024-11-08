// utility.h
#ifndef UTILITY_H
#define UTILITY_H

void freeMatrice (char** m, int h);

char** createMatrice (int h, int l, char var);

char** updateMatrice(char** m, int h, int l, int newL, char var);

void printMatrice (char** m, int h, int l);

#endif

