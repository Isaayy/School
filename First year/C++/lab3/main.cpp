#include <iostream>
#include "macierz.h"

using namespace std;

int main()
{
    macierz macierz1(5,5);
    macierz macierz2(5,5);
    cout << macierz2;
    cout << "Przypisanie macierzy :"<<endl;
    macierz macierz3=macierz2;
    cout << macierz3;

    cout << "Dodawanie macierzy :"<<endl;
    macierz macierz4 = macierz3+macierz2;
    cout << macierz4;

    cout << "Dodawanie liczby :"<<endl;
    macierz4 + 3;
    cout << macierz4;

    cout << "Odejmowanie macierzy :"<<endl;
    macierz macierz5 = macierz4 - macierz3;
    cout << macierz5;

    cout << "Odejmowanie liczby :"<<endl;
    macierz4 - 3;
    cout << macierz5;

    cout << "Mnozenie macierzy przez liczbe:"<<endl;
    macierz4 * 3;
    cout << macierz5;
    cout<<"----"<<endl;
    macierz macierz6 = macierz4*2;
    cout << macierz6;

    cout << "Mnozenie macierzy przez inna macierz:"<<endl;
    macierz macierz8(3,2);
    macierz8+=macierz8;
    macierz macierz9(2,3);
    macierz9*4;
    macierz macierz7 = macierz8 * macierz9;
    cout << macierz7;

    cout << "Operator += :"<<endl;
    macierz5 += macierz4;
    cout << macierz5;

    cout << "Operator =-:"<<endl;
    macierz5 -= macierz4 ;
    cout << macierz5;

    macierz5 += macierz4;
    cout << "Operator *=:"<<endl;
    macierz5 *= 2;
    cout << macierz5;

    cout << "Operator *=: z inna macierza"<<endl;
    macierz5 *= macierz4;
    cout << macierz5;

    macierz macierzKopia = macierz5;
    if ( macierz5 == macierzKopia )
    {
        cout << "Macierze sa rowne"<<endl;
    }
    if ( macierz5 != macierz4 )
    {
        cout << "Macierze nie sa rowne"<<endl;
    }

    cout << "Dostep do komorek macierzy"<<endl;
    cout<<macierz5(1,1)<<endl;


    cout << "Wpisz liczby do macierzy ktora chcesz utworzyc : "<<endl;
    macierz macierzIn(2,2);
    cin >> macierzIn;
    cout << macierzIn;
    return 0;
}
