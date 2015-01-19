#include<iostream>
#include<cmath>
using namespace std;
int main(){
	int N,i,j;
	while(scanf("%d %d %d",&N, &i, &j)!= EOF){
		int round = 0;
		while( i != j){
			i = i % 2 == 0 ? i / 2 : ceil(i/2.0);
			j = j % 2 == 0 ? j / 2 : ceil(j/2.0);
			round ++;
		}
		cout << round << endl;
	}
}
