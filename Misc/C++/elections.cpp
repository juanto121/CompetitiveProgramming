# include <iostream>
#include <stdio.h>

using namespace std;

int main(){
	int c;
	while(scanf("%d",&c) != EOF){
		for(int t = 0; t < c; t++){
			int n, m;
			scanf("%d %d",&n,&m);
			int votes[n];
			for(int vt = 0; vt < n; vt ++) votes[vt] = 0;
			int max =0 ;
			int maxin = 0;
			for(int i = 0; i < m; i++){
				for(int j = 0; j < n; j++){
					int v;
					cin >> v;
					votes[j] += v;
					if(votes[j] > max){
						max = votes[j];
						maxin = j;
					}
				}
			}
			
			printf("%d\r\n",maxin+1);
						
		}
	}
	
}
