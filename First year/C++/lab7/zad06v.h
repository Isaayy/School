#ifndef ZAD06V_H
#define ZAD06V_H

#include <iostream>
using namespace std;

class vect{

    friend ostream& operator << (ostream&,const vect&);
    friend istream& operator >> (istream&,vect&);
    friend bool operator == (const vect&,const vect&);
    friend bool operator != (const vect&,const vect&);
public:
    double *array;
    int length;
    vect();
    vect(double*,int);
    vect(int);
    vect(const vect&);
    ~vect();

    vect operator = (const vect &obj)
    {
        delete this->array;
        this->array = new double[obj.length];

        for ( int i = 0 ; i < obj.length ; i++)
        {
            this->array[i]=obj.array[i];
        }
        this->length=obj.length;
        return *this;
    }

    double& operator[](int);

    int get_size(void);
    void set_size(int);
    void dodaj(double);
};


#endif // ZAD06V_H
