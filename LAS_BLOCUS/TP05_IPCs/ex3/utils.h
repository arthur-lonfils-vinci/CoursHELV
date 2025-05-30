#ifndef _UTILS_H_
#define _UTILS_H_

/// Module à compiler avec les feature test macros suivantes, selon la machine:
/// (cf. man 7 feature_test_macros)
///
///    -D_DEFAULT_SOURCE
///
/// OU si _DEFAULT-SOURCE n'est pas définie:
///
///    -D_BSD_SOURCE -D_SVID_SOURCE -D_POSIX_C_SOURCE=200809

#include <stdio.h>
#include <stdbool.h>
#include <signal.h>
#include <sys/ipc.h>
#include <poll.h>
#include <sys/types.h>
#include <sys/signal.h>
#include <sys/stat.h>
#include <bits/types/sigset_t.h>


//******************************************************//
// KEYBOARD INPUT
//******************************************************//

/** 
 * Reads a line from standard input and copies it into the buffer s
 * PRE: s: char array of size sz
 *      sz: an integer > 2
 * POST: a string of at most sz-2 characters 
 *       has been read from stdin and placed in s (without '\n')
 * RES: returns the number of characters in the string s;
 *      -1 in case of error or EOF
 */
int readLimitedLine (char* s, int sz);

/**
 * Returns a line read from standard input
 * RES: returns a line of characters of any length read from stdin (without '\n');
 *      NULL in case of error or EOF; the string has been dynamically allocated,
 *      so it must be freed
 */
char* readLine ();


//******************************************************//
// DISPLAYING COLORED MESSAGES
//******************************************************//

enum {BLACK_TEXT = 30,
      RED_TEXT,
      GREEN_TEXT,
      YELLOW_TEXT,
      BLUE_TEXT,
      PURPLE_TEXT,
      CYAN_TEXT,
      WHITE_TEXT};

#define colorOn(mode,textColor) printf("\033[%d;%dm",mode,textColor)   // mode: 0 normal ; 1 bold
#define colorOff() printf("\033[0m");  // text color reset

void printOk (char* s);
void printError (char* s);
void printColor (char* format, char* s, int color);


//******************************************************//
// DATE AND TIME
//******************************************************//

/**
 * RES: a string representing system time and date
 *      (format: "Wed Jun 30 21:49:08 1993")
 */
char* getTime ();


//******************************************************//
// RANDOM INTEGER
//******************************************************//

/**
 * PRE: valMin, vamMax: integer values, with valMin <= valMax
 * RES: a pseudo-random integer value in the range valMin to valMax inclusive
 *      (i.e. the mathematical range [valMin, valMax]).
 */
int randomIntBetween (int valMin, int valMax);


//***************************************************************************//
// Safe SYSCALL functions: Note to the readers of the specifiactions.
//***************************************************************************//
// Given a function "type f (arg1, arg2, ..., argn)", a safe function of "f" is 
// a function "sf" which has the same signature and the same specifiction except
// that it is abruptly stopped when an error occurs. Such an error could occur
// when an system call is made. Usually, we do not add this notice to the 
// specifications when a a function is characterized as a safe function.
// Nevertheless, its specification is implicitly defined.
//***************************************************************************//


//***************************************************************************//
// MALLOC
//***************************************************************************//

// NOTE: This is a safe version of the "malloc" function
void *smalloc(size_t size);


//***************************************************************************//
// CHECK
//***************************************************************************//

/**
 * PRE: msg != NULL
 * POST: 1) If the value of "cond" is true, "msg" followed by a message 
 *      describing the last error encountered during a call 
 *      to a system or library function is produced 
 *      on stderr. Moreover, the program is abruptly terminied.
 *      2) If the value of  "cond" is false, nothing happens.
 */
void checkCond(bool cond, char* msg);

/** 
 * PRE: msg != NULL
 * POST: 1) If the value of "cond" is less than 0, "msg" followed by a message 
 *      describing the last error encountered during a call 
 *      to a system or library function is produced 
 *      on stderr. Moreover, the program is abruptly terminied.
 *      2) If the value of  "cond" is greater or egal to 0, nothing happens.
 */
void checkNeg(int res, char* msg);

/** 
 * PRE: msg != NULL
 * POST: 1) If the value of "res" is NULL, "msg" followed by a message 
 *      describing the last error encountered during a call 
 *      to a system or library function is produced 
 *      on stderr. Moreover, the program is abruptly terminied.
 *      2) If the value of  "res" is not NULL, nothing happens.
 */
void checkNull(void* res, char* msg);


//***************************************************************************//
// IO SYSCALLS
//***************************************************************************//

// NOTE: This is a safe version of the "open" system call
int sopen(const char *pathname, int flags, mode_t mode);

// NOTE: This is a safe version of the "close" system call
int sclose(int fd);

// NOTE: This is a safe version of the "read" system call
ssize_t sread(int fd, void *buf, size_t count);

// NOTE: This is a safe version of the "write" system call
ssize_t swrite(int fd, const void *buf, size_t count);

