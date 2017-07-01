import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class C {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter wt = new PrintWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(bf.readLine());
		
		Stack<Integer> s = new Stack<>();
		int next = 1;
		int reorder = 0;
		int justReordered = 0;
		for(int c = 0; c < 2*n; c++){
			String command[] = bf.readLine().split(" ");
			if(command[0].equals("add")){
				int box = Integer.parseInt(command[1]);
				s.add(box);
				if(box != next) justReordered = 0;
				wt.println("added : "+box);
			}else{
				if(justReordered == 0 && s.peek()!=next){
					reorder++;
					wt.println("reordered : "+next);
					justReordered = s.size();
				}
				if(justReordered > 0 || s.peek() == next){
					s.pop();
					wt.println("removed : "+next);
					next++;
					if(justReordered>0)justReordered--;
				}
			}
		}
		wt.println(reorder);
		wt.flush();
	}

}
