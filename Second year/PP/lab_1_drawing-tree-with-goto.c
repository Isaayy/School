#include <stdio.h>

void main() {
    
    int trees = 0;
    int treeHeight;
    
    int spaces;
    int znaki ;
    int i ;
    int k ;
    
    char sign;
    
    
    getHeight:
        // base settings
        i = 1;
        k = 1;
        trees++;
        znaki = 0;
        
        // get height of tree from user
        scanf("%d",&treeHeight);
        spaces = treeHeight - i ;
        
        // Determine which sign use to draw tree
        if(treeHeight % 2 == 0 ) sign = '*';
        if(treeHeight % 2 != 0 ) sign = '#';

    // Draw row of spaces 
    drawSpace:
        printf(" ");
        spaces--;
    if(spaces >= 0) goto drawSpace;
    
    // Draw row of signs
    znaki = 0;
    rysujZnak:
        printf("%c", sign);
        znaki++;
        if(znaki<k) goto rysujZnak;
        
    if(znaki < k) goto rysujZnak;
     
    printf("\n"); // start next row
    i++; // for spaces
    
    k+=2;

    // Draw new row of spaces
    spaces=treeHeight-i;
    if(i<=treeHeight) goto drawSpace;

 
    printf("\n"); // space between trees
    
    // Draw trees until 7
    if(trees<7) goto getHeight;
    
}