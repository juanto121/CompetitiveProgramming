import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class eggs {
	
	static int MAX_VAL = 1_000_000;
	static int prime[] = new int[MAX_VAL + 1];
	static int res[] = new int[MAX_VAL + 1];

	public static void main(String[] args) throws IOException {
		generatePrimes();
		prime[1] = 1;
		Arrays.fill(res, -1);
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder stb = new StringBuilder(335*4);
		String line;
		while((line = bf.readLine()) != null){
			int N = Integer.parseInt(line);
			int savedN = N;
			int count = 0;
			//System.out.println(N);
			while(N != 0){
				if(res[N] != -1){
					count += res[N];
					break;
				}else{
					int batch = prime[N];
					N = N - batch;
					count ++;
				}
			}
			res[savedN] = count;
			stb.append(String.format("%d%n",count));
		}
		System.out.print(stb.toString());
	}
	
	public static void generatePrimes(){
		int n = MAX_VAL;
		Arrays.fill(prime, 2, n + 1, 1);
		
		// is prime if value == 1
		// else prime[i] is the largest prime that divides i
		
		for(int i = 2; i  <= n; i++){
			if(prime[i] == 1){
				for(int j = i; j <= n; j+= i){
					prime[j] = i;
				}
			}
		}
		//System.out.println("Done");
	}

}
