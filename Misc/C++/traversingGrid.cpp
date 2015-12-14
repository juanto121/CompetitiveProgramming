#include <iostream>
#include <stdio.h>

using namespace std;
int main(){
	int cases;
	scanf("%d",&cases);
	for(int t = 0; t < cases; t++){
		int min, n, m;
		scanf("%d %d",&n,&m);
		min = n<m?n:m;
		if(min%2 != 0){
			if( (n-m) > 0 ) printf("D\r\n");
			else printf("R\r\n");
		}else{
			if( (n-m) > 0 ) printf("U\r\n");
			else printf("L\r\n");
		}
	}
}
