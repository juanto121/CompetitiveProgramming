#include<iostream>
#include<stdio.h>
#include<vector>
#include<string.h>

using namespace std;

const int MAX_NODES = 1000+3;
vector<int> rgraph[MAX_NODES];
int connections [MAX_NODES][MAX_NODES];

bool disj(int ig, int j){
	int iga = ig<j?ig:j; //When j < ig & j is connected to ig
	int ja =  j>ig?j:ig;
	ig = iga;
	j = ja;
	bool areDisjoint = 0;
	if(ig == 1) return true;//Logville has disjoint water supply with every other city.
	if(ig  == j) return false;//The path from j to 1 has ig in it.
	//if(j < ig) return true;//Priority: knowing that if j<ig then ig is not in the j->1 path
	if (connections [ig][j] != -1 ) return connections[ig][j];
	for(int i = 0; i < rgraph[j].size() ; i++ ){
			if ( areDisjoint = disj(ig,rgraph[j][i]) )
			{
					areDisjoint = true;
					break;
					
			}
	}
	connections[ig][j] = areDisjoint;
    return areDisjoint;
}

void printint(int a){
	cout<<a<<endl;
}
void printg(int nodes){
	for(int i = 1; i < nodes ; i++){
		cout << "\n";
		for(int j = 1 ; j < nodes ; i++){
			cout << rgraph[i][j];
		}
	}
}

void clearG(){
	for(int i = 0 ; i < MAX_NODES; i++){
		rgraph[i].clear();
	}
}

int main(){
	int nodes, pipes;
	while(scanf("%d %d",&nodes,&pipes) != EOF){
		clearG();
		memset(connections,-1,sizeof(connections));
		for(int i = 0 ; i < pipes; i++){
			int w , v ; 
			cin >>  v  >> w;
			rgraph[w].push_back(v);
		}
		int disjoint = nodes-1;
		for(int i = 2 ; i <= nodes; i++){
			for(int j = i+1; j <= nodes ; j++){
				//disjoint = disj(i,j)?disjoint+1:disjoint;
				if(disj(i,j)){
					disjoint++;
				}
			}
		}
		cout << disjoint <<endl;
		
	}
}

