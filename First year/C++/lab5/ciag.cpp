#include <iostream>
#include "ciag.h"
#include <string.h>

using namespace std;

ciag::ciag()
{
    przechowywanyCiag= new char[50];
    if (przechowywanyCiag == NULL)
    {
        cout << "Nie udalo sie zaalokowac pamieci";
    }
    else
        iloscObiektow++;
}
ciag::ciag(char const * str)
{
    przechowywanyCiag = new char[strlen(str)];
    if (przechowywanyCiag == NULL)
    {
        cout << "Nie udalo sie zaalokowac pamieci";
    }
    else
    {
        strcpy(przechowywanyCiag,str);
        iloscObiektow++;
    }
}

ciag::ciag(ciag &obj)
{
    this->przechowywanyCiag = new char[strlen(obj.przechowywanyCiag)];
    strcpy(this->przechowywanyCiag,obj.przechowywanyCiag);
    iloscObiektow++;
}

ciag::~ciag()
{
    delete[] this->przechowywanyCiag;
    iloscObiektow--;
}

int ciag::iloscObiektow = 0;

ull ciag::dl()
{
    return strlen(przechowywanyCiag);
}

int ciag::ile()
{
    return iloscObiektow;
}
ciag operator + ( const ciag &obj , const ciag &obj2 )
{
    ciag tmp(obj.przechowywanyCiag);
    ciag tmp2(obj2.przechowywanyCiag);
    tmp+=tmp2;
    return tmp;
}

ostream & operator << (ostream &out,const ciag &obj)
{
    out << obj.przechowywanyCiag;
    return out;
}

istream & operator >> (istream &in, const ciag &obj)
{
    in >> obj.przechowywanyCiag;
    return in;
}
