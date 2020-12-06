#include "talerz.h"


talerz::talerz()
{
    srednica = 0 ;
}

talerz::talerz(double rozmiar)
{
    if (rozmiar != 0)
        srednica = rozmiar;
}

talerz::talerz(const talerz &obj)
{
    this->srednica=obj.srednica;
}


double talerz::jakaSrednica()
{
    return srednica;
}

void talerz::dodajSrednice(double rozmiar)
{
    if (srednica == 0 )
    {
        if (rozmiar != 0)
            srednica = rozmiar;
    }

}


