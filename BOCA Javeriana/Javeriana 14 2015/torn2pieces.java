import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class torn2pieces {
	
	static boolean seen[];
	static StringBuilder route;
	static ArrayList<node> g[];
	static HashMap<String, node> map; 
	static node parent[];

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = Integer.parseInt(scan.nextLine());
	
		map = new HashMap<>();
		g = new ArrayList[33]; for(int i =0 ; i < 33; i++)g[i] = new ArrayList<>();
		seen = new boolean[33];
		route = new StringBuilder();
		parent = new node[33];
		
		for(int t = 0; t < N; t++){
			String conn[] = scan.nextLine().split(" ");
			String uName = conn[0];
			node u = null;
			if(map.containsKey(uName)){
				u = map.get(uName);
			}else{
				u = new node(uName);
				map.put(uName, u);
			}
			
			for(int i = 1; i < conn.length; i++){
				String vName = conn[i];
				node v = null;
				if(map.containsKey(vName)){
					v = map.get(vName);		
				}else{
					v = new node(vName);
					map.put(vName, v);
				}
				if(!g[u.num].contains(v))g[u.num].add(v);
				if(!g[v.num].contains(u))g[v.num].add(u);
			}
			
		}
		String start_end[] = scan.nextLine().split(" ");
		if(map.containsKey(start_end[0]) && map.containsKey(start_end[1])){
			node start = map.get(start_end[0]);
			node end = map.get(start_end[1]);
		
		
		dfs(start);
		
		ArrayList<String> route = new ArrayList<String>();
		
		if(parent[end.num]==null){
			System.out.println("no route found");
		}else{
			route.add(end.name);
			while(!parent[end.num].name.equals(start.name)){
				route.add(parent[end.num].name);
				end = parent[end.num];
			}
			route.add(start.name);
			int l = route.size();
			StringBuilder res = new StringBuilder();
			for(int k = l - 1; k >= 0; k--){
				if(k==l - 1)res.append(route.get(k));
				else res.append(" " + route.get(k));
			}
			System.out.println(res);
		}
		}else{
			System.out.println("no route found");
		}
		
		
	}
	
	private static void dfs(node s){
		int u = s.num;
		seen[u] = true;
		int adyLen = g[u].size();
		for(int i = 0; i < adyLen; i++){
			node v = g[u].get(i);
			if(!seen[v.num]){
				parent[v.num] = s;
				dfs(v);
			}
		}
	}

}

class node{
	static int next = 0;
	String name;
	int num;
	public node(String name){
		this.name = name;
		num = next++;
	}
	public String toString(){
		return this.name+"-"+this.num;
	}
}