#include <stdio.h>
#include <iostream>
using namespace std;
int main(){

	int nums[1001];
	nums[0] = 99999999;
	for(int i = 2; i < 1001; i+=2){
		nums[i] = nums[i-2]-1;
	}
	for(int k = 1, l = 1; k < 1001; l++,k+=2){
		nums[k] = l;
	}
	int n = 0;
	while(cin>>n){
		for(int c = 0; c < n; c++){
			printf("%d ",nums[c]);
		}
		printf("\r\n");
	}
}