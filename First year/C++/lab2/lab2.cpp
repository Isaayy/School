#include <iostream>

using namespace std;

const int TABSIZE = 25 ;

class tabliceSchowek
{
public :
    int set(int* , int);
    int get(int);
    void wypisz()
    {
        for ( int i = 0 ; i < dlg ; i++)
        {
            cout << tab[i]<<" ";
        }
    }
    static int ile();
    tabliceSchowek(int*,int);
    ~tabliceSchowek();

private:
    int tab[TABSIZE];
    int dlg = 0;
    static int ileObiektow ;
};



int tabliceSchowek::set(int *tablica , int dlugosc)
{
    int i = 0 ;
    if ( (dlugosc+dlg) <= TABSIZE)
    {
        while (dlugosc--)
        {
            tab[dlg]=tablica[i];
            dlg++;
            i++;
        }
    }
    else
    {
       cout << "Brak miejsca w tablicy "<<endl;
    }
}

int tabliceSchowek::get(int indeks)
{
        if (tab[indeks]!='\0')
            return tab[indeks];
        else
            return NULL;
}

int tabliceSchowek::ileObiektow=0;

tabliceSchowek::tabliceSchowek(int *tab, int length)
{
    set(tab,length);
    ileObiektow++;
}

tabliceSchowek::~tabliceSchowek()
{
    ileObiektow--;
}
int tabliceSchowek::ile()
{
    return  ileObiektow;
}

int main()
{
    const int size = 5 ;
    const int size2 = 10;
    int tab[size]={1,2,3,4,5};
    int tab2[size2]={1,2,3,4,5,6,7,8,9,10};

    tabliceSchowek obiekt1(tab,size);
    obiekt1.wypisz();
    int indeksW = obiekt1.get(4);
    if (indeksW == NULL)
    {
        cout << "Pod tym indeksem tablica nie przechowuje zadnej wartosci"<<endl;
    }
    else
        cout << "Wartosc pod indeksem :"<<indeksW<<endl;
    tabliceSchowek obiekt2(tab2,size2);
    obiekt2.~tabliceSchowek();
    tabliceSchowek obiekt3(tab2,size2);

    obiekt3.wypisz();


    return 0;
}
