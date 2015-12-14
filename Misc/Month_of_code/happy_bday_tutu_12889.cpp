#include<iostream>
using namespace std;
int main(){
	int cases = 0;
	scanf("%d",&cases);
	while(cases--){
		int piles = 0;
		scanf("%d",&piles);
		int real_piles = 0;
		while(piles--){
			int num_stones  = 0;
			scanf("%d",&num_stones);
			if(num_stones>0)real_piles++;
			
		}
		if(real_piles >= 2) printf("%s","Happy Birthday Tutu!\n");
		else{
			printf("%s","Better luck next time!\n");	
		} 
	}
}
