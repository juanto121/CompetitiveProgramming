import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class WeakLinks {
	
	static int numBridges;
	static ArrayList<Integer> g[];
	static boolean seen[];
	static int discovered[];
	static int low[];
	static int parent[];
	static int time = 0;
	static ArrayList<Bridge> bridges;
	
	public static void main(String[] args) throws Exception{
		boolean first = true;
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			String in[] = scan.readLine().split(" ");
			
			int n = Integer.parseInt(in[0]);
			int m = Integer.parseInt(in[1]);
			
			if(n == 0 && m == 0)break;
			
			g = new ArrayList[n];
			seen = new boolean[n];
			low = new int[n];
			discovered = new int[n];
			parent = new int[n]; Arrays.fill(parent, -1);
			numBridges = 0;
			bridges = new ArrayList<Bridge>();
			time = 0;
			
			for(int i = 0; i < n ; i++) g[i] = new ArrayList<Integer>();
			
			for(int i = 0 ; i < m; i++){
				in = scan.readLine().split(" ");
				int u = Integer.parseInt(in[0]);
				int v = Integer.parseInt(in[1]);
				
				g[u].add(v);
				g[v].add(u);
				
			}
	
			for(int u = 0; u < n; u ++){
				if(!seen[u]){
					dfs(u);
				}
			}
			StringBuilder stb = new StringBuilder();

			stb.append(numBridges);
			Collections.sort(bridges);
			int blen = bridges.size();
			for(int i = 0; i < blen; i++){
				stb.append(" ");
				stb.append(bridges.get(i));
			}
			System.out.println(stb.toString());
		}
		
	}

	private static void dfs(int u) {
		seen[u] = true;
		discovered[u] = low[u] = ++time;
		int adyLen = g[u].size();
		for(int i = 0; i < adyLen; i++){
			int v = g[u].get(i);
			if(!seen[v]){
				parent[v] = u;
				dfs(v);
				 
				//Subtree with root v is connected to ancestor of u.
				low[u] = Math.min(low[u], low[v]);
				
				if(low[v] > discovered[u]){
					// Swap this M*fckers!
					bridges.add(new Bridge(Math.min(u,v),Math.max(u,v)));
					numBridges++;
				}	
			}else if(v != parent[u]){
				//update low value of current node with earliest discovered vertex, visited through back edge.
				low[u] = Math.min(low[u], discovered[v]);
			}
		}
	}

}

class Bridge implements Comparable<Bridge>{

	int u;
	int v;
	
	Bridge(int u, int v){
		this.u = u;
		this.v = v;
	}
	
	@Override
	public int compareTo(Bridge o) {
		if(u == o.u)
			return v - o.v;
		else
			return u - o.u;
	}
	
	public String toString(){
		return u + " " + v;
	}

}
