#ifndef COMPLEX_H
#define COMPLEX_H

class complex
{
public:
    complex();
    complex(double);
    complex(double,double);
    double fun1(void);
    double fun2(void);
    double fun3(void);
    double fun4(void);

    complex(double (complex::*wf)() , double (complex::*wf2)() , double (complex::*wf3)() ,double (complex::*wf4)()){
       tab_wsk_fun[0]=wf;
       tab_wsk_fun[1]=wf2;
       tab_wsk_fun[2]=wf3;
       tab_wsk_fun[3]=wf4;
    }


    double (complex::*tab_wsk_fun[4])();


private:
    double re, im;
};

#endif // COMPLEX_H
