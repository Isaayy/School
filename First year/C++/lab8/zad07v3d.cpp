#include "zad07v3d.h"
#include <math.h>
vect3d::vect3d():vect()
{
    normalizuj();
}

vect3d::vect3d(double* arrIn,int size):vect(arrIn,size)
{
    normalizuj();
}
vect3d::vect3d(int r):vect(r)
{
    normalizuj();
}
vect3d::vect3d(const vect3d &obj):vect(obj)
{
    normalizuj();
}

void vect3d::normalizuj()
{
    this->set_size(3);
    double dlugoscWektora;
    dlugoscWektora = sqrt( pow(array[0],2) + pow(array[1],2) + pow(array[2],2));

    array[0]=array[0]/dlugoscWektora;
    array[1]=array[1]/dlugoscWektora;
    array[2]=array[2]/dlugoscWektora;
}


ostream & operator << (ostream &out, vect3d &obj)
{
    obj.normalizuj();
    out << "[";
    out << obj.length << " ";
    for ( int i = 0 ; i < obj.length ; i++)
    {
        out << obj.array[i]<<" ";
    }
    out << "]";
    return out;
}

