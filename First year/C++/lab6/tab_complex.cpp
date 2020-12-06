#include "tab_complex.h"
#include "complex.h"

tab_complex::tab_complex()
{
    for ( int i = 0 ; i < 10 ; i++)
    {
        complex tmp(i,i);
        tmp.tab_wsk_fun[0]=&complex::fun1;
        tmp.tab_wsk_fun[1]=&complex::fun2;
        tmp.tab_wsk_fun[2]=&complex::fun3;
        tmp.tab_wsk_fun[3]=&complex::fun4;
        tab_c[i]=tmp;
    }
}

ostream & operator << (ostream &out, tab_complex&obj)
{
    for ( int i = 0 ; i < 10 ; i++)
    {
        for ( int k = 0 ; k < 4 ; k ++)
        {
            out << (obj.tab_c[i].*obj.tab_c[i].tab_wsk_fun[k])()<<endl;
        }

    }
    return out;
}
