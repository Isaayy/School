#ifndef ZAD06SW_H
#define ZAD06SW_H

#include <iostream>
using namespace std;

template <class Typ>
Typ* sortowanie_wybieranie( Typ * tablica )
{
    int index_min=0 ;
    Typ min ;
    Typ tmp;
    bool change = false;

    for ( int i = 0 ; tablica[i]!='\0';i++)
    {
       min =tablica[i];
       for ( int k = i+1 ; tablica[k]!='\0';k++)
       {
           if(min > tablica[k])
           {
               min = tablica[k];
               index_min = k;
               change = true;
           }
       }
       if (change == true)
       {
           tmp = tablica[i];
           tablica[i]=min;
           tablica[index_min]=tmp;
           change=false;
       }

    }

    return tablica;
}

#endif // ZAD06SW_H
