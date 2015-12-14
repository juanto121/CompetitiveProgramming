#include<iostream>
#include<stdio.h>
#include<stdlib.h>
#include<cstring>

#define MAX_N 1000

using namespace std;
	
	int n;
	int mat[MAX_N][MAX_N];
	int res[MAX_N][MAX_N];
	int r[MAX_N];
	
	void printm(){
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				cout << mat[i][j] << " "; 
			}
			cout << endl;
		}
	}
	
	void printc(int res[][1000]){
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				cout << res[i][j] << " "; 
			}
			cout << endl;
		}
	}
	
	void printarr(int a[]){
		for(int i = 0; i < n ; i++){
			printf(" %d ", a[i]);
		}
		cout << endl;
	}
	

bool isCorrect(){
	bool correct = true;
	int Br [n];
	memset(Br,0,sizeof(Br));
	int Cr [n];
	memset(Cr,0,sizeof(Cr));
	for(int j = 0; j < n; j++){
		for(int k = 0; k < n ; k++){
		 Br[j] += mat[j][k]*r[k];
		 Cr[j] += res[j][k]*r[k];
		}
	}
	int w[n];
	memset(w,0,sizeof(w));
	for(int j = 0; j < n ; j ++){
		for(int k = 0; k < n; k++){
			w[j] += mat[j][k] *	Br[k];
		}
	 if(w[j] != Cr[j]) return false;
	}
	
	return correct;
}	

int main(){
	while(true){
		n = 0;
		scanf("%d",&n);
		if(n == 0) break;
	
		memset(r,0,sizeof(r));
		
		for(int i = 0; i < n; i++){
			
			for(int j = 0; j < n; j++){
				//mat[i][j] = 0;
				scanf("%d",&mat[i][j]);
			}
		}
		
		for(int i = 0; i < n ; i++){
			for(int j = 0; j < n; j++){
				scanf("%d",&res[i][j]);
			}
		}
		

		bool correct = true;
		for(int i = 0; i < 10; i++){
			for(int k = 0; k < n; k++ )r[k] = rand()%n;
				correct = isCorrect();
				if(!correct) break;
		}
		if(correct)
			printf("%s\n","YES");
		else
			printf("%s\n","NO");
	}
}

