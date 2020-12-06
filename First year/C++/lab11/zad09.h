#ifndef ZAD09_H
#define ZAD09_H

#include <iostream>

using namespace std;

template <class typ_danych> class stos
{
public:
    stos();
    stos(const stos<typ_danych> &); // konstruktor kopiujacy
    ~stos();
    void wypisz();
    void dodaj(typ_danych);
    typ_danych zdejmij();
    int iloscElementow();
    void oproznij();
    bool isEmpty();
private:
    typ_danych *tablica;
    static int wielkosc_stosu;
};

template<class typ_danych> int stos<typ_danych>::wielkosc_stosu=0;


template <class typ_danych>stos<typ_danych>::stos(const stos<typ_danych> &obj) // konstruktor kopiujacy
{
    this->tablica = new typ_danych[wielkosc_stosu];
    for ( int i = 0 ; i < wielkosc_stosu ; i++)
    {
        tablica[i] = obj.tablica[i];
    }
}

template <class typ_danych>stos<typ_danych>::stos()
{
//    wielkosc_stosu++;
//    *tablica = new typ_danych[wielkosc_stosu];
}

template <class typ_danych>stos<typ_danych>::~stos()
{
    delete [] tablica;
}

template <class typ_danych> bool stos<typ_danych>::isEmpty()
{
    if ( wielkosc_stosu == 0)
        return true;
    else
        return false;
}

template <class typ_danych> void stos<typ_danych>::dodaj(typ_danych dodawany_element)
{
        if ( !isEmpty() )
        {
            stos<typ_danych> kopia = *this;
            delete [] tablica;
            wielkosc_stosu++;
            tablica = new typ_danych[wielkosc_stosu];
            for ( int i = 0 ; i < wielkosc_stosu ; i++)
            {
                this->tablica[i]=kopia.tablica[i];
            }
        }
        else
        {
            wielkosc_stosu++;
            tablica = new typ_danych[wielkosc_stosu];
        }

        tablica[wielkosc_stosu-1] = dodawany_element;
}

template <class typ_danych> typ_danych stos<typ_danych>::zdejmij()
{
        if ( wielkosc_stosu != 0 )
        {
            typ_danych tmp = tablica[wielkosc_stosu-1];
            tablica[wielkosc_stosu-1]=NULL;
            wielkosc_stosu--;
            return tmp;
        }
        else
        {
            cout << "STOS JEST PUSTY";
            return NULL;
        }
}

template <class typ_danych> void stos<typ_danych>::oproznij()
{
    int i = wielkosc_stosu-1;
    while (!isEmpty())
    {
        tablica[i]=NULL;
        wielkosc_stosu--;
        i--;
    }
}

template <class typ_danych> int stos<typ_danych>::iloscElementow()
{
    return wielkosc_stosu;
}

template <class typ_danych> void stos<typ_danych>::wypisz()
{
    for ( int i = 0 ; i < wielkosc_stosu ; i++)
    {
        cout << tablica[i]<<" ";
    }
    cout<<endl;
}

#endif // ZAD09_H
