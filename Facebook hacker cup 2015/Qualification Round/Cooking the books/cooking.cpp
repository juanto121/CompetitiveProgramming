#include <iostream>
#include <cstdio>
#include <string>
#include <climits>
using namespace std;

void lowHighIndexes(int * low, int *high, const string number){
	
	int max = 0;
	int min = INT_MAX;
	
	int max_i = 0;
	int min_i = 0;

	int lenght = number.length();
	
	for(int i = 0; i < lenght; i++){
		int num = number[i] - '0';
		if( num  > max ){
			 max = num;
			 max_i = i;
		}else if(num < min && num > 0){
			 min = num;
			 min_i = i;	
		}
	}
	
	*low = min_i;
	*high = max_i;
	
}

string swapIndexes(string s, int index){
	
 	string stemp = s;
 	s[0] = s[index];
 	s[index] = stemp[0];
 	return s;
}

int main(){
	
	/* TODO 
		Largest: Take the rightmost largest number & 
		put it in the leftmost position that is different
		from itself 
		
		Smallest: Take the rightmost lowest number (lower than the leftmost & different from 0) & 
		put it in the leftmost position that is different
		from itself, check if the lowest is 0 then skip
		the leftmost position and swap with the first different
		number.
	*/
	
	int t, casenum;
	scanf("%d",&t);
	for(casenum = 0; casenum < t; casenum++){
		string number;
	 	cin >> number;
		
		int highest_num_index = 0; 
		int lowest_num_index = 0;
		
		lowHighIndexes(&lowest_num_index, &highest_num_index, number);
		
		string lowest = swapIndexes(number,lowest_num_index);
		string highest = swapIndexes(number,highest_num_index);
		
		printf("Case #%d: %s %s\n", casenum+1, lowest.c_str(), highest.c_str());
		
		
	}

}











