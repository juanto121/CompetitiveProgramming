import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class A {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(bf.readLine());
		for(int t = 0; t < cases; t++){
			int n = Integer.parseInt(bf.readLine());
			String pis[] = bf.readLine().split(" ");
			System.out.println(String.format("Case #%d: %s", t + 1, solve(n,pis)));
		}
	
	}

	private static String solve(int n, String [] pis) {
		PriorityQueue<partie> q = new PriorityQueue<>();
		for(int party = 0; party < n; party++){
			q.add(new partie( (char)(party+'A') +"" , Integer.parseInt(pis[party]))); 
		}
		StringBuilder stb = new StringBuilder();
			
		partie first = q.peek();
		while(q.size()>2){
			stb.append(first.name+" ");
			first.qty--;
			q.poll();
			if(first.qty > 0)
				q.add(first);
			
			first = q.peek();
		}
		
		first = q.poll();
		partie second = q.poll();
		
		while(first.qty > 0 && first.qty == second.qty){
			stb.append(first.name+second.name+" ");
			first.qty--;
			second.qty--;
		}
		
		while(first.qty > second.qty){
			first.qty--;
			stb.append(first.name+" ");
		}
		
		while(second.qty > first.qty){
			second.qty--;
			stb.append(second.name+" ");
		}
		
		
		
		return stb.toString();
	}


}

class partie implements Comparable{
	public String name;
	public int qty;
	public partie(String n, int q){
		name = n;
		qty = q;
	}
	@Override
	public int compareTo(Object o) {
		return Integer.compare(((partie)o).qty,qty );
	}
	@Override
	public String toString(){
		return name + " " + qty;
	}
}
