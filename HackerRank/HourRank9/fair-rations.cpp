#include <map>
#include <set>
#include <list>
#include <cmath>
#include <ctime>
#include <deque>
#include <queue>
#include <stack>
#include <string>
#include <bitset>
#include <cstdio>
#include <limits>
#include <vector>
#include <climits>
#include <cstring>
#include <cstdlib>
#include <fstream>
#include <numeric>
#include <sstream>
#include <iostream>
#include <algorithm>

using namespace std;


int main(){
    int N;
    cin >> N;
    vector<int> B(N);
    int odd = 0;
    int even = 0;
    bool in = false;
    
    for(int B_i = 0;B_i < N;B_i++){
       	cin >> B[B_i];
	    if( B[B_i] %2 != 0){
	        odd++;
	        in = !in;
	    }
	    if(in)even++;
    }
    if(odd%2!=0){
        cout << "NO";
    }else{
        cout << even*2;
    }
	
    return 0;
}
