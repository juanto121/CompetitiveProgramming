#include<iostream>
#include<stdio.h>
#include <string>
#include <sstream>
using namespace std;

int main(){
	int tanks, queries;
	
		bool firstl = 1;
	while(scanf("%d %d",&tanks, &queries) != EOF){
		int t[tanks];
		int tac[tanks];
			string line;
		getline(cin,line);
		tac[0] = 1;
	
		getline(cin,line);
		stringstream ss(line);

		for(int i = 0 ; i < tanks; i++){
			int num =0;
			ss >> num;
			t[i] = num;
			t[0] = 1;
			if(i > 0)
				tac[i] = tac[i-1] + t[i];		
		}
		
		int q;
		getline(cin,line);
		stringstream ssr(line);
		bool first = 1;
		
		if(firstl){	
			firstl = 0;
		}else{
			printf("\n");
			
		}
		
		for(int r = 0; r < queries; r++){
			ssr >> q;
			
			for(int k = tanks-1; k >= 0; k--){
				if(q >= tac[k]){
					if(first){
						printf("%d",k+1);
						first = 0;
					}
						
					else
					{
						printf(" %d",k+1);
					}	
					break;
				}
			}
		}
	

	}
}
