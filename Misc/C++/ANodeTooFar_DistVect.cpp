# include <iostream>
# include <queue>
# include <cstdio>
# include <map>
# include <cstring>


using namespace std;

const int MAX = 33;
map<int,int> nodes;
int graph[MAX][MAX];
bool visited[MAX];
int dist[MAX];

void bfs(int from,int TTL,int tnodes){
	memset(visited,false,sizeof(visited));
	memset(dist,-1,sizeof(dist));
	queue<int> q;
	q.push(from);
	visited[from] = true;
	int dista = 0; 
	dist[from] = dista;
	while(!q.empty()){
		int v = q.front();
		q.pop();
		dista++;
		for(int i = 0 ; i < tnodes ; i++){
			if(graph[v][i] == -1) continue;
			if( !visited[i] ){
				visited[i] = true;
				q.push(i);
				dist[i] = dista;
			}
			
		}	 
	}

}

void printd(){
	for(int i = 0; i < 30; i++) cout << dist[i] << " ";
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
			
			int reach = 1;
			cin >> from >> ttl;
			if(from == 0) break;
			else{
				bfs(nodes[from],ttl,tnodes);
				++cases;
				for(int y = 0 ; y< tnodes ; y++){
					if(dist[y] >= 0 && dist[y] <= ttl) reach++;
				}
				printf("Case %d: %d nodes not reachable from node %d with TTL = %d.\n",cases,reach,from,ttl);
			}
		}
	}
	return 0;
}



