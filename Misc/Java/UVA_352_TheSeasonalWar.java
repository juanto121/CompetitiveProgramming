import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


public class UVA_352_TheSeasonalWar {

	static ArrayList<Integer> g[];
	static boolean seen[];
	static int cc[];
	
	public static void main(String[] args) {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String smapLen = null;
		try{
			int cases = 1;
			while( (smapLen = scan.readLine()) != null ){
				int mapLen = Integer.parseInt(smapLen);
				
				int picture[][] = new int[mapLen][mapLen];
				seen = new boolean[mapLen*mapLen];
				cc = new int[mapLen*mapLen];
				Arrays.fill(cc, -1);
				g = new ArrayList[mapLen*mapLen];
				
				for(int i = 0; i < mapLen; i++){
					String line = scan.readLine();
					for(int j = 0; j < mapLen; j++){
						picture[i][j]  = line.charAt(j)-'0';
					}
				}
				
				buildGraph(picture);
				int count = 0;
				int numV = mapLen*mapLen;
				for(int i = 0; i < numV; i++){
					if(!seen[i]){
						seen[i] = true;
						if(g[i]!=null){
							dfs(i,count);
							count++;
						}
					}
				}
				
				int eagles = count;
				System.out.println("Image number "+ cases++ + " contains " + eagles + " war eagles.");
				
			}
		}catch(IOException e){}
		
	}
	
	private static void dfs(int u, int count) {
		if(g[u]==null)return;
		int adySize = g[u].size();
		cc[u] = count;
		seen[u] = true;
		for(int v = 0; v < adySize; v++){
			int ady = g[u].get(v); 
			if( !seen[ady] ){
				dfs(ady,count);
			}
		}
	}

	public static void buildGraph(int picture[][]){
		int len = picture[0].length;
		for(int i = 0; i < len; i++){
			for(int j = 0; j < len; j++){
				if(picture[i][j]==1){
					int starty = i-1<0?i:i-1;
					int endy = i+1>=len?i:i+1;
					int startx = j-1<0?j:j-1;
					int endx = j+1>=len?j:j+1;
					int cindex = index(i,j,len);
					
					g[cindex] = new ArrayList<Integer>();
					
					for(int k = starty; k <= endy; k++){
						for(int r = startx; r <= endx; r++){
							int index = index(k, r,len);
							if(i == k && j == r)continue;
							if(picture[k][r]==1)
								g[cindex].add(index);
						}
					}
					
				}
			}
		}
	}
	
	public static int index(int i, int j, int len){
		return len*i + j;
	}

}
