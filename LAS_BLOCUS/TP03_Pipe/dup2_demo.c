#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>

void demonstrate_dup2() {
    printf("=== Understanding dup2() ===\n\n");
    
    // Create a file to redirect output to
    int file_fd = open("dup2_demo.txt", O_CREAT | O_WRONLY | O_TRUNC, 0644);
    if (file_fd == -1) {
        perror("open");
        exit(1);
    }
    
    printf("1. Before dup2(): This goes to terminal (stdout)\n");
    
    // Show current file descriptor numbers
    printf("   - STDOUT_FILENO = %d\n", STDOUT_FILENO);
    printf("   - file_fd = %d\n", file_fd);
    
    // Save original stdout for later restoration
    int saved_stdout = dup(STDOUT_FILENO);
    
    printf("2. Calling dup2(file_fd, STDOUT_FILENO)...\n");
    
    // This is the magic! Redirect stdout to the file
    if (dup2(file_fd, STDOUT_FILENO) == -1) {
        perror("dup2");
        exit(1);
    }
    
    // This will go to the file, not the terminal!
    printf("3. After dup2(): This goes to the FILE, not terminal!\n");
    printf("   - STDOUT_FILENO (%d) now points to the same place as file_fd (%d)\n", 
           STDOUT_FILENO, file_fd);
    printf("   - Any printf() now writes to the file\n");
    
    // Close the original file descriptor (stdout still works via fd 1)
    close(file_fd);
    printf("4. Even after close(file_fd), stdout still works because fd 1 points to file\n");
    
    // Restore original stdout
    dup2(saved_stdout, STDOUT_FILENO);
    close(saved_stdout);
    
    // This goes back to terminal
    printf("5. Back to terminal: stdout restored!\n");
    printf("   Check the file 'dup2_demo.txt' to see what was written there.\n");
}

int main() {
    demonstrate_dup2();
    return 0;
}