/** 
 * PRE "fd" is a valid file desciptor on which something can be written, and
 *      "counf" >= 0, and 
 *     "buf" points to a memory segment such that the size of this 
 *      memory segment is at least "count" bytes.

 * POST Exactly "count" bytes from the buffer pointed by "buf" to the file referred 
 *     to the file descriptor "fd" is written.
 */
void nwrite(int fd, const void* buf, size_t count);

/** 
 * Reads a file line by line and stores it in an array
 * PRE: fd: is a file descriptor for a file opened in read mode
 * POST: a dynamically allocated array containing one line per index
 *       has been created. The lines are also dynamically allocated.
 *       The end-of-line characters '\n' have been replaced by '\0'.
 * RES: an array terminated by a NULL string ; in case of an error, NULL is returned
 * Note: It will be necessary to free each line of the array and the array itself
 *       in the calling program
 */
char **readFileToTable(int fd);


//***************************************************************************//
// FORK SYSCALL
//***************************************************************************//

// NOTE: This is a safe version of the "close" system call
pid_t sfork();

/** 
 * POST: A new child process is created by means of the "fork" function.
 *       Firstly, it executes the <run> then it exits. 
 *       The calling process and the child process run concurrently. 
 *       If an error occurs, the calling process is abruptly terminated. 
 * RES:  The pid of the chid process
 */
pid_t fork_and_run0(void (*run)(void));


/** 
 * POST: A new child process is created by means of the "fork" function.
 *       Firstly, it executes the <run> function with <arg0> as a parameter
 *       then it exits. The calling process and the child process run 
 *       concurrently. 
 *       If an error occurs, the calling process is abruptly terminated. 
 * RES:  The pid of the chid process
 */
pid_t fork_and_run1(void (*run)(void *), void* arg0);

/** 
 * POST: A new child process is created by means of the "fork" function.
 *       Firstly, it executes the <run> function with <arg0> and <arg1> 
 *       as parameterS then it exits. 
 *       The calling process and the child process run concurrently. 
 *       If an error occurs, the calling process is abruptly terminated. 
 * RES: The pid of the chid process
 */
pid_t fork_and_run2(void (*run)(void *, void *), void* arg0, void* arg1);

/** 
 * POST: A new child process is created by means of the "fork" function.
 *       Firstly, it executes the <run> function with <arg0>, <arg1> and <arg2> 
 *       as parameters then it exits. 
 *       The calling process and the child process runs concurrently. 
 *       If an error occurs, the calling process is abruptly terminated. 
 * RES: The pid of the chid process
 */
pid_t fork_and_run3(void (*run)(void*, void*, void*), void *arg0, void *arg1, void* arg2);

// NOTE: This is a safe version of the "waitpid" system call
pid_t swaitpid(pid_t pid, int *wstatus, int options);

// NOTE: This is a safe version of the "wait" system call
pid_t swait(int *wstatus);

// NOTE: This is a safe version of the "execl" system call
int sexecl(const char *path, const char *arg, ...);


//***************************************************************************//
// PIPE SYSCALL
//***************************************************************************//

// NOTE: This is a safe version of the "pipe" system call
int spipe(int pipefd[2]);


//***************************************************************************//
// SIGNAL SYSCALLS
//***************************************************************************//

/** 
 * POST: This function do nothing.
 */
void ehandler(int signum);

/** 
 * PRE:  signum represents a signal number.
 * POST: The action taken by a process on receipt of the <signum> signal
 *       is "handler". The <signum> signal mask includes all signals. 
 *       Note that no specific options are set.
 */
void ssigaction(int signum, void (*handler)(int));

// NOTE: This is a safe version of the "sigemptyset" system call
void ssigemptyset(sigset_t *set);

// NOTE: This is a safe version of the "sigfillset" system call
void ssigfillset(sigset_t *set);

// NOTE: This is a safe version of the "sigaddset" system call
void ssigaddset(sigset_t *set, int signum);

// NOTE: This is a safe version of the "sigdelset" system call
void ssigdelset(sigset_t *set, int signum);

// NOTE: This is a safe version of the "sigprocmask" system call
void ssigprocmask(int how, const sigset_t *set, sigset_t *oldset);

// NOTE: This is a safe version of the "kill" system call
void skill(pid_t pid, int signum);

// NOTE: This is a safe version of the "sigpending" system call
void ssigpending(sigset_t *set);


//***************************************************************************//
// SHARED MEMORY SYSCALLS
//***************************************************************************//

// NOTE: This is a safe version of the "shmget" system call
int sshmget(key_t key, size_t size, int shmflg);

// NOTE: This is a safe version of the "shmat" system call
void* sshmat(int shm_id);

// NOTE: This is a safe version of the "shmdt" system call
void sshmdt(const void *shmaddr);

// NOTE: This is a safe version of the "shmctl" system call with IPC_RMID command
void sshmdelete(int shm_id);


//***************************************************************************//
// SEMAPHORES SYSCALLS
//***************************************************************************//

