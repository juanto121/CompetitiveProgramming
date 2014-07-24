#include<iostream>
#include<cstdio>
#include<algorithm>
#include<cstring>
#include <map>
#include<sstream>
using namespace std;
//asd
int main(){
	int cases;
	scanf("%d",&cases);
	for(int ca = 0; ca< cases ; ca++){
	
		int N;
		map<int,int> digits;
	
		scanf("%d",&N);
		
		stringstream cadenaDigits;
		for(int i = 1 ; i<= N;i++){
			cadenaDigits << i ;
		}
	//	cout << cadenaDigits.str()<<endl;
		
		string complete = cadenaDigits.str();
		int sizeString = cadenaDigits.str().length();
	
		for(int k = 48; k<58 ; k++){
			digits.insert( pair<int,int> (k,0) );
		}
		
		for(int j = 0 ; j <sizeString;j++) {
			
			if(digits.find(complete.at(j)) != digits.end()){
				digits[complete.at(j)] = digits[complete.at(j)]+1;
			}
		
		}
		
		for(map<int,int>::iterator ii = digits.begin() ; ii!=digits.end(); ii++){
			cout << (*ii).second;
			if((*ii).first != 57) cout <<" ";
		}
	
		cout<< endl;
		
		//cout << cadenaDigits.str();
	
	}
	
}