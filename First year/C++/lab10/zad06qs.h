#ifndef ZAD06QS_H
#define ZAD06QS_H

template <class Typ>
Typ partition( Typ * tablica , int poczatek , int koniec)
{
    int pivot = tablica[poczatek];
    int i = poczatek;
    int j = koniec;
    int tmp;
    while (true)
    {
        while (tablica[j] > pivot)
        {
            j--;
        }
        while (tablica[i] < pivot)
        {
            i++;
        }
        if (i < j)
        {
            tmp = tablica[i];
            tablica[i] = tablica[j];
            tablica[j] = tmp;
            i++;
            j--;
        }
        else
            return j;
    }
}

template <class Typ>
Typ *sortowanie_szybkie( Typ * tablica , int poczatek , int koniec)
{
    int granica;
    if (poczatek < koniec)
    {
        granica = partition<Typ>(tablica,poczatek,koniec);
        sortowanie_szybkie<Typ>(tablica, poczatek, granica);
        sortowanie_szybkie<Typ>(tablica, granica+1, koniec);
    }
    return tablica;
}

#endif // ZAD06QS_H
