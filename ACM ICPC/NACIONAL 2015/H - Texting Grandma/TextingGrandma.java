package maraton_nacional_2015;

/*

								INCOMPLETE

*/

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class TextingGrandma {

	static ArrayList<Integer>g [];
	static int count[];
	static int leaves[];
	static int parents[];
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(new InputStreamReader(System.in));
		while(scan.hasNextLine()){
			String desc[] = scan.nextLine().split(" ");
			int n = desc.length + 2;
			g = new ArrayList[n+2]; 
			Arrays.fill(g, new ArrayList());
			count = new int[n+2];
			leaves = new int[n+2];
			parents = new int[n+2];
			
			int leave = 0;
			for(int i = 0; i < n - 2; i++){
				int parent = Integer.parseInt(desc[i]);
				parents[i] = parent; 
				g[parent].add(leave++);
				count[parent] ++;
			}
			
			removeLeaves(n);
		}
	}
	
	public static void removeLeaves(int n){
		
	}
}

class node implements Comparable<node>{
	int vertex;
	int size;
	public node(int v){
		vertex = v;
	}
	@Override
	public int compareTo(node that) {
		return this.size - that.size;
	}
}

