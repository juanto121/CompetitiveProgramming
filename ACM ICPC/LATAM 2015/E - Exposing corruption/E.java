/*

The Mighty Non Polynomial Squids

*/
package maraton_latam_2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class E {

	static ArrayList<Integer> g[];
	static boolean visited[];
	static int cost[];
	static ArrayList<Integer> weights;
	static ArrayList<Integer> valuesP;
	static ArrayList<Integer> valuesD;
	static int components;
	static int d, p;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String desc;
		
		while((desc = br.readLine()) != null){
			String s[] = desc.split(" ");
			d = Integer.parseInt(s[0]);
			p = Integer.parseInt(s[1]);
			int r = Integer.parseInt(s[2]);
			int b = Integer.parseInt(s[3]);
			
			cost = new int[d+p];
			g = new ArrayList[d+p];
			visited = new boolean[d+p];
			
			s = br.readLine().split(" ");
			for(int i = 0; i < d; i++){
				cost[i] = Integer.parseInt(s[i]);
				g[i] = new ArrayList<Integer>();
			}
			
			s = br.readLine().split(" ");
			for(int i = 0; i < p; i++){
				cost[d+i] = Integer.parseInt(s[i]);
				g[d+i] = new ArrayList<Integer>();
			}
			
			for(int i = 0; i < r; i++){
				s = br.readLine().split(" ");
				int x = Integer.parseInt(s[0]) - 1;
				int y = Integer.parseInt(s[1]) - 1;
				g[x].add(d+y);
				g[d+y].add(x);
			}
			
			components = 0;
			weights = new ArrayList<>();
			valuesD = new ArrayList<>();
			valuesP = new ArrayList<>();
			
			for(int i = 0; i < d+p; i++){
				if(!visited[i]){
					bfs(i);
					components++;
				}
			}
			
			int AD[][] = new int[components + 1][b + 1];
			int AP[][] = new int[components + 1][b + 1];
			
			for(int i = 1; i <= components; i++){
				for(int x = 0; x <= b; x++){
					if(weights.get(i-1) > x){
						AD[i][x] = AD[i-1][x];
					}else{
						int g = AD[i-1][x];
						int h = AD[i-1][x-weights.get(i-1)] + valuesD.get(i-1);
						AD[i][x] = (g > h) ? g : h;
					}
				}
			}
			
			for(int i = 1; i <= components; i++){
				for(int x = 0; x <= b; x++){
					if(weights.get(i-1) > x){
						AP[i][x] = AP[i-1][x];
					}else{
						int g = AP[i-1][x];
						int h = AP[i-1][x-weights.get(i-1)] + valuesP.get(i-1);
						AP[i][x] = (g > h) ? g : h;
					}
				}
			}
			
			int finalD = AD[components][b] + d;
			int finalP = AP[components][b] + p;
			
			System.out.println(finalD + " " + finalP);
			
		}
		
	}

	private static void bfs(int s) {
				Queue<Integer> q = new LinkedList<>();
				visited[s] = true;
				q.add(s);
				
				int actualD = 0;
				int actualP = 0;
				
				int valor = 0;
				
				while(!q.isEmpty()){
					s = q.poll();
					if(s < d){
						actualD++;
						valor += cost[s];
					}else{
						actualP++;
						valor += cost[s];
					}
					
					int adyLen = g[s].size();
					for(int i = 0; i < adyLen; i++){
						int v = g[s].get(i);
						if(!visited[v]){
							visited[v] = true;
							q.add(v);
						}
					}
				}
				
				weights.add(valor);
				valuesD.add(actualP - actualD);
				valuesP.add(actualD - actualP);
	}

}
