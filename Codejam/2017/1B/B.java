import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;

public class B {

	static ArrayList<uni> g[];
	static boolean seen[];
	static int parent[];
	static Queue<Integer> queue;
	static ArrayList<uni> unicorns;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int cases = 0;
		
		cases = Integer.parseInt(scan.nextLine());
		
		for(int t = 0; t <cases; t++){
			
			String desc[] = scan.nextLine().split(" ");
			int n = Integer.parseInt(desc[0]);

			initg(n);
			unicorns = new ArrayList<>();
			
			for(int i = 0; i < 6; i++){
				int col = Integer.parseInt(desc[i+1]);
				for(int j = 0; j < col; j++){
					uni u = new uni(i);
					unicorns.add(u);
				}
			}
			
			for(int i = 0; i < n; i++){
				uni u = unicorns.get(i);
				for(int j = i+1; j < n; j++){
					uni v = unicorns.get(j);
					if(u.color == 0){
						if(v.color == 2 || v.color == 4 || v.color == 3){
							g[u.id].add(v);
							g[v.id].add(u);
						}
					}
					if(u.color == 1){
						if(v.color == 4){
							g[u.id].add(v);g[v.id].add(u);
						}
					}
					if(u.color == 2){
						if(v.color == 0 || v.color == 5 || v.color == 4 ){
							g[u.id].add(v);g[v.id].add(u);
						}
					}
					if(u.color == 3){
						if(v.color == 0){
							g[u.id].add(v);g[v.id].add(u);
						}
					}
					if(u.color == 4){
						if(v.color == 0 || v.color == 2 || v.color == 1){
							g[u.id].add(v);g[v.id].add(u);
						}
					}
					if(u.color == 5){
						if(v.color == 2){
							g[u.id].add(v);g[v.id].add(u);
						}
					}
					
					
				}
			}
			for(int u = 0; u < n; u++){
				dfs(u);
				seen = new boolean[n];
				System.out.println("");
			}
			
		}
		
	}
	
	private static void dfs(int u){
		seen[u] = true;
		System.out.print(colorear(u));
		int adylen = g[u].size();
		for(int i = 0; i < adylen; i++){
			int v = g[u].get(i).id;
			if(!seen[v]){
				parent[v] = u;
				dfs(v);
			}
		}
	}

	private static String colorear(int u) {
		for(int i = 0; i < unicorns.size(); i++){
			uni un = unicorns.get(i);
			if(unicorns.get(i).id == u){
				switch(un.color){
				case 0: return "R";
				case 1: return "O"; 
				case 2: return  "Y";
				case 3: return "G";
				case 4: return  "B";
				case 5: return  "V";
				}
			}			
		}
		return "";
	}

	private static void initg(int nodes) {
		g = new ArrayList[nodes];
		for(int i = 0; i < nodes; i++){
			g[i] = new ArrayList<uni>();
		}
		seen = new boolean[nodes];
		parent = new int[nodes];
		Arrays.fill(parent, -1);
	}
	
	private static class uni{
		int color;
		int id;
		static int idgen = 0;
		public uni(int color){
			this.color = color;
			id = idgen++;
		}
		@Override
		public String toString() {
		 return id+"/"+color;
		}
		
	}

}
