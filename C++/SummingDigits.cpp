#include<iostream>
#include<cstdio>
#include<cstring>
using namespace std;

int f(int n){
	return (n >= 10 ? (n%10)+ f(n/10) : n);
}
int main(){
	int n;
	while(true){
		scanf("%d",&n);
		if(n==0)break;
		n=f(n);
		while(n>=10){
			n=f(n);
		}
		cout<<n<<endl;
	}
}
