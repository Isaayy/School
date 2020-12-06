#ifndef STOSTALERZY_H
#define STOSTALERZY_H

#include "stos.h"
#include "talerz.h"
///
/// \brief klasa stosTalrzy
/// Pochodna klasy stos umozliwa przechowanie stosu i typie danych talerz
class stosTalrzy : public stos <talerz>
{
public:
    ///
    /// \brief Konstruktor
    ///
    stosTalrzy();
    ///
    /// \brief Konstruktor kopiujacy
    ///
    stosTalrzy(const stosTalrzy &);
    ///
    /// \brief Zmieniona funkcja wyposujaca
    ///
    void wypisz();
    ///
    /// \brief Zmieniona funkcja dodajaca elementy
    ///
    void dodaj(talerz);
};



#endif // STOSTALERZY_H
