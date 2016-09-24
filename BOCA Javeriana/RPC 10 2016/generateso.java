import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;


public class generateso {
	static int a,c,m,x,q,n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String params [] = bf.readLine().split(" ");
		a = Integer.parseInt(params[0]);
		c = Integer.parseInt(params[1]);
		m = Integer.parseInt(params[2]);
		x = Integer.parseInt(params[3]);
		q = Integer.parseInt(params[4]);
		n = Integer.parseInt(params[5]);
		
		ArrayList<Integer> sq = generateSequence();
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int i = 0; i < q; i++){
			int res;
			int query = Integer.parseInt(bf.readLine()) -1 ;
			if(query!=n)
				 res = sq.get(query%n);
			else
				res = sq.get(query);
			
			bw.write(res+"\n");
		}
		bw.flush();
	}
	
	private static ArrayList<Integer> generateSequence() {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		
		boolean complete = false;
		int index = 0;
		nums.add(rnd(index,0));
		int first=x;
		boolean start = false;
		int partialSize = -1;
		for(int cur = 1; cur <= m && !complete; cur++){
			int ni = nums.get(cur-1);
			int next = rnd(cur-1,ni);
			
			if(next == first){
				start = true;
			}
			
			if(start){
				if(ni != nums.get(index)){
					index = 0;
					partialSize = -1;
				}else{
					if(partialSize == -1) partialSize = nums.size();
					if(index == partialSize){
						complete = true; 
					}
					index++;
				}
			}
			
			nums.add(next);
			
		}
		
		if(partialSize == -1)partialSize = nums.size();
		Collections.sort(nums.subList(0, partialSize));
		
		return nums;
	}

	public static int rnd(int n, int prev){
		if(n == 1) return x;
		return (a*prev+c)%m; 
	}

}
