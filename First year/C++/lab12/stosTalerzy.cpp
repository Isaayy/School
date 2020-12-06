#include "stosTalerzy.h"

stosTalrzy::stosTalrzy():stos()
{
}


stosTalrzy::stosTalrzy(const stosTalrzy &obj)
{
    {
        this->tablica = new talerz[iloscObiektow];
        for ( int i = 0 ; i < iloscObiektow ; i++)
        {
            tablica[i].srednica = obj.tablica[i].srednica;
        }
    }
}



void stosTalrzy::wypisz()
{
    if (!isEmpty())
    {
        for ( int i = 0 ; i <iloscElementow() ; i++)
        {
            cout << tablica[i].srednica<<" ";
        }
        cout<<endl;
    }
}

void stosTalrzy::dodaj(talerz dodawany_element)
{
    if (iloscElementow()<79)
    {

        if ( !isEmpty() )
        {
            stosTalrzy tmp;
            for ( int i = 0 ; i < iloscElementow() ; i++)
            {
                tmp.tablica[i].srednica=tablica[i].srednica;
            }
            delete [] this->tablica;
            iloscElementowStosu++;
            this->tablica = new talerz[iloscElementowStosu];

            for ( int i = 0 ; i < this->iloscElementow(); i++)
            {
                this->tablica[i].srednica=tmp.tablica[i].srednica;
            }
        }
        else
        {
            iloscElementowStosu++;
            tablica = new talerz[iloscElementowStosu];
        }
        this->tablica[iloscElementow()-1].srednica = dodawany_element.srednica ;
    }
}
