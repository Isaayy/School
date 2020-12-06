#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;

int main()
{
    string nazwaPliku,c1,c2,wczytaneSlowo;

    cin >> nazwaPliku ; cin >> c1;  cin >> c2;

    fstream file , file2 ;
    file.open(nazwaPliku,ios::in);
    file2.open("output.txt",ios::out);

    while (file >> wczytaneSlowo)
    {
        if (wczytaneSlowo==c1)
        {
            wczytaneSlowo=c2;
        }

        file2<<wczytaneSlowo<<" ";
    }
    file2.close();
    file.close();

    return 0;
}
