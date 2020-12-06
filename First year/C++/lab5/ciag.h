#ifndef CIAG_H
#define CIAG_H

#include <string.h>
#include <iostream>

using namespace  std;

typedef unsigned long long int ull;

class ciag
{
    friend ciag operator + (const ciag& , const ciag&);
    friend ostream& operator << (ostream&,const ciag&);
    friend istream& operator >> (istream&,const ciag&);

public:
    ciag();
    ciag(char const *);
    ciag(ciag &);
    ~ciag();

    ull  dl(void);
    static int ile();

    ciag operator = (char const *str)
    {
        this->przechowywanyCiag = new char[strlen(str)];
        strcpy(this->przechowywanyCiag,str);
        return *this;
    }

    ciag operator += (ciag const &obj)
    {
        ciag tmp(this->przechowywanyCiag);
        ull size = this->dl() ;
        delete[] this->przechowywanyCiag;
        ull size2 = strlen(obj.przechowywanyCiag);
        this->przechowywanyCiag = new char [size+size2];

        strcpy(this->przechowywanyCiag,tmp.przechowywanyCiag);
        strcat(this->przechowywanyCiag,obj.przechowywanyCiag);

        return *this;
    }

    operator char*()
    {
        return this->przechowywanyCiag;
    }

private:
    char *przechowywanyCiag ;
    static int iloscObiektow ;
};

#endif // CIAG_H
