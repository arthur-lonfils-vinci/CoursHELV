#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include "utils.h"

#define WEB_PORT 80
#define BUFFER_SIZE 4096

/**
 * PRE: serverIP : a valid IP address
 *      serverPort: a valid port number
 * POST: on success, connects a client socket to serverIP:serverPort
 *       on failure, displays error cause and quits the program
 * RES: return socket file descriptor
 */
int initSocketClient(char * serverIP, int serverPort)
{
  int sockfd = ssocket();

  sconnect(serverIP, serverPort, sockfd);

  return sockfd;
}

/**
 * Parse URL to extract hostname and path
 * PRE: url: a valid URL string
 *      hostname: buffer to store the hostname
 *      path: buffer to store the path
 * POST: hostname and path are filled with the corresponding parts of the URL
 */
void parseUrl(const char *url, char *hostname, char *path) {
  // Skip "http://" if present
  const char *urlStart = url;
  if (strncmp(urlStart, "http://", 7) == 0) {
    urlStart += 7;
  }

  // Find the first '/' which separates hostname from path
  const char *pathStart = strchr(urlStart, '/');
  if (pathStart) {
    // Copy hostname (characters before the '/')
    strncpy(hostname, urlStart, pathStart - urlStart);
    hostname[pathStart - urlStart] = '\0';
    
    // Copy path (including the '/')
    strcpy(path, pathStart);
  } else {
    // No path found, assume root path
    strcpy(hostname, urlStart);
    strcpy(path, "/");
  }
}

/**
 * Extract page name from URL to use as filename
 * PRE: url: a valid URL string
 *      pageName: buffer to store the page name
 * POST: pageName is filled with the page name from the URL
 */
void extractPageName(const char *url, char *pageName) {
  const char *lastSlash = strrchr(url, '/');
  if (lastSlash && *(lastSlash + 1) != '\0') {
    strcpy(pageName, lastSlash + 1);
  } else {
    strcpy(pageName, "index.html");
  }
}

/**
 * Find the start of HTML content (after HTTP headers)
 * PRE: buffer: HTTP response including headers
 * POST: returns pointer to the start of HTML content after headers
 */
char* findHtmlStart(char *buffer) {
  char *headersEnd = strstr(buffer, "\r\n\r\n");
  if (headersEnd) {
    return headersEnd + 4;  // Skip the "\r\n\r\n"
  }
  return buffer;  // If no headers found, return the original buffer
}

/**
 * Download a web page and save it to a file
 * PRE: url: a valid URL string
 * POST: downloads the page and saves it to a file named after the page
 */
void downloadPage(const char *url) {
  char hostname[256] = {0};
  char path[512] = {0};
  char pageName[256] = {0};
  char ipAddress[17] = {0};
  char request[1024] = {0};
  char buffer[BUFFER_SIZE] = {0};
  
  // Parse URL to get hostname and path
  parseUrl(url, hostname, path);
  
  // Get page name for the output file
  extractPageName(url, pageName);
  
  printf("Downloading: %s\n", url);
  printf("  Hostname: %s\n", hostname);
  printf("  Path: %s\n", path);
  printf("  Page name: %s\n", pageName);
  
  // Convert hostname to IP address
  hostname_to_ip(hostname, ipAddress);
  printf("  IP address: %s\n", ipAddress);
  
  // Create socket and connect to server
  int sockfd = initSocketClient(ipAddress, WEB_PORT);
  
  // Create HTTP GET request
  sprintf(request, "GET %s HTTP/1.0\r\nHost: %s\r\n\r\n", path, hostname);
  
  // Send request
  swrite(sockfd, request, strlen(request));
  
  // Create output file
  int filefd = sopen(pageName, O_WRONLY | O_CREAT | O_TRUNC, 0644);
  
  // Read response
  ssize_t bytesRead;
  bytesRead = sread(sockfd, buffer, BUFFER_SIZE - 1);
  buffer[bytesRead] = '\0';  // Ensure null-termination
  
  // Find the start of HTML content (after headers)
  char *htmlContent = findHtmlStart(buffer);
  
  // Write HTML content to file
  swrite(filefd, htmlContent, strlen(htmlContent));
  
  // Read and write remaining data if any
  while ((bytesRead = read(sockfd, buffer, BUFFER_SIZE - 1)) > 0) {
    buffer[bytesRead] = '\0';
    swrite(filefd, buffer, bytesRead);
  }
  
  // Close file and socket
  sclose(filefd);
  sclose(sockfd);
  
  printf("  Page saved to %s\n\n", pageName);
}

int main(int argc, char **argv) {
  // Open sitemap file
  int fd = sopen("sitemap.txt", O_RDONLY, 0);
  
  // Read sitemap into an array of strings
  char **urlList = readFileToTable(fd);
  sclose(fd);
  
  if (urlList == NULL) {
    fprintf(stderr, "Error reading sitemap.txt\n");
    return 1;
  }
  
  printf("Web page downloader started\n\n");
  
  // Process each URL
  for (int i = 0; urlList[i] != NULL; i++) {
    downloadPage(urlList[i]);
    free(urlList[i]);  // Free each line
  }
  
  // Free the array
  free(urlList);
  
  printf("All pages downloaded successfully!\n");
  
  return 0;
}