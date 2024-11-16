#include "utility.h"
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function for Matrice with pointers

/*Function to free the memory of a matrice of pointer
 * char m = the matrice
 * int h = the number of lignes
 * */
void matFreeMatrice(char **m, int h) {
  for (int i = 0; i < h; i++) {
    free(m[i]);
  }
  free(m);
}

/*Function to create a matrice of pointer
 * int h = the number of lignes
 * int l = the number of columns
 * char var = the character that will complete the blank
 * @return char** m = matrice that return char pointer
 * */
char **matCreateMatrice(int h, int l, char var) {
  char **m;
  m = (char **)malloc(h * sizeof(char *));

  if (m == NULL)
    exit(1);

  for (int i = 0; i < h; i++) {
    m[i] = (char *)malloc(l * sizeof(char));
    if (m[i] == NULL)
      exit(1);
  }

  for (int i = 0; i < h; i++) {
    for (int j = 0; j < l; j++) {
      m[i][j] = var;
    }
  }

  return m;
}

/*Function to update the number of column of matrice of pointer
 * char** m = matrice of pointer to update
 * int h = the number of lignes
 * int l = the number of columns
 * int newL = the new number of column
 * char var = the charater that will complete the blank
 * @return char** m = the updated version of the matrice of pointer
 * */
char **matUpdateMatrice(char **m, int h, int l, int newL, char var) {
  int lBase = (newL < 0) ? (-newL) : newL;

  for (int i = 0; i < h; i++) {
    char *temp = realloc(m[i], lBase * sizeof(char));
    if (!temp) {
      // Handle realloc failure
      matFreeMatrice(m, i);
      return NULL;
    }
    m[i] = temp;

    // Fill new elements with 'var' if expanding the matrix
    for (int j = l; j < lBase; j++) {
      m[i][j] = var;
    }
  }

  return m;
}

/*Function to print a matrice of pointer
 * char** m = the matrice of pointer to print
 * int h = the number of lignes
 * int l = the number of columns
 * */
void matPrintMatrice(char **m, int h, int l) {
  for (int i = 0; i < h; i++) {
    for (int j = 0; j < l; j++) {
      printf("%c\t", m[i][j]);
    }
    printf("\n");
  }
}

// Function for string

/*Function to return the length of a string
 * char* str = the string to get the length
 * @return int = the length of the string
 * */
int strLength(char *str) {
  int i = 0;
  while (str[i] != '\0') {
    i++;
  }
  return i;
}

/*Function to delete '\n' from a string
 * char* str = the string to delete '\n'
 * @return char* str = the string without '\n'
 * */
char *strDeleteNewLine(char *str) {
  for (int i = 0; i < strLength(str); i++) {
    if (str[i] == '\n') {
      str[i] = '\0';
    }
  }
  return str;
}

/*Function to convert a string to uppercase
 * char[] str = the string to convert
 * @return char[] str = the string converted to uppercase
 * */
char *strToUpper(char *str) {
  str = strDeleteNewLine(str);
  for (int i = 0; i < strLength(str); i++) {
    if (str[i] >= 'a' && str[i] <= 'z') {
      str[i] = str[i] - 32;
    }
  }
  return str;
}

/*Function to convert a string to lowercase
 * char[] str = the string to convert
 * @return char[] str = the string converted to lowercase
 * */
char *strToLower(char *str) {
  str = strDeleteNewLine(str);
  for (int i = 0; i < strLength(str); i++) {
    if (str[i] >= 'A' && str[i] <= 'Z') {
      str[i] = str[i] + 32;
    }
  }
  return str;
}

/*Function to read a line from the user
 * @return char* line = the line read from the user
 * */
void clearBuffer() {
  int c;
  while ((c = getchar()) != '\n' && c != EOF)
    ;
}

char *strReadLine(char *messageError, int maxCharacters) {
  char *line = NULL;
  while (1) {
    line = (char *)malloc(maxCharacters + 1);
    // Lire une ligne avec fgets
    if (fgets(line, maxCharacters + 1, stdin)) {
      // Vérifier si l'entrée dépasse la limite autorisée
      if (line[strlen(line) - 1] != '\n') {
        // La ligne est trop longue, car il n'y a pas de '\n'
          printf("Erreur : votre mot est trop long. Veuillez réessayer.\n");
          clearBuffer();
      } else {
        // Retirer le '\n' de la fin
        line = strDeleteNewLine(line);
       
        // Vérifier si la ligne n'est pas égale à '\n'
        if (strLength(line) == 0) {
          printf("%s\n", messageError);
          free(line);
          clearBuffer();
          return NULL;
        }
        // Vérifier si la ligne respecte la longueur maximale
        if (strlen(line) > maxCharacters) {
          printf("Erreur : votre mot contient plus de %d caractères. Veuillez "
                 "réessayer.\n",
                 maxCharacters);
        } else {
          strDeleteNewLine(line);
          return line; // L'entrée est valide
        }
      }
    } else {
      printf("%s\n", messageError);
      free(line);
      clearBuffer();
      return NULL;
    }
  }
}

/*Function to compare two strings
 * char[] str1 = the first string to compare
 * char[] str2 = the second string to compare
 * @return bool = true if the strings are equals, false otherwise
 * */
bool strCompareString(char *str1, char *str2) {
  str1 = strDeleteNewLine(str1);
  str2 = strDeleteNewLine(str2);
  if (strLength(str1) != strLength(str2)) {
    return false;
  }
  for (int i = 0; i < strLength(str1); i++) {
    if (str1[i] != str2[i]) {
      return false;
    }
  }
  return true;
}

/*Function to find a string in a table of strings
 * char** tab = the table of strings
 * int size = the size of the table
 * char* str = the string to find
 * @return bool = true if the string is found, false otherwise
 * */
bool strFindString(char *tab[], int size, char *str) {
  str = strDeleteNewLine(str);
  for (int i = 0; i < size; i++) {
    if (strCompareString(tab[i], str)) {
      return true;
    }
  }
  return false;
}

/*Function to find a string in a table of strings
 * char** tab = the table of strings
 * char* str = the string to find
 * @return int = the number of times the string has been found, -1 otherwise
 * */
int strFindAndCountString(char *tab[], int size, char *str) {
  int count = 0;
  str = strDeleteNewLine(str);
  for (int i = 0; i < size; i++) {
    if (strCompareString(tab[i], str)) {
      count++;
    }
  }
  if (count == 0) {
    return -1;
  }
  return count;
}
