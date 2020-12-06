#include <iostream>
#include "zad06v.h"
#include "zad06sv.h"
#include "zad07v3d.h"
#include "zad07sv3d.h"

using namespace std;

int main()
{
    //vect3d
    vect3d v3d_1(3);
    for (int i = 0 ; i < v3d_1.get_size() ; i++)
    {
        v3d_1[i]=i+1;
    }

    cout<<"v3d_1 = " << v3d_1<<endl;
    vect3d v3d_2 = v3d_1;
    v3d_2[0]=5;
    cout<<"v3d_2 = " << v3d_2<<endl;

//    cout << "---------------------------"<<endl;
//    //svect3d

//    double tmpArray[3]={2,4,6};
//    svect3d sv3d_1(tmpArray,3);
//    cout<<"sv3d_1 = " << sv3d_1<<endl;

    return 0;
}
