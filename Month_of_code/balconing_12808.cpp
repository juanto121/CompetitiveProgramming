#include<iostream>
#include<cstring>
#include <math.h> 
using namespace std;

const int gravity = 9810;

int main(){
	int cases = 0;
	scanf("%d",&cases);
	for(int t = 0; t < cases; t++){
		double L,D,H,V;
		string result;
		cin >> L >> D >> H >> V;
		double time_to_hit = H / (gravity/2.0);
		double air_distance = V * sqrt(time_to_hit);
		
		if( air_distance >D +500 && air_distance < D+L-500 ){
			result = "POOL";
		//	printf("%d %d\n",D+500,D+L-500);
		}else if(air_distance < D-500 || air_distance > D+L+500 ){
			result = "FLOOR";
		//	printf("%d %d\n",D-500,D+L+500);
		}else{
			result = "EDGE";
		}
		
		printf("%s\n",result.c_str());
	}
}
