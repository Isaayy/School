#include "complex.h"
#include <math.h>
complex::complex()
{
    re = 0 ;
    im = 0 ;


}

complex::complex(double reIn)
{
    re = reIn;
    im = 0;

}

complex::complex(double reIn ,double imIn)
{
    re = reIn;
    im = imIn;

}

double complex::fun1()
{
    return re;
}

double complex::fun2()
{
    return im;
}

double complex::fun3()
{
    return re*re+im*im;
}
double complex::fun4()
{
    return sqrt(fun3());
}

