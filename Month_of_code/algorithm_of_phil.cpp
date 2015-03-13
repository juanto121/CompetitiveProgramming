#include <iostream>
#include <vector>
using namespace std;
int main(){
	
	int tcase = 0;
	cin >> tcase;
	for(int t = 0 ; t < tcase; t++){
	
		vector<int> S;
		string s;
		cin >> s;
		int length = s.length();
		for(int c = 0; c < length; c++){
			S.push_back(s[c]-'0');
		}
		
		printf("%s",s.c_str());
		
		
	}
	
}
