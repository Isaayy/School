#ifndef ZAD07V3D_H
#define ZAD07V3D_H

#include "zad06v.h"

class vect3d : public vect
{
    friend ostream& operator << (ostream&, vect3d&);
public:
    vect3d();
    vect3d(double*,int);
    vect3d(int);
    vect3d(const vect3d&);

    void normalizuj(void);
};

#endif // ZAD07V3D_H
