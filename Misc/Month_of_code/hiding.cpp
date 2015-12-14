#include<iostream>
#include<cstring>
using namespace std;
int main(){
	int numOfNums = 0;
	scanf("%d",&numOfNums);
	int nums[numOfNums];
	memset(nums, 0, sizeof(nums));
	for(int i = 0; i < numOfNums; i++){
		scanf("%d", &nums[i] );
	}
	int zeros = 0;
	for(int i = 0; i < numOfNums; i++){
		int sum = nums[i];
		for(int j = i+1; j < numOfNums; j++){
			sum+= nums[j];
			if(sum == 0) zeros++;
		}
	}
	printf("%d\n", zeros);
}
