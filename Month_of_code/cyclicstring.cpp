#include<iostream>
#include<string>

using namespace std;

int main(){
	ios::sync_with_stdio(false);
	int cases = 0;
	cin >> cases;

	
	for(int t = 0; t < cases; t++){
		string s;
	    string symbol;
		cin >> s;
		int l = s.length();
		
		symbol.append(s.substr(0,1));
		int pos = 0;
		for(int c = 1; c < l; c++){
			int sl = symbol.length();
			if(s[c] == s[0] && symbol[pos%sl] == s[c] && l-sl >= sl){
				if(sl == 1)	pos = 1;
				else pos++;
			}else{
				if(symbol[pos%sl] == s[c] && l-sl >= sl){
					pos++;
				}else{
					symbol= s.substr(0,c+1);
					pos = 0;
				}
			}
		}
		cout << symbol.length() <<"\n";
		
	}
}


