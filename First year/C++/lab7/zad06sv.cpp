#include "zad06sv.h"
#include <cstdlib>
svect::svect():vect()
{
    sort();
}

svect::svect(double* arrIn,int size):vect(arrIn,size)
{
    sort();
}
svect::svect(int r):vect(r)
{
    sort();
}
svect::svect(const vect &obj):vect(obj)
{
    sort();
}

void svect::sort()
{
    if(array==0){}
    else
    {
        int i, j;
        int n = get_size();
        for (i = 0; i < n-1; i++)

        for (j = 0; j < n-i-1; j++)
            if (array[j] > array[j+1])
            {
                double tmp = array[j];
                array[j] = array[j+1];
                array[j+1]= tmp;
            }
    }

}

ostream & operator << (ostream &out, svect &obj)
{
    obj.sort();
    out << "[";
    out << obj.length << " ";
    for ( int i = 0 ; i < obj.length ; i++)
    {
        out << obj.array[i]<<" ";
    }
    out << "]";
    return out;
}

