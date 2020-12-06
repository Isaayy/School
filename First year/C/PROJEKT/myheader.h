#ifndef MYHEADER_H
#define MYHEADER_H

#define max(a,b)((a>b)?a:b)

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <windows.h>

int recordSesji ;
void losujLiczbe(int zakres,int *liczba);
int dlugosc(char tab[]);
struct wyrazy;
void instrukcje(void);
void game(void);
void menu(void);


#endif // MYHEADER_H
