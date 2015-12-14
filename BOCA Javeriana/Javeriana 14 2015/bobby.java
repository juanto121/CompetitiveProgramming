import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class bobby {

	static long fact[] = new long[22];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		factorial();
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(scan.readLine());
		for(int t = 0; t < N; t++){
			String params[] = scan.readLine().split(" ");
			int r = Integer.parseInt(params[0]);
			int s = Integer.parseInt(params[1]);
			int x = Integer.parseInt(params[2]);
			int y = Integer.parseInt(params[3]);
			int w = Integer.parseInt(params[4]);
			double p = (s - r  + 1.0)/s;
			double sum = 0.0;
			for(int i = x; i <= y; i++){
				double res = binomial(i,y,p);
				//System.out.println(String.format("bin: (%d,%d) = %f",i,y,res));
				sum += res;
			}
//			System.out.println(sum);
			if( w*sum > 1.0){
				System.out.println("yes");
			}else{
				System.out.println("no");
			}
		}
	}
	
	private static void factorial(){
		fact[0] = 1;
		for(int i = 1; i < 22; i++){
			fact[i] = i * fact[i-1]; 
		}
	}
	private static double binomial(int r, int n, double p) {
		double a = choose(n, r);
		double b = Math.pow(p, r);
		double c = Math.pow((1 - p), n - r);
		return a * b * c;
	}

	public static double choose(int n, int r){
		return fact[n]/(fact[(n-r)]*fact[r]);
	}

}
