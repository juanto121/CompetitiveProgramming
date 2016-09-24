#include<iostream>
#include<math.h> 
#include<stdio.h>

using namespace std;

int main(){
	int t = 0;
	scanf("%d",&t);
	for(int i = 0; i < t; i++){
		int sum = 0;
		int n = 0;
		int k = 0;
		scanf("%d %d",&n,&k);
		double root = sqrt(n);
		
		for(int j = 1; j <= root; j++){
			double second = (n/j);
			if(j%k!=0){
				if(n%j == 0){
					sum += j;
				}
			}
			
			if(fmod(second,k)!=0 && j!=root){
				if(fmod(n,second) == 0){
					sum += second;
				}
			}
			
			
		}
		printf("%d\n",sum);
	}
}
