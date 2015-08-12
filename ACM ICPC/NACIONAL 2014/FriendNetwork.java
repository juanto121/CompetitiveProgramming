import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


class FriendNetwork {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLine()){
			String graphDesc[] = scan.nextLine().split(" ");
			int nodes = Integer.parseInt(graphDesc[0]);
			ArrayList<Integer> g[] = new ArrayList[nodes];
			int degree_rev[] = new int[nodes];
			int degree[] = new int[nodes];
			
			for(int i = 0; i < nodes; i++){
				degree_rev[i] = Integer.parseInt(graphDesc[i+1]);
			}
					
			degree = sort_rev(degree_rev,nodes);
			
			for(int u = 0; u < nodes; u++){
				int deg = degree[0];
				for( int v = 1; v <= deg; v++){
						degree[v]--;
						degree[0]--;
				}
				degree = sort_rev(degree,nodes);
			}
			
			boolean possible = true;
			for(int i = 0; i < nodes; i++){
				if(degree[i] != 0){
					possible = false;
					break;
				}
			}
		
		System.out.println(possible?1:0);				
		}
		
	}
	
	public static int[] sort_rev(int deg[], int nodes){
		Arrays.sort(deg);
		int degree[] = new int[nodes];
		for(int j = 0; j < nodes; j++){
			degree[j] = deg[nodes-j-1];
		}
		return degree;
	}

}