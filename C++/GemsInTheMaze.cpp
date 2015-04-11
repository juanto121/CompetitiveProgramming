#include<iostream>
#include<cstdio>
#include<cstring>

using namespace std;

int main(){
	
	
	int maximum=0;
	int cases;
	int n;
	int a,b,c;
	scanf("%d",&cases);
	for(int y = 0;y<cases;y++){
	scanf("%d %d %d %d",&a,&b,&c,&n);
	int gemsPerNode[n];
		//Eg. Si empiezo en el 35 y se que desde ahi se pueden obtener 3 Gems cuando llegue al 35 sumar lo que llevo, ver si esta visitado y guardar Cuantas se pueden obtener desde ese nodo.
		for(int i = 0;i<n;i++){
			int numGems=1;
			int visited[n];
			memset(visited,1,n);//1 es no visitado
			bool flagCyc;// i.e. v!=i.
			int v = (a*i*i+b*i+c)%n;
			
			while(v!=i && visited[v])
				{
					
					v=(a*i*i+b*i+c)%n;
					++numGems;
					visited[v]=0;
					
				}
			if(!visited[v]){
			numGems+=gemsPerNode[v];
			gemsPerNode[i] = numGems;
			}
			
			maximum = maximum>numGems?maximum:numGems;
			
			cout<<maximum<<endl;
		}
}
	
	
}
