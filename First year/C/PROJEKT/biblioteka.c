#include "myheader.h"

struct wyrazy
{
   char  wyraz[50];
};

void losujLiczbe(int zakres,int *liczba)
{
    srand((unsigned int) time(NULL));
    *liczba = rand()%zakres;
}

int dlugosc(char tab[] )
{
    int i = 0 ;
    while(tab[i]!='\0')
    {
        i++;
    }
    return i ;
}

void instrukcje(void)
{
    system("cls");
    printf("Gra polega na przepisaniu wylosowanego slowa\n");
    printf("Za poprawne przepisanie zdobywasz punkty ");
    printf("Wcisnij 1 aby wrocic\n");
}


void game(void)
{
    //UTWORZENIE TABLICY SŁÓW
    FILE * f = fopen("slowa.txt","r");
    if (f == NULL)
    {
        printf("Nie udalo sie otworzyc pliku slowa.txt\n");
    }
    char tab[25];
    struct wyrazy mainTab[1000];
    int i = 0;
    int iloscSlow = 0 ;
    while(fgets(tab,25,f)!=NULL)
    {

        while(tab[i]!='\n')
        {
            mainTab[iloscSlow].wyraz[i]=tab[i];
            i++;
        }
        i=0;
        iloscSlow++;
    }
    fclose(f);
    int wylosowanaLiczba;

    //User input

    int gameStatus=1;
    int punkty=0;
    while(gameStatus!=0)
    {
        system("cls");
        printf("Punkty:%d\n",punkty);
        //Losowanie slowa
        losujLiczbe(iloscSlow,&wylosowanaLiczba);
        printf("%s\n",mainTab[wylosowanaLiczba].wyraz);
        // pobieranie słowa
        char wpisaneSlowo[20];
        fgets(wpisaneSlowo,20,stdin);
        int x = 0 ; //zmienna pom

        while(wpisaneSlowo[x]!= '\n' && gameStatus!=0)
        {
            if( (wpisaneSlowo[x] != mainTab[wylosowanaLiczba].wyraz[x]) || (dlugosc(wpisaneSlowo)-1 != dlugosc(mainTab[wylosowanaLiczba].wyraz)) )
            {
                gameStatus=0; // przegrana
                system("cls");
                printf("Nie poprawne slowo\n");
                printf("Twoj wynik to : %d\n",punkty);
                if(max(recordSesji,punkty)>recordSesji)
                {
                    printf("Gratulacjie udalo Ci sie pobic rekord\n");
                    recordSesji = punkty;
                }
                char wybor[10];

                printf("Aby wrocic do menu wcisnij 1\n");
                printf("Aby wyjsc z gry  wcisnij 2\n");

                int wyborDokonany=0;
                while (wyborDokonany!= 1)
                {
                    fgets(wybor,10,stdin);

                    int wyborParsed = atoi(wybor);

                    if(wyborParsed==1)
                    {
                        wyborDokonany= 1;
                        system("cls");
                        menu();
                    }
                    else if(wyborParsed==2)
                    {
                        wyborDokonany= 1;
                    }
                    else
                    {
                        printf("Nie poprawna komenda wybierz jeszcze raz\n");
                        Sleep(500);
                    }
                }


            }
            x++;
        }
        punkty++;
    }
}
void menu(void)
{
    char wybor[10];

    printf("BE QUICK\n");
    printf("Aby zagrac wcisnij 1\n");
    printf("Aby zobaczyc instrukcje wcisnij 2\n");
    printf("Aby wyjsc z gry wcisnij 3\n");

    fgets(wybor,10,stdin);

    int wyborParsed = atoi(wybor);

    if (wyborParsed==1)
    {
        game();
    }
    else if(wyborParsed ==2)
    {
       instrukcje();
        while(wyborParsed !=1)
        {
            fgets(wybor,10,stdin);
            wyborParsed = atoi(wybor);
            if(wyborParsed == 1)
            {
                system("cls");
                menu();
            }
            else
            {
                printf("Nie poprawna komenda wybierz jeszcze raz\n");
                Sleep(500);
                instrukcje();
            }
        }
    }
    else if(wyborParsed == 3)
    {

    }
    else
    {
        system("cls");
        wyborParsed = -1;
        printf("Nie prawidlowa komenda wybierz jeszcze raz\n");
        menu();
    }
}
