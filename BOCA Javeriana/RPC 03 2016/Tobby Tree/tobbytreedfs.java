import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class tobbytree {

	
	static ArrayList<Integer>[] g;
	static int houses[];
	static boolean seen[];
	static int parent[];
	static ArrayList<Integer> elements;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		String line[] = br.readLine().split(" ");
		houses = new int[n];
		g = new ArrayList[n];
		
		
		for(int i=0; i<n; i++){
			g[i] = new ArrayList<Integer>();
			houses[i] = Integer.parseInt(line[i]);
		}
		for (int i = 0; i < n-1; i++){
			String uv[] = br.readLine().split(" ");
			int u = Integer.parseInt(uv[0]);
			int v = Integer.parseInt(uv[1]);
			g[u].add(v);
			g[v].add(u);
		}
		int q = Integer.parseInt(br.readLine());

		for(int j = 0; j < q; j++){
			seen = new boolean[n];
			parent = new int[n];
			elements = new ArrayList<Integer>();
			
			String qu[] = br.readLine().split(" ");
			int t = Integer.parseInt(qu[0]);
			int u = Integer.parseInt(qu[1]);
			int v = Integer.parseInt(qu[2]);
			
			parent[u] = -1;
			
			if (t == 1){
				int source = u;
				dfs(u, v);
				int temp = v;
				while(temp!=-1){
					elements.add(houses[temp]);
					temp = parent[temp];
				}
				System.out.println(play());
			}else{
				houses[u] = v;
			}
		}
	}
	
	//elements.add(houses[u]);
	
	private static int play() {
		
		int n = elements.size();
		int res = elements.get(0);
		for(int i = 1; i < n; i++){
			res = gcd(res, elements.get(i));
		}
		return res;
	}

	public static void dfs(int u, int t){
		seen[u] = true;
		int adyLen = g[u].size();
		if(u != t)
		for(int i = 0; i < adyLen; i++){
			int v = g[u].get(i);
			if(!seen[v]){
				parent[v] = u;
				dfs(v, t);
			}
		}
	}
	
	public static int gcd(int a, int b){
		while(b != 0){
			int t = b;
			b = a%b;
			a = t;
		}
		return Math.abs(a);
	}
}
