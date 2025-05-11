#!/bin/bash

##########################################################################
# lastrace.sh
# anthony.legrand
# 26 février 2024
#
#   Trace les appels système et les signaux du programme passé en argument
#
# Usage: lastrace.sh executable [args] [-h regex]
# executable = chemin d'un fichier exécutable (avec ou sans ./)
# args = arguments du fichier exécutable
# regex = expresssion régulière pour afficher en rouge les caractères 
#         correspondants de l'output de strace
#
# Pour que ce script soit accessible depuis n'importe quel répertoire,
# créez un répertoire local caché dans votre home directory (p.ex: ~/.local/bin);
# copiez votre script dans ce répertoire et ajoutez ce répertoire à la fin 
# du PATH en écrivant la commande suivante dans votre fichier ~/.bashrc:
#     export PATH=$PATH:~/.local/bin
#
##########################################################################

SYSCALLS="read,write,openat,close,clone,execve,wait4,waitpid,pipe,kill,alarm,sigaction,sigsuspend,sigprocmask,shmget,semget,socket,poll"
REGEX='^|\bread\b|write|open|close|clone|exec|wait4|waitpid|pipe|kill|alarm|sigaction|sigsuspend|sigprocmask|shmget|semget|socket|poll'
FILENAME=.lastrace.out

if [ $# -eq 0 ]; then
    echo $0 Error: executable file should be provided >&2
    exit 1
fi

if [ ! -x "$1" ]; then
    echo $0 Error: $1 is not an executable file >&2
    exit 1
fi

prog="$1"
if [[ ! "$1" =~ ^(\./|/) ]]; then
    prog="./"$1
fi

i=1
args=($*)
str=""
while [ $i -lt $# ]; do
    if [ ${args[i]} = "-h" ] && [ -n ${args[i+1]} ]; then
        REGEX="$REGEX|${args[i+1]}"
        ((i+=2))
    else
        str=$str" ${args[i]}"
        ((i++))
    fi
done

# Execute program $prog and trace syscalls
strace -f -o $FILENAME -e trace=$SYSCALLS "$prog" $str

# Print tracing results
YELLOW='\033[1;36m'
NC='\033[0m' # No Color
echo -e "\n${YELLOW}Traced syscalls and signals on $prog:${NC}\n"

cat $FILENAME | awk '{$1="[PID="$1"]"}1' | egrep --color -E $REGEX
echo

rm $FILENAME
