import java.util.Arrays;
import java.util.Scanner;

public class TriangleCountCCPL2013 {
	static int MAXN = 501;
	static int t[] = new int[MAXN];
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Arrays.fill(t, -1);
		while(scan.hasNextInt()){
			System.out.println(triangles(scan.nextInt()));
		}
	}
	
	public static int triangles(int n){
		if(t[n]!=-1)return t[n];
		if(n == 1){
			return 1;
		}else{
			t[n] = up(n)+down(n)+triangles(n-1);
			return t[n];
		}
	}

	private static int down(int n) {
		int sum = 0;
		if(n % 2 == 0){
			for(int i = 1; i < n; i+=2){
				sum += i;
			}
		}else{
			for(int i = n-1; i > 0; i-=2){
				sum += i;
			}
		}
		return sum;
	}

	private static int up(int n) {
		return n*(n+1)/2;
	}

}
