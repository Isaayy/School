#include <iostream>
 using namespace std;
 
int **createTriangle(int size)
{
        int sizee = size;
        int **tab = new int*[size];
        for (int a = 0 ; a < size ; a++)
        {
                tab[a]= new int[size];          
        }
        
        for ( int i = 0 ; i < size ; i++){
                for(int k = 0 ; k <size ; k++){
                        if (k ==0 ){
                                tab[i][k]=1;
                        }
                        else if (i==0 && k!= 0){
                                tab[i][k]=0;
                                
                        }
                        else if (k>0&&i>0){
                                tab[i][k]=tab[i-1][k]+tab[i-1][k-1];
                        }
                        
                }
        }
        return tab;
}
 
 
int main()
{
                int N ; 
                cin >> N ; 
                int **tab = createTriangle(N);
                for ( int i = 0 ; i < N ;  i ++){
                        for ( int j = N-i ; j>0;j-- ){
                                cout <<" ";
                        }
                        for ( int k = 0 ; k < N ; k++){
                                if (tab[i][k]!=0){
                                        cout << tab[i][k] << " " ; 
                                }
                                
                        }
                        cout << endl;
                }
                
        for (int a = 0 ; a < size ; a++)
        {
                tab[a]= delete int[size];               
        }
}