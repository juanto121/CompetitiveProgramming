#include<iostream>
#include<cmath>

using namespace std;
int main(){
	int n = 0;
	int s = 0;
	while(scanf("%d", &s) && s){
		
		
		double n = (-1.0+(sqrt(1.0+4.0*(2.0*s))))/2.0;
		long m = ceil(n);
		
		if(fmod(n,1.0) == 0){
			printf("%d %d\n",m+1,m+1);
		}else{
			long r = m*(m+1)/2;
			printf("%d %d\n", r-s, m);
		}
	}
}
