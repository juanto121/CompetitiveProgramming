import java.util.Arrays;
import java.util.Scanner;

public class B {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		for(int t = 0 ; t < cases; t++){
			String res = "";
			int n = scan.nextInt();
			int p = scan.nextInt();					
			int realQty[] = new int[n];
			for(int i = 0; i < n; i++){
				realQty[i] = scan.nextInt();
			}
			int packages[][] = new int[n][p];
			for(int pq= 0; pq<n; pq++){
				for(int q = 0; q < p; q++){
					packages[q][pq] = scan.nextInt();
				}
				Arrays.sort(packages[pq]);
			}
			
			System.out.println(String.format("Case %d:%s", t+1, res));
		}
	}

}
