import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


class doll implements Comparable<doll>{
	int sz;
	int p;
	public doll(int s, int price){
		sz = s;
		p = price;
	}
	@Override
	public int compareTo(doll o) {
		if(this.sz == o.sz)
			return this.p - o.p;
		else
			return this.sz - o.sz;
	}
}

public class g {

	public static void main(String[] args) throws IOException {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int caso=1;
		while(true){
			String nk[] = scan.readLine().split(" ");
			
			int n = Integer.parseInt(nk[0]);
			int k = Integer.parseInt(nk[1]);
			if(n== 0 && n==k){
			 break;	
			}
			
			doll dolls[] = new doll[n];
			
			for(int t = 0; t < n; t++){
				String desc[] = scan.readLine().split(" ");
				int sz = Integer.parseInt(desc[0]);
				int p = Integer.parseInt(desc[1]);
				doll d = new doll(sz,p);
				dolls[t] = d;
			}
			
			Arrays.sort(dolls);

			long sum = 0;
			for(int i = 0; i < k-1; i++){
				sum += (long)dolls[i].sz;
			}
			double minp = Double.POSITIVE_INFINITY;
			for(int i = k-1; i < n; i++){
				double current = (sum+dolls[i].sz)*(double)dolls[i].p/(double)dolls[i].sz;
				if(current < minp) minp = current;
			}
			
			System.out.println(String.format("Case #%d: %.6f", caso, minp));
			caso++;
		}
	}

}
