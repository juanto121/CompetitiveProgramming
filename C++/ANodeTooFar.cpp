# include <iostream>
# include <queue>
# include <cstdio>
# include <map>
# include <cstring>


using namespace std;

map<int,int> nodes;
int graph[30+3][30+3];
bool visited[30+3];

int bfs(int from,int TTL,int tnodes){
	int added = 1;
	int reachable = 0;
	memset(visited,false,sizeof(visited));
	queue<int> q;
	q.push(from);
	visited[from] = true;
	while(!q.empty()){
		if(added == 0){
			TTL--; added = q.size();
		}
		int v = q.front(); 
		int s = q.size();
		q.pop();
		added--;
		for(int i = 0 ; i < tnodes ; i++){
			if(graph[v][i] == -1) continue;
			if(TTL == 0) return reachable;
			if( !visited[i] ){
				visited[i] = true;
				q.push(i);
				reachable++;
			}
		}	 
	}
	return reachable;
}


int main(){
	int edges; int cases = 0;
	while(scanf("%d",&edges)!=0){
		int index = 0;
		nodes.clear();
		memset(graph,-1, sizeof(graph) );
		int v, w;
		int tnodes = 0;
		for(int i = 0; i < edges ; i++){
			cin >> v >> w;
			if(nodes.find(v) == nodes.end()){
				nodes[v] = index++;
				tnodes++;
			}
			if(nodes.find(w) == nodes.end()){
				nodes[w] = index++;
				tnodes++;
			}
			if(graph[nodes[v]][nodes[w]] == -1){
				graph[nodes[v]][nodes[w]] = 1;
				graph[nodes[w]][nodes[v]]  = 1;
			}
		}		
		int from = 0; 
		int ttl = 0;
		while(true){
		
			cin >> from >> ttl;
			if(from == 0) break;
			else{
				int reach = bfs(nodes[from],ttl,tnodes);
				++cases;
				printf("Case %d: %d nodes not reachable from node %d with TTL = %d.\n",cases,tnodes-reach-1,from,ttl);
			}
		}
	}
	return 0;
}



