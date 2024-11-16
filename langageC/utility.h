// utility.h
#include <stdbool.h>
#ifndef UTILITY_H
#define UTILITY_H


//Function for Matrice with pointers (mat... functions)
void matFreeMatrice (char** m, int h);

char** matCreateMatrice (int h, int l, char var);

char** matUpdateMatrice(char** m, int h, int l, int newL, char var);

void matPrintMatrice (char** m, int h, int l);


//Function for string (str... functions)
int strLength(char* str);

char* strDeleteNewLine(char* str);

char* strToUpper(char* str);

char* strToLower(char* str);

char* strReadLine(char* messageError, int maxCharactere);

bool strCompareString(char* str1, char* str2);

bool strFindString(char* str1[], int size, char* str2);

int strFindAndCountString(char* str1[], int size, char* str2);


#endif

