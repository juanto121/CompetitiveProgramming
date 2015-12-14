import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;


public class cantina {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = Integer.parseInt(scan.nextLine());
		
		Guy characters[] = new Guy[N];
		
		//langMap: language -> list of guys who understand <key> language
		HashMap<String, List<Guy>> langMap = new HashMap<>();
		
		List<Integer>[] g = new List[N];
		for(int i = 0;i < N; i++) g[i] = new ArrayList<>();
		
		for(int c = 0; c < N; c++){
			String line[] = scan.nextLine().split(" ");
			String character = line[0];
			String lang = line[1];
			
			Guy guy = new Guy(character, lang, c);
			characters[c] = guy;
			
			for(int i = 1; i < line.length; i++){
				String und = line[i];
				
				if(langMap.containsKey(line[i])){
					langMap.get(und).add(guy);
				}else{
					ArrayList<Guy> guyList = new ArrayList<>();
					guyList.add(guy);
					langMap.put(und, guyList);
				}
			}
		
		}
		for(int i = 0; i < N; i++){
			
			if(langMap.containsKey(characters[i].speaks)){
				List<Guy> converse = langMap.get(characters[i].speaks);
				int len = converse.size();
				for(int k = 0; k < len; k++){
					Guy u = converse.get(k);
					if(u.index != characters[i].index)
						g[i].add(u.index);
				}
			}
		}
		
		List<List<Integer>> components = new SCCTarjan().scc(g);
		//System.out.println(components);
		
		int clen = components.size();
		int max = 0;
		for(int p = 0; p < clen; p++){
			int comsize = components.get(p).size();
			max = comsize > max ? comsize : max; 
		}
		
		System.out.println(N - max);
	}

}

class Guy{
	String name;
	String speaks;
	int index;
	
	Guy(String n, String lang, int i){
		name = n;
		speaks = lang;
		index = i;
	}

	@Override
	public String toString() {
		return String.format("(%s, %s, %d)", name,speaks,index);
	}
	
	
	
}

class SCCTarjan {
	List<Integer>[] graph;
	boolean[] visited;
	Stack<Integer> stack;
	int time;
	int[] lowlink;
	List<List<Integer>> components;

	public List<List<Integer>> scc(List<Integer>[] graph) {
		int n = graph.length;
		this.graph = graph;
		visited = new boolean[n];
		stack = new Stack<>();
		time = 0;
		lowlink = new int[n];
		components = new ArrayList<>();

		for (int u = 0; u < n; u++)
			if (!visited[u])
				dfs(u);

		return components;
	}

	void dfs(int u) {
		lowlink[u] = time++;
		visited[u] = true;
		stack.add(u);
		boolean isComponentRoot = true;

		for (int v : graph[u]) {
			if (!visited[v])
				dfs(v);
			if (lowlink[u] > lowlink[v]) {
				lowlink[u] = lowlink[v];
				isComponentRoot = false;
			}
		}

		if (isComponentRoot) {
			List<Integer> component = new ArrayList<>();
			while (true) {
				int x = stack.pop();
				component.add(x);
				lowlink[x] = Integer.MAX_VALUE;
				if (x == u)
					break;
			}
			components.add(component);
		}
	}
}
