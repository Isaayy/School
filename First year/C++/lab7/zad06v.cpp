#include "zad06v.h"
#include <iostream>
using namespace std;

vect::vect()
{
   array = new double[50];
}

vect::vect(double *arrIn , int size)
{
    array = new double[size];
    for ( int i = 0 ; i < size ; i++)
    {
        array[i]=arrIn[i];
    }
    length=size;
}

vect::vect(int size)
{
    array = new double[size];
    length=size;
}

vect::vect(const vect &obj)
{
    array = new double[obj.length];
    for ( int i = 0 ; i < obj.length ; i++)
    {
        array[i]=obj.array[i];
    }
    length=obj.length;

}


vect::~vect()
{
    delete array;
    length=0;
}
double& vect::operator[](int index)
{
    if (index >= length) {
        cout << "Index jest za wysoki";
        exit(0);
    }
    return array[index];
}

int vect::get_size()
{
    return length;
}

void vect::set_size(int newSize)
{
    vect tmp(array,length);

    delete array;
    array = new double[newSize];

    for ( int i = 0 ; i < newSize ; i++)
    {
        array[i]=0;
    }
    for ( int k = 0 ; k < length ; k ++)
    {
        array[k]=tmp.array[k];
    }
    length=newSize;

}

void vect::dodaj(double element)
{
    vect tmp(array,length);
    delete array;
    array = new double[length+1];

    for ( int k = 0 ; k < length ; k ++)
    {
        array[k]=tmp.array[k];
    }
    array[length]=element;
    length++;
}

ostream & operator << (ostream &out,const vect &obj)
{
    out << "[";
    out << obj.length << " ";
    for ( int i = 0 ; i < obj.length ; i++)
    {
        out << obj.array[i]<<" ";
    }
    out << "]";
    return out;
}

istream & operator >> (istream &in,vect &obj)
{
    int size;
    in >> size;
    delete obj.array;
    obj.array = new double[size];
    obj.length=size;
    for ( int i = 0 ; i < size ; i++)
    {
        in >> obj.array[i];
    }
    return in;
}

bool operator == (const vect& obj1,const vect& obj2)
{
    if (obj1.length != obj2.length)
    {
        return false;
    }
    else
    {
        for ( int i = 0 ; i < obj1.length ; i++)
        {
            if(obj1.array[i] != obj2.array[i])
            {
                return false;
            }
        }
        return true;
    }
}

bool operator != (const vect& obj1,const vect& obj2)
{
    int size;
        if (obj1.length  > obj2.length)
            size = obj1.length;
        else
            size = obj2.length;

        for ( int i = 0 ; i < size ; i++)
        {
            if(obj1.array[i] != obj2.array[i])
            {
                return true;
            }
        }
        return false;
}
