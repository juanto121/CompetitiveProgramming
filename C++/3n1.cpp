
#include <iostream>
#include <cstdio>

using namespace std;


int  main(){
    
    int i ;
    int j ;
        
    
  while ( scanf ( "%d %d" , &i , &j ) != EOF){
         int swap;
        if( i > j ){
          swap = j;
          j = i;
          i = swap;
          }
          int lcycle = 1;
          int lcycleM = 0;
          for (int h = i ; h <= j ; h++){
              long long int m = h;
              lcycle = 1;
              while ( m != 1){
                    if ( m % 2 == 0 ) 
                       m = m/2;
                    else
                       m = (m*3)+1;
                       
                    lcycle++;
                    }
                    
          
          lcycleM = (lcycle > lcycleM ? lcycle : lcycleM);
          
          }
           printf("%d %d %d\n", i,j,lcycleM);          
        
    
        } 
return 0;
    
}