//cf man semctl
// This function has three or four arguments, depending on cmd.  
// When there are four, the fourth has the type union semun.  
// The  calling program must define this union as follows:
union semun {
   int              val;    /* Value for SETVAL */
   struct semid_ds *buf;    /* Buffer for IPC_STAT, IPC_SET */
   unsigned short  *array;  /* Array for GETALL, SETALL */
   struct seminfo  *__buf;  /* Buffer for IPC_INFO (Linux-specific) */
};

/** 
 * PRE:  key: semaphore identification key
 *       nsems: number of semaphores to create
 *       perm: permissions to access semaphores
 *       val: value to initialize semaphores
 * POST: creates a new set of semaphores associated with key,
 *      with permissions perm, initializes their values to val 
 *      and returns the semaphore set identifier.
 */
int sem_create(key_t key, int nsems, int perm, int val);

/** 
 * PRE:  key: identification key of an existing set of semaphores
 *      nsems: number of semaphores 
 * POST: returns the semaphore set identifier associated with key
 */
int sem_get(key_t key, int nsems);

/** 
 * PRE:  sem_id: identification number of a set of semaphores
 *       sem_num: semaphore number
 * POST: decrements the sem_num'th semaphore of the set
 */
void sem_down(int sem_id, int sem_num);

// idem sem_down() with sem_num = 0
void sem_down0(int sem_id);

/** 
 * PRE:  sem_id: identification number of a set of semaphores
 *       sem_num: semaphore number
 * POST: increments the sem_num'th semaphore of the set
 */
void sem_up(int sem_id, int sem_num);

// idem sem_up() with sem_num = 0
void sem_up0(int sem_id);

/** 
 * PRE:  sem_id: identification number of a set of semaphores
 * POST: removes the semaphore set
 */
void sem_delete(int sem_id);


//***************************************************************************//
// SOCKETS SYSCALLS
//***************************************************************************//

/** 
 * PRE : None 
 * POST: Create a socket an IPv4 socket with two communication
 *       of type SOCK_STREAM. 
 *       Returns the file descriptor for the socket
 */
int ssocket();

/** 
 * PRE : serverIP   : the server IP address 
 *       serverPort : the port for the socket
 *       sockfd   : socket file descriptor sockfd successufully created with ssocket.
 * POST : on success connect a socket to the specified address and port 
 *        on failure, displays the error cause on stderr and exit
 */
int sconnect(char *serverIP,int serverPort, int sockfd );

/** 
 * PRE : serverPort : the port for the socket
 *       sockfd   : socket file descriptor sockfd successufully created with ssocket.
 * POST : bind the socket to the specified port on all network interface of the server
 *        on failure, displays the error cause on stderr and exit
 */
int sbind(int port, int sockfd);

/** 
 * PRE : sockfd : a valid socket file descriptor 
 *       backlog: the maximum length to which the queue of pending connections for sockfd may grow.
 * POST: on success marks  the  socket referred to by sockfd as a passive socket,
 *       that is, as a socket that will be used to accept incoming connection requests using accept
 *       on failure, displays the error cause on stderr and exit
 */
int slisten(int sockfd, int backlog);

/** 
 * PRE: sockfd: a valid passive (listen) socket is a socket that has been created with socket(2),
 *      bound to a local address with bind(2), and is listening for connections after a listen(2).
 * POST: on success  extracts the first connection request on the queue of pending connections for
 *       the listening socket, sockfd,creates a new connected socket.
 *       Returns a nonnegative integer that is a file descriptor for the accepted socket.
 */
int saccept(int sockfd);

/** 
 * Convert a DNS domain name to IP v4 
 * Necessary for socket connection because IP required 
 * PRE: hostname: hostname string
 *      ip: a char buffer of at least 17 characters (16 + \0)
 * POST: if succeed, ip contains the ip address of the hostname
 *       exits with 1 on failure.
 */
void hostname_to_ip (char * hostname, char* ip);

/** 
 * PRE: fds     : array of structures representing polled file descriptors (see man 2 poll)
 *      nfds_t  : number of entries in fds array.
 *      timeout : timeout in specifies the number of milliseconds that poll() should  block  
 *      waiting for a file descriptor to become ready
 * POST: If none of the events requested (and no error) has occurred for any of the file descriptors,
 *       then poll() blocks until one of the events occurs.
 *       The call will block until either:
 *       *  a file descriptor becomes ready;
 *       *  the call is interrupted by a signal handler; or
 *       *  the timeout expires.
 *       Specifying  a  negative value in timeout means an infinite timeout.  
 *       Specifying a timeout of zero   causes poll() to return immediately, 
 *       even if no file descriptors are ready.
 *       On  success, a positive number is returned; this is the number of structures which have non
 *       zero revents fields (in other words, those descriptors with events or errors  reported).
 *       A value  of 0 indicates that the call timed out and no file descriptors were ready.
 */
int spoll(struct pollfd *fds, nfds_t nfds, int timeout);

#endif  // _UTILS_H_
