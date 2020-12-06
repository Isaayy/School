#ifndef STOS_H
#define STOS_H

#include <iostream>
#include "talerz.h"

using namespace std;

///
/// \brief Szablon klasy stos
/// Szablon klasy stos z poprzedniego zadania imituje zachowanie sie struktury stosu, umozliwia operacje dodawania usuwania etc.
template <class typ_danych> class stos
{
public:
    ///
    /// \brief Konstruktor
    ///
    stos();
    ///
    /// \brief Konstruktor kopiujacy
    ///
    stos(const stos<typ_danych> &); // konstruktor kopiujacy
    ///
    /// \brief Destruktor
    ///
    ~stos();
    ///
    /// \brief Wypisywanie elementow
    ///
    void wypisz();
    ///
    /// \brief Dodawanie elementow
    /// @param Dodawany element
    void dodaj(typ_danych);
    ///
    /// \brief Usuwanie(zdejmowanie) elementow
    ///
    typ_danych zdejmij();
    ///
    /// \brief Funkcja zwracajaca ilosc elementow
    ///
    int iloscElementow();
    ///
    /// \brief Funkcja oprozniajaca caly stos
    ///
    void oproznij();
    ///
    /// \brief Funkcja zwracajaca true jesli stos jest pusty lub false jesli nie
    ///
    bool isEmpty();
    ///
    /// \brief Stos zaimplementowany na dynamicznej tablicy
    ///
    typ_danych *tablica;
    static int iloscObiektow;
    ///
    /// \brief Stos elementow na stosie
    ///
    int iloscElementowStosu=0;
};

template<class typ_danych> int stos<typ_danych>::iloscObiektow=0;


template <class typ_danych>stos<typ_danych>::stos(const stos<typ_danych> &obj) // konstruktor kopiujacy
{
    this->tablica = new typ_danych[iloscObiektow];
    for ( int i = 0 ; i < iloscObiektow ; i++)
    {
        tablica[i] = obj.tablica[i];
    }
}

template <class typ_danych>stos<typ_danych>::stos()
{
    tablica = new typ_danych[iloscObiektow];
}

template <class typ_danych>stos<typ_danych>::~stos()
{
    delete [] tablica;
}

template <class typ_danych> bool stos<typ_danych>::isEmpty()
{
    if ( iloscElementowStosu == 0)
        return true;
    else
        return false;
}

template <class typ_danych> void stos<typ_danych>::dodaj(typ_danych dodawany_element)
{
    if (iloscElementow()<79)
    {

        if ( !isEmpty() )
        {
            stos<typ_danych> kopia = *this;
            delete [] tablica;
            iloscElementowStosu++;
            tablica = new typ_danych[iloscElementowStosu];
            for ( int i = 0 ; i < iloscElementowStosu ; i++)
            {
                this->tablica[i]=kopia.tablica[i];
            }
        }
        else
        {
            iloscElementowStosu++;
            tablica = new typ_danych[iloscElementowStosu];
        }

        this->tablica[iloscElementowStosu-1] = dodawany_element ;
    }
}

template <class typ_danych> typ_danych stos<typ_danych>::zdejmij()
{
        if ( iloscElementowStosu != 0 )
        {
            typ_danych tmp = tablica[iloscElementowStosu-1];
            tablica[iloscElementowStosu-1]=NULL;
            iloscElementowStosu--;
            return tmp;
        }
        else
        {
            return NULL;
        }
}

template <class typ_danych> void stos<typ_danych>::oproznij()
{
    int i = iloscElementowStosu-1;
    while (!isEmpty())
    {
        tablica[i]=NULL;
        iloscElementowStosu--;
        i--;
    }
}

template <class typ_danych> int stos<typ_danych>::iloscElementow()
{
    return iloscElementowStosu;
}

template <class typ_danych> void stos<typ_danych>::wypisz()
{
    if (!isEmpty())
    {
        for ( int i = 0 ; i < iloscElementow() ; i++)
        {
            cout << this->tablica[i].srednica<<" ";
        }
        cout<<endl;
    }

}

#endif // STOS_H
