#include<iostream>
#include<cstring>
#include <algorithm>
using namespace std;

int printsolution(int *weights){
	for(int i = 0; i < 5; i++){
		printf("%d ", weights[i]);
	}
	printf("\n");
}

int main(){
	int cases = 0,  t = 0;
	scanf("%d",&cases);
	int weights[10];
	while(t++<cases){
		memset(weights,0,sizeof(weights));
		int total = 0;
		for(int i = 0 ; i < 10; i++){
			scanf("%d",&weights[i]);
			total += weights[i];
		}
			int x = weights[0]; int y = weights[1];	int z = weights[9];	int w = weights[8];
			
			int a = ( total - (4*x+4*y+4*z+4*w+4*(-1*(w-y))+4*-y))/-4;
			int b = x-a;
			int c = y -a;
			int e = w-c;
			int d = z-e;
			
			int individual_w[] = {a,b,c,d,e};
			//sort(individual_w,individual_w+5);
			printf("Case %d:",t);
			
			
			for(int i = 0; i < 5; i++){
				printf(" %d", individual_w[i]);
			}
			printf("\n");
			
	}
	
}

