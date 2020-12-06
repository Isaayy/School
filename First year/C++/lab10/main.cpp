#include <iostream>
#include <time.h>
#include <windows.h>
#include "zad06qs.h"
#include "zad06sw.h"

using namespace std;

void porownanie(int dlugosc)
{
    int *tab = new int[dlugosc];
    double *tab2 = new double[dlugosc];

    srand(time(NULL));

    for ( int i = 0 ; i < dlugosc ; i++)
    {
        tab[i]=rand()%100+1;
        tab2[i]=rand()%100+1;
    }

    clock_t start, stop;
    double czasTrwania;

    start=clock();
    tab = sortowanie_wybieranie<int>(tab);
    stop=clock();

    czasTrwania = (double)(stop-start)/CLOCKS_PER_SEC;
    cout << "Czas trwania sortowania przez wybieranie : "<<czasTrwania<<"s"<<endl;

    start=clock();
    tab2 = sortowanie_szybkie<double>(tab2,0,dlugosc-1);
    stop=clock();

    czasTrwania = (double)(stop-start)/CLOCKS_PER_SEC;
    cout << "Czas trwania sortowania szybkiego : "<<czasTrwania<<"s"<<endl;



    delete [] tab;
    delete [] tab2;
}


int main()
{
    porownanie(25000);
    return 0;
}
