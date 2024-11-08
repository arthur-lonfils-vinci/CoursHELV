#include <stdio.h>

int main(int argc, char **args)
{
    int x = 1;
    int y= 2;
    int t[2] = {3, 4};
    int z = 5;
    int zz = 6;

    t[3] = 100;
    printf("x = %d, y= %d, z = %d, zz = %d; t[2] = %d\n", \
        x, y, z, zz, t[2]);

}