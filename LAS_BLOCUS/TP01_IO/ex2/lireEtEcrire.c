#include <sys/types.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <errno.h>

#include "../utils_v1.h"

#define MAX_LINE_LENGTH 80
#define BUFFER_SIZE 1024  // Read a chunk of data at a time

void readAndWriteFile(int fd_uppercase, int fd_lowercase);

int main(int argc, char *argv[]) {
    if (argc != 3) {
        fprintf(stderr, "Usage: %s <uppercase_file> <lowercase_file>\n", argv[0]);
        return 1;
    }

    char *uppercase_file = argv[1];
    char *lowercase_file = argv[2];

    // Open/Create files and empty them if they exist
    int fd_uppercase = sopen(uppercase_file, O_WRONLY | O_CREAT | O_TRUNC, 0644);
    int fd_lowercase = sopen(lowercase_file, O_WRONLY | O_CREAT | O_TRUNC, 0644);

    printf("Enter lines (max %d characters each):\n", MAX_LINE_LENGTH);

    readAndWriteFile(fd_uppercase, fd_lowercase);

    sclose(fd_uppercase);
    sclose(fd_lowercase);
    
    return 0;
}

void readAndWriteFile(int fd_uppercase, int fd_lowercase) {
    char buffer[BUFFER_SIZE];
    char line[MAX_LINE_LENGTH + 1];  // +1 for '\0'
    ssize_t bytesRead;
    int index = 0;

    while ((bytesRead = sread(STDIN_FILENO, buffer, sizeof(buffer))) > 0) {
        for (ssize_t i = 0; i < bytesRead; i++) {
            if (buffer[i] == '\n') {
                line[index] = '\0'; // Null-terminate the string

                if (index > 0 && isalpha(line[0])) {
                    if (isupper(line[0])) {
                        swrite(fd_uppercase, line, index);
                        swrite(fd_uppercase, "\n", 1);
                    } else if (islower(line[0])) {
                        swrite(fd_lowercase, line, index);
                        swrite(fd_lowercase, "\n", 1);
                    }
                }
                index = 0; // Reset for the next line
            } else {
                if (index < MAX_LINE_LENGTH) {
                    line[index++] = buffer[i];
                } else {
                    // Empty Buffer
                    fprintf(stderr, "Error: Line too long (max %d characters). Ignored.\n", MAX_LINE_LENGTH);
                    while (i < bytesRead && buffer[i] != '\n') i++;
                    index = 0;
                }
            }
        }
    }
}