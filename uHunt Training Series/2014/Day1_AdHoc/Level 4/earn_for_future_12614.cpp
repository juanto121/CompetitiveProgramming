#include<iostream>
#include<cmath>
using namespace std;
int main(){
	int t;
	cin >> t;
	for(int cases = 0; cases <t ; cases++){
		int cards = 0;
		cin >> cards;
		int max = 0;
		int card = 0;
		for(int c = 0; c < cards; c++){
			cin >> card;
			if(card > max){
				max = card;
			}
		}
		printf("Case %d: %d\n",cases+1, max);
	}
}
