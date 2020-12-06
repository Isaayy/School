#include "zad07sv3d.h"

svect3d::svect3d():svect()
{
     vect3d::normalizuj();
}

svect3d::svect3d(double* arrIn,int size):svect(arrIn,size)
{
//    vect3d::normalizuj();
}
svect3d::svect3d(int r):svect(r)
{
     vect3d::normalizuj();
}
svect3d::svect3d(const svect3d &obj):svect(obj)
{
    vect3d::normalizuj();
}

//ostream & operator << (ostream &out, svect3d &obj)
//{

//    out << "[";
//    vect3d tmp = obj;
//    out << tmp.get_size() << " ";
//    for ( int i = 0 ; i < tmp.get_size() ; i++)
//    {
//        out << tmp.array[i]<<" ";
//    }
//    out << "]";
//    return out;
//}

int svect3d::get_size()
{
    vect3d::get_size();
}
