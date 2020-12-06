#ifndef TALERZ_H
#define TALERZ_H
///
/// \brief klasa talerz
/// Umozliwia przechowanie obiektu(talerza) o podanej srednicy
class talerz
{
public:
    ///
    /// \brief Konstruktor domyslny
    /// Ustawia srednice na 0
    talerz();
    ///
    /// \brief Konstruktor z podana srednica
    /// @param podana srednica
    talerz(double);
    ///
    /// \brief Konstruktor kopiujacy
    /// @param podany obiekt
    talerz(const talerz&);
    ///
    /// \brief Srednica obiektu
    /// funkcja zwraca srednice obiektu
    double jakaSrednica(void);
    ///
    /// \brief Dodaj srednice
    /// funkcja dodaje srednice do talerza
    void dodajSrednice(double);
    ///
    /// \brief srednice
    ///
    double srednica;
};



#endif // TALERZ_H
