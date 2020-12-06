#ifndef ZAD07SV3D_H
#define ZAD07SV3D_H

#include "zad07v3d.h"
#include "zad06sv.h"

class svect3d : public svect , public vect3d
{
//    friend ostream& operator << (ostream&, svect3d&);
public:
    svect3d();
    svect3d(double*,int);
    svect3d(int);
    svect3d(const svect3d&);
    int get_size(void);

    void normalizuj(void);
};


#endif // ZAD07SV3D_H
