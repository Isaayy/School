#include <iostream>
#include "talerz.h"
#include "stosTalerzy.h"
using namespace std;

void gra(stosTalrzy *stosA , stosTalrzy *stosC , stosTalrzy *stosB, int N )
{
    if ( N >= 0 )
    {
        gra(stosA , stosB , stosC ,N -1);
        talerz zdjetyElement = stosA->zdejmij();
        stosC->dodaj(zdjetyElement);
        gra(stosB ,stosC ,stosA , N-1);
    }
}

int main()
{

    stosTalrzy stosy[3];
    stosTalrzy stosA, stosB, stosC ;

    int N ;

    cout << "Podaj liczbe dyskow od 1 do 10 : ";
    cin >> N ;
    if (N > 0 && N <= 10)
    {
        for ( int i = N ; i>0; i--)
        {
            talerz talerzTMP(i);
            stosA.dodaj(talerzTMP);
        }
        cout<<"Elementy stosu A : ";stosA.wypisz();
        cout<<"Elementy stosu B : ";stosB.wypisz();cout<<endl;
        cout<<"Elementy stosu C: ";stosC.wypisz();cout<<endl;
        gra(&stosA,&stosC,&stosB,N-1);
        cout<<"-----------------------"<<endl;
        cout<<"Elementy stosu A : ";stosA.wypisz();cout<<endl;
        cout<<"Elementy stosu B : ";stosB.wypisz();cout<<endl;
        cout<<"Elementy stosu C: ";stosC.wypisz();cout<<endl;
    }
    else
        cout << "Podana zostala nieprawidlowa wartosc";


    return 0;
}
