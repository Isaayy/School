#ifndef MACIERZ_H
#define MACIERZ_H

#include <iostream>
using namespace std;

class macierz
{
    friend macierz operator + (macierz const & ,macierz const &);
    friend macierz operator - (macierz const & ,macierz const &);
    friend macierz operator * (macierz const& ,int);
    friend macierz operator * (macierz const & ,macierz const &);
    friend ostream& operator << (ostream&,const macierz&);
    friend istream& operator >> (istream&,const macierz&);

public :
    macierz operator = (macierz const &obj)
    {
       if (this!=&obj)
       {
           if ( this->wiersze == obj.wiersze && this->kolumny == obj.kolumny)
           {
               for ( int i = 0 ; i < obj.wiersze ; i++)
               {
                   for ( int k = 0 ; k <obj.kolumny ; k ++)
                   {
                       this->macierzMain[i][k]=obj.macierzMain[i][k];
                   }
               }
           }
       }
       return *this;
    }

    macierz operator + (int liczba)
    {
        for ( int i = 0 ; i < this->wiersze ; i++)
        {
            for ( int k = 0 ; k < this->kolumny ; k ++)
            {
                this->macierzMain[i][k] = this->macierzMain[i][k] + liczba;
            }
        }
        return *this;
    }

    macierz operator - (int liczba)
    {
        for ( int i = 0 ; i < this->wiersze ; i++)
        {
            for ( int k = 0 ; k < this->kolumny ; k ++)
            {
                this->macierzMain[i][k] = this->macierzMain[i][k] - liczba;
            }
        }
        return *this;
    }

    macierz operator +=(macierz const&obj)
    {
        if ( this->wiersze == obj.wiersze && this->kolumny == obj.kolumny)
        {
            for ( int i = 0 ; i < obj.wiersze ; i++)
            {
                for ( int k = 0 ; k <obj.kolumny ; k ++)
                {
                    this->macierzMain[i][k]=this->macierzMain[i][k] + obj.macierzMain[i][k];
                }
            }
        return *this;
        }
    }

    macierz operator -=(macierz const&obj)
    {
        if ( this->wiersze == obj.wiersze && this->kolumny == obj.kolumny)
        {
            for ( int i = 0 ; i < obj.wiersze ; i++)
            {
                for ( int k = 0 ; k <obj.kolumny ; k ++)
                {
                    this->macierzMain[i][k]=this->macierzMain[i][k] - obj.macierzMain[i][k];
                }
            }
        }
        return *this;
    }

    macierz operator *=(int liczba)
    {
        for ( int i = 0 ; i < this->wiersze ; i++)
        {
            for ( int k = 0 ; k <this->kolumny ; k ++)
            {
                this->macierzMain[i][k]=this->macierzMain[i][k] * liczba ;
            }
        }
        return *this;
    }

    macierz operator *= ( macierz const &obj)
    {
        macierz tmp = obj;
        if (this->kolumny==obj.wiersze)
        {
            int wierszeTmp , kolumnyTmp;
            if (this->wiersze>obj.wiersze)
            {
                wierszeTmp = this->wiersze;
                kolumnyTmp = obj.kolumny;
            }
            else
            {
                wierszeTmp = obj.wiersze;
                kolumnyTmp = this->kolumny;
            }
            macierz macierzTmp(wierszeTmp,kolumnyTmp);
            double suma;
            for ( int i = 0 ; i < this->wiersze ; i++)
            {
                for ( int j = 0 ; j < obj.kolumny ; j++)
                {
                    suma = 0 ;
                    for ( int k = 0 ; k < this->kolumny ; k ++ )
                    {
                        suma += this->macierzMain[i][k] * obj.macierzMain[k][j];

                    }
                    macierzTmp.macierzMain[i][j]=suma;
                }
            }
            for ( int i = 0 ; i < this->wiersze ; i++)
            {
                for ( int k = 0 ; k < this->kolumny ; k ++)
                {
                   this->macierzMain[i][k] = this->macierzMain[i][k]+macierzTmp.macierzMain[i][k];
                }
            }

            return *this;
        }
    }

    bool operator == (macierz const &obj)
    {
        if (this->wiersze == obj.wiersze && this->kolumny == obj.kolumny)
        {
            int rowne = 0 ;
            for ( int i = 0 ; i < this->wiersze ; i++)
            {
                for ( int k = 0 ; k < this->kolumny ; k ++)
                {
                    if (this->macierzMain[i][k] == obj.macierzMain[i][k])
                    {
                        rowne++;
                    }
                }
            }
            int ilosc = this->wiersze * this->kolumny;
            if (rowne == ilosc)
            {
                return true;
            }
            else
                return false;
        }
        else
            return false;

    }

    bool operator != (macierz const &obj)
    {
        if (this->wiersze == obj.wiersze && this->kolumny == obj.kolumny)
        {
            int rowne = 0 ;
            for ( int i = 0 ; i < this->wiersze ; i++)
            {
                for ( int k = 0 ; k < this->kolumny ; k ++)
                {
                    if (this->macierzMain[i][k] == obj.macierzMain[i][k])
                    {
                        rowne++;
                    }
                }
            }
            int ilosc = this->wiersze * this->kolumny;
            if (rowne == ilosc)
            {
                return false;
            }
            else
                return true;
        }
        else
            return true;
    }
    double& operator()(int  , int );

    macierz();
    macierz(int , int);
    macierz(double** , int , int );
    ~macierz();

    void zmienRozmiar(int , int);
    int ileKolumn();
    int ileWierszy();
    void losuj();
    void wypisz();
    void alokujPamiec(int , int);
    void usunPamiec(void);

private:
    double **macierzMain;
    int wiersze , kolumny  ;
};



#endif // MACIERZ_H
