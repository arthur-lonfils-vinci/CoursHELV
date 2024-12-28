#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define ID_LENGTH 5
#define MAX_LINE 255

typedef struct {
    char **safes;     // Dynamic array of strings
    int logical_size;  // Number of safes currently stored
    int physical_size; // Actual allocated size
} SafeTable;

// Initialize the safe table with initial capacity
void initSafeTable(SafeTable *table, int initial_capacity) {
    table->safes = (char**)malloc(initial_capacity * sizeof(char*));
    table->logical_size = 0;
    table->physical_size = initial_capacity;
}

// Free the memory used by the safe table
void freeSafeTable(SafeTable *table) {
    for (int i = 0; i < table->logical_size; i++) {
        free(table->safes[i]);
    }
    free(table->safes);
}

// Remove newline character from string
void stripNewline(char *str) {
    int len = strlen(str);
    if (len > 0 && str[len-1] == '\n') {
        str[len-1] = '\0';
    }
}

// Double the capacity of the safe table
void expandSafeTable(SafeTable *table) {
    int new_size = table->physical_size * 2;
    char **new_safes = (char**)realloc(table->safes, new_size * sizeof(char*));
    if (new_safes == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        exit(1);
    }
    table->safes = new_safes;
    table->physical_size = new_size;
}

// Find a safe by its ID
int findSafe(SafeTable *table, const char *id) {
    for (int i = 0; i < table->logical_size; i++) {
        if (strncmp(table->safes[i], id, ID_LENGTH) == 0) {
            return i;
        }
    }
    return -1;
}

// Add a new safe or update existing one
void addOrUpdateSafe(SafeTable *table, char *line) {
    char id[ID_LENGTH + 1];
    strncpy(id, line, ID_LENGTH);
    id[ID_LENGTH] = '\0';

    int index = findSafe(table, id);
    
    if (index == -1) { // New safe
        if (table->logical_size >= table->physical_size) {
            expandSafeTable(table);
        }
        
        table->safes[table->logical_size] = strdup(line);
        if (table->safes[table->logical_size] == NULL) {
            fprintf(stderr, "Memory allocation failed\n");
            exit(1);
        }
        table->logical_size++;
    } else { // Existing safe
        char *content = line + ID_LENGTH; // Skip ID
        char *new_safe = (char*)realloc(table->safes[index], 
                                      strlen(table->safes[index]) + strlen(content) + 1);
        if (new_safe == NULL) {
            fprintf(stderr, "Memory allocation failed\n");
            exit(1);
        }
        table->safes[index] = new_safe;
        strcat(table->safes[index], content);
    }
}

int main() {
    SafeTable table;
    initSafeTable(&table, 10);
    char line[MAX_LINE];

    while (fgets(line, MAX_LINE, stdin) != NULL) {
        stripNewline(line);
        addOrUpdateSafe(&table, line);
    }

    // Print all safes and their contents
    for (int i = 0; i < table.logical_size; i++) {
        printf("%s\n", table.safes[i]);
    }

    freeSafeTable(&table);
    return 0;
}
