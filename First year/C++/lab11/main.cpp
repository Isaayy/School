#include <iostream>
#include "zad09.h"

using namespace std;

void dzialanie()
{
    stos<char> stosZnakow;
    stosZnakow.dodaj('a');
    stosZnakow.dodaj('b');
    stosZnakow.dodaj('c');
    stosZnakow.dodaj('d');
    cout << "Stos znakow "<<endl;
    cout << "--------------- "<<endl;
    cout<<"Ilosc elementow : "<<stosZnakow.iloscElementow()<<endl;
    cout<<"Elementy : ";stosZnakow.wypisz();cout<<endl;
    stosZnakow.zdejmij();
    cout<<"Ilosc elementow po zdjeciu : "<<stosZnakow.iloscElementow()<<endl;
    cout<<"Elementy : ";stosZnakow.wypisz();cout<<endl;
    stosZnakow.oproznij();


    stos<int> stosIntow;
    stosIntow.dodaj(1);
    stosIntow.dodaj(2);
    stosIntow.dodaj(3);
    stosIntow.dodaj(4);
    cout << "Stos intow "<<endl;
    cout << "--------------- "<<endl;
    cout<<"Ilosc elementow : "<<stosIntow.iloscElementow()<<endl;
    cout<<"Elementy : ";stosIntow.wypisz();cout<<endl;
    stosIntow.zdejmij();
    cout<<"Ilosc elementow po zdjeciu : "<<stosIntow.iloscElementow()<<endl;
    cout<<"Elementy : ";stosIntow.wypisz();cout<<endl;
    stosIntow.oproznij();

    stos<char*> stos1;
    stos1.dodaj("ciag1");
    stos1.dodaj("ciag2");
    stos1.dodaj("ciag3");
    stos1.dodaj("ciag4");
    cout << "Stos ciagow"<<endl;
    cout << "--------------- "<<endl;
    cout<<"Ilosc elementow : "<<stos1.iloscElementow()<<endl;
    cout<<"Elementy : ";stos1.wypisz();cout<<endl;
    stos1.zdejmij();
    cout<<"Ilosc elementow po zdjeciu : "<<stos1.iloscElementow()<<endl;
    cout<<"Elementy : ";stos1.wypisz();cout<<endl;
    stos1.oproznij();
}



int main()
{
    dzialanie();
    return 0;
}
