#include<iostream>
#include<string>

using namespace std;

int count_ones(string t, string s){
	int ones_s = 0, ones_t = 0;
	for(int i = 0; i < t.length(); i++){ 
		if(t[i] == '1')	ones_t++;
		if(s[i] == '1') ones_s++;
	}
	
	return ones_s > ones_t;
}

int main(){
	int cases = 0;
	scanf("%d",&cases);
	int t_case = 0;
	while(t_case++ < cases){
		string S, T;
		int sol = 0;
		cin >> S >> T;
		
		int count_diff = 0;
		for(int i = 0; i < S.length(); i++){
			if(S[i] != T[i]){
				count_diff ++;
			}
		}
		count_ones(T, S);
		if(count_ones(T,S)) sol = -1;
		else sol = count_diff;
		
		printf("Case %d: %d\n", t_case, sol);
	}
}

