#include <iostream>
#include <cstdio>
#include <cstring>
#include <string>
using namespace std;
int highest(int num, int low_high[]){
	int high = 0;
	int low = 1e9;
	int lowbit = 0;
	int current = num;
	int pos = 0;
	int lowpos,highpos;
	while(current != 0){
		
		lowbit = current % 10;
		if( lowbit > high){	
			high = lowbit;
			highpos = pos;
		}if(lowbit < low)
		{
			low = lowbit;
			lowpos = pos;
		}
		current = current / 10;
		pos ++;
	}
	
	low_high[0] = lowpos;
	low_high[1] = highpos;
	low_high[2] = low;
	low_high[3] = high;
	low_high[4] = pos;
}
int main(){
	int t, casenum;
	casenum = 1;
	t = 0;
	scanf("%d",&t);
	int low_high[5];
	int lenght;
	memset(low_high,0,sizeof(low_high));
	int swaplow,swaphigh;
	
	while(t--){
		int number = 0;
		swaplow = 0;
		swaphigh = 0;
		scanf("%d",&number);
		int tempn = number;
		highest(tempn,low_high);
		cout << low_high[0] << " " << low_high[1] << " " << low_high[4] << endl;
		int lowpos = low_high[0];
		int highpos = low_high[1];
		lenght = low_high[4];
		string lowest = "";
		for(int i = 0; i < lenght-1; i++){
			if(i == lowpos){
				lowest = to_string(low_high[lenght-1]) + lowest;
				swaplow = tempn%10;
			}else{
				lowest = to_string(tempn % 10) + lowest;
			}
			tempn /= 10;
		}
		printf("%d",low_high[2]);
		tempn = number;
		for(int i = 0; i < lenght; i++){
			if(i == highpos){
				printf("%d",low_high[lenght-1]);
			}else{
				printf("%d",tempn%10);
			}
			tempn /= 10;
		}
		printf("%d",low_high[3]);
	}
}











