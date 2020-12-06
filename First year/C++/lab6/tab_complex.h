#ifndef TAB_COMPLEX_H
#define TAB_COMPLEX_H

#include <iostream>
#include "complex.h"

using namespace  std;

class tab_complex
{

friend ostream& operator << (ostream&, tab_complex&);

public:
    tab_complex();
private:
    complex tab_c[10];
};

#endif // TAB_COMPLEX_H
