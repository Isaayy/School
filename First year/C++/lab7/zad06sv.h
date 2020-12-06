#ifndef ZAD06SV_H
#define ZAD06SV_H

#include "zad06v.h"

class svect : public vect
{
    friend ostream& operator << (ostream&, svect&);
public:
    svect();
    svect(double*,int);
    svect(int);
    svect(const vect&);

    svect operator =(const svect &obj)
    {
        vect::operator=(obj);
        sort();
    }

    double& operator[](int index)
    {
        sort();
        vect::operator[](index);
    }
private:
    void sort(void);
};

#endif // ZAD06SV_H
