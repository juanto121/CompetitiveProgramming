package DIV2_574;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BearAndElections {

	public static void main(String[] args) {
		PriorityQueue<Integer> a = new PriorityQueue<Integer>();
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int n = Integer.parseInt(scan.readLine());
			StringTokenizer str = new StringTokenizer(scan.readLine());
			int votes = Integer.parseInt(str.nextToken());
			for(int i = 1; i < n; i++){
				int v = Integer.parseInt(str.nextToken());
				if(votes<=v)
					a.add(-1*v);
			}
			while(a.isEmpty()){
				
			}
			
		} catch (IOException e){}
		
		
		
	}

}
