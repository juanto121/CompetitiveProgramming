#include<iostream>
#include<string>

using namespace std;

string text, pattern;
int const maxr = 123;
int const maxm = 10e5+1;
int dfa[maxr][maxm];

int KMP(){
	dfa[pattern[0]][0] = 1;
	int M = pattern.length();
	
	for(int x = 0, j = 1; j < M; j++){
		for(int c = 0; c < maxr; c++){
			dfa[c][j] = dfa[c][x];
		}
		dfa[pattern[j]][j] = j+1;
		x = dfa[pattern[j]][x];
	}
}

int findNextMatch(int i , int j){
	int N = text.length();
	int M = pattern.length();
	if(j == M) return 0;
	int k = i;
	for(k = i; k < N; k++){
		if(pattern[j]== text[k]){
			return k;
		}
	}
	return k;
}

int search(){
	int i,j,N = text.length(), M = pattern.length();
	for(i = 0, j = 0; i < N && j < M; i++){
		if(pattern[j]=='*'){
			j++;
			i = findNextMatch(i,j);
		}
		if(j < M && i < N)
			j = dfa[text[i]][j];
	
	}
		if(j == M) return i-M;
		else return N;
}


int main(){
	int cases = 0;
	while( cin >> cases){
		cin >> text;
		for(int t = 0; t < cases; t++){
			cin >> pattern;
			int plen = pattern.length();
			for(int i = 0; i < maxr; i++)
				for(int j = 0; j < plen; j++)
					dfa[i][j] = 0;
			KMP();
			int index = search();
			if(index == text.length())
				cout << "no" << endl;
			else
				cout << "yes" << endl;
		}
	}
}
