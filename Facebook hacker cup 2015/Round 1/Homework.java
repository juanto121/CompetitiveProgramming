import java.util.Scanner;
import java.util.StringTokenizer;



public class Homework {

	static int MAXB = 10000005;
	static long primes[] = new long[MAXB];

	public static void main(String[] args) {
		genPrimes();
		Scanner scan = new Scanner (System.in);
		int cases = Integer.parseInt(scan.nextLine());
		StringTokenizer str;
		for(int t = 0; t < cases; t++){
			str = new StringTokenizer(scan.nextLine());
			int a = Integer.parseInt(str.nextToken());
			int b = Integer.parseInt(str.nextToken());
			int k = Integer.parseInt(str.nextToken());
			int count = 0;
			for(int i = a ; i <= b; i++){
				if(primes[i]==k){
					count ++;
				}
			}
			System.out.println(String.format("Case #%d: %d", t+1, count));
		}
		
		scan.close();
		
		}
	public static void genPrimes(){
		primes[0] = 0;
		primes[1] = 0;
		for(int i = 2; i<MAXB;i++){
			if(primes[i]==0)
			for(int j = i; j<MAXB;j+=i){
				primes[j]++;
			}
		}
	}

}
