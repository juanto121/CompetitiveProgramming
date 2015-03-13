#include <iostream>
#include <cstdio>
#include <string>
#include <climits>
#include <algorithm>
using namespace std;


void stringToNumberArray(string number, int *num){
	int lenght = number.length();
	
	for(int i = 0; i < lenght; i++){
		num[i] = number[i] -'0';
	}
	
}

void high(int *high, string number){
	
	
	int lenght = number.length();
	int num[lenght];
	stringToNumberArray(number, num);	
	sort(num, num + sizeof(num)/sizeof(num[0]) );
	
	
	int max_index = -1, max = -1;
	for(int i = 0; i < lenght; i++){
		if( number[i] - '0' > max )
		{
			max = number[i] - '0';
			max_index = i;
		}
	}
	if(max!=-1)*high = max_index;
}


void lowHighIndexes(int * low, int *high, const string number){
	
	int max = 0;
	int min = INT_MAX;
	
	int max_i = 0;
	int min_i = 0;
	int min_i_zero = -1;

	int lenght = number.length();
	
	for(int i = 0; i < lenght; i++){
		int num = number[i] - '0';
		if( num  >= max){
			 max = num;
			 max_i = i;
		}
		 if(num <= min){
			 if(num != 0){
				 min = num;
				 min_i = i;		
			 }else{
			 	min_i_zero = i;
			 }
		}
	}
	
	//es el minimo igual al primer numero?
	if(number[min_i]-'0' == number[0]-'0' && min_i_zero!=-1){
		*low = min_i_zero;
	}else{
		*low = min_i;
	}
	
	*high = max_i;
	
}

string swapHigh(string s, int index){
	int lenght = s.length();
	string stemp = s;
	for(int i = 0; i < index; i++){
		if(s[i]-'0' != s[index]-'0' && !(s[index]-'0' == 0 && i == 0) ){
			s[i] = s[index];
			s[index] = stemp[i];
			break;
		}
	}
	return s;	
}

string swapLow(string s, int index){
	int lenght = s.length();
	string stemp = s;
	if(s[index]-'0' != 0){
		s[0] = s[index];
		s[index] = stemp[0];
	}else{
		for(int i = 1; i < lenght; i++){
			if(s[i]-'0' != 0){
				s[i] = '0';
				s[index] = stemp[i];
				break;
			}
		}
	}
	return s;
}


int main(){
	
	int t, casenum;
	scanf("%d",&t);
	for(casenum = 0; casenum < t; casenum++){
		string number;
	 	cin >> number;
		
		int highest_num_index = 0; 
		int lowest_num_index = 0;
		
		lowHighIndexes(&lowest_num_index, &highest_num_index, number);
		
		string lowest = swapLow(number,lowest_num_index);
		string highest = swapHigh(number,highest_num_index);
		
		high(&highest_num_index, number);
		
		printf("Case #%d: %s %s\n", casenum+1, lowest.c_str(), highest.c_str());	
		
	}

}











