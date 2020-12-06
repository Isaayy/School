#include <iostream>
#include "macierz.h"
#include <stdlib.h>
#include <time.h>

using namespace std;

double & macierz::operator()(int wiersz, int kolumna)
{
    if ( wiersz >= wiersze || wiersz <0 || kolumna >= kolumny || kolumna < 0 )
    {
        cout << "Bledny adres";
        exit(0);
    }
    else
        return macierzMain[wiersz][kolumna];
}


macierz operator + (macierz const &obj , macierz const &obj2)
{
        if (obj.wiersze == obj2.wiersze && obj.kolumny == obj2.kolumny)
        {
            double **tmpTab;
            tmpTab= new double*[obj.wiersze];
            for ( int i = 0 ; i < obj.wiersze; i++)
            {
                tmpTab[i]=new double[obj.kolumny];
            }

            for ( int i = 0 ; i < obj.wiersze ; i++)
            {
                for ( int k = 0 ; k < obj.kolumny ; k ++)
                {
                    tmpTab[i][k]=obj.macierzMain[i][k]+obj2.macierzMain[i][k];
                }
            }
            macierz nowaMacierz(tmpTab,obj.wiersze,obj.kolumny);
            return nowaMacierz;
        }
        else
        {
            exit(0);
        }
}
macierz operator - (macierz const &obj , macierz const &obj2)
{
    if (obj.wiersze == obj2.wiersze && obj.kolumny == obj2.kolumny)
    {
        double **tmpTab;
        tmpTab= new double*[obj.wiersze];
        for ( int i = 0 ; i < obj.wiersze; i++)
        {
            tmpTab[i]=new double[obj.kolumny];
        }

        for ( int i = 0 ; i < obj.wiersze ; i++)
        {
            for ( int k = 0 ; k < obj.kolumny ; k ++)
            {
                tmpTab[i][k]=obj.macierzMain[i][k]-obj2.macierzMain[i][k];
            }
        }
        macierz nowaMacierz(tmpTab,obj.wiersze,obj.kolumny);
        return nowaMacierz;
    }
    else
    {
        exit(0);
    }
}

macierz operator * (macierz const &obj ,int liczba )
{
    macierz macierzTmp = obj;
    for ( int i = 0 ; i < macierzTmp.wiersze ; i++)
    {
        for ( int k = 0 ; k < macierzTmp.kolumny ; k ++)
        {
            macierzTmp.macierzMain[i][k]=macierzTmp.macierzMain[i][k]*liczba;
        }
    }
    return macierzTmp;
}


macierz operator *(macierz const &obj , macierz const &obj2)
{
    if (obj.kolumny==obj2.wiersze)
    {
        int wierszeTmp , kolumnyTmp;
        if (obj.wiersze>obj2.wiersze)
        {
            wierszeTmp = obj.wiersze;
            kolumnyTmp = obj2.kolumny;
        }
        else
        {
            wierszeTmp = obj2.wiersze;
            kolumnyTmp = obj.kolumny;
        }
        macierz macierzTmp(wierszeTmp,kolumnyTmp);
        double suma;
        for ( int i = 0 ; i < obj.wiersze ; i++)
        {
            for ( int j = 0 ; j < obj2.kolumny ; j++)
            {
                suma = 0 ;
                for ( int k = 0 ; k < obj.kolumny ; k ++ )
                {
                    suma += obj.macierzMain[i][k] * obj2.macierzMain[k][j];

                }
                macierzTmp.macierzMain[i][j]=suma;
            }
        }
    return macierzTmp;
    }
    else
    {
        exit(0);
    }
}

ostream & operator << (ostream &out,const macierz &obj)
{
    for ( int i = 0 ; i < obj.wiersze ; i++)
    {
        for ( int k = 0 ; k < obj.kolumny ; k ++)
        {
            out << obj.macierzMain[i][k] <<" ";
        }
        out << endl;
    }
    return out;
}

istream & operator >> (istream &in,const macierz &obj)
{
    for ( int i = 0 ; i < obj.wiersze ; i++)
    {
        for ( int k = 0 ; k < obj.kolumny ; k ++)
        {
            in >> obj.macierzMain[i][k];
        }

    }
    return in;
}


macierz::macierz()
{
    alokujPamiec(5,5);
    wiersze = 5 ;
    kolumny = 5;
}

macierz::macierz(int m , int n)
{
    alokujPamiec(m,n);
    for ( int j = 0 ;j < m ; j++)
    {
        for ( int k = 0 ; k < n ; k++)
        {
            macierzMain[j][k]=1;
        }
    }
    wiersze = m ;
    kolumny = n ;
}

macierz::macierz(double** tab , int m , int n )
{

    alokujPamiec(m,n);
    for ( int i = 0 ; i<m ; i++)
    {
        for ( int j = 0 ;  j<n ; j++)
        {
            macierzMain[i][j]=tab[i][j];
        }
    }
    wiersze = m ;
    kolumny = n ;
}

macierz::~macierz()
{
    //usunPamiec();
}

void macierz::wypisz()
{
    for ( int i = 0 ; i < wiersze ; i++)
    {
        for ( int x = 0 ;  x < kolumny ; x++)
        {
            cout << macierzMain[i][x]<<" ";
        }
        cout << endl;
    }
}
void macierz::losuj()
{
    double a = 0.0;
    double b = 1.0;
    for ( int i = 0 ; i < wiersze ; i++)
    {
        for ( int k = 0 ; k < kolumny ; k ++)
        {
            macierzMain[i][k]= ((double)rand() / RAND_MAX) * (b - a) + a;
        }
    }
}


void macierz::zmienRozmiar(int newN, int newM)
{
    macierz kopia(newN,newM) ;
    for ( int i = 0 ; i < wiersze ; i++)
    {
        for ( int k = 0 ; k < kolumny ; k ++)
        {
            kopia.macierzMain[i][k]=macierzMain[i][k];
        }
    }
    usunPamiec();
    wiersze = newN;
    kolumny = newM;
    alokujPamiec(newN,newM);
    for ( int i = 0 ; i < newN ; i++)
    {
        for ( int k = 0 ; k < newM ; k ++)
        {
            macierzMain[i][k]=kopia.macierzMain[i][k];
        }
    }
}

int macierz::ileKolumn()
{
    return kolumny;
}

int macierz::ileWierszy()
{
    return wiersze;
}

void macierz::alokujPamiec(int n, int m)
{
    macierzMain= new double*[n];
    for ( int i = 0 ; i < n; i++)
    {
        macierzMain[i]=new double[m];
    }
}

void macierz::usunPamiec()
{
    for(int i = 0; i <wiersze; ++i) {
        delete[] macierzMain[i];
    }
    delete[] macierzMain;
    macierzMain = NULL ;
}



