#include<iostream>
#include<stdio.h>
#include<math.h>

using namespace std;

int triangle()
{
	
	int triang[500];
	triang[1] = 1;
	triang[2] = 5;
	triang[3] = 13;
	triang[4] = 27;
	
	int n;

	
		
			for(int n = 5;n<501;n++){
			
				triang[n]=0;
				triang[n]+=triang[n-1]+1;
			
				for(int j = n-1;j>0;j--){
					triang[n] += ( n-(j-1) );	
					
				}
				
				if(n%2 == 0){
					for(int u = 1;u<n;u+=2){
						triang[n]+=u;
						
					}
				}else{
					for(int u = 2; u <=n;u+=2){
						triang[n]+=u;
						//cout<<u;
					}
				}
	     	}
	     	
	while(scanf("%d",&n)!=EOF){
		cout<<triang[n]<<endl;
	}
			

	
}
