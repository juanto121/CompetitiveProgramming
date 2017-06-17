import java.util.Scanner;

public class A {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		for(int t = 0; t < cases; t++){
			double d = scan.nextDouble();
			double n = scan.nextDouble();
			double slowpokehrs = 0;  
			for(int i = 0; i < n; i++){
				double k = scan.nextDouble();
				double s = scan.nextDouble();
				double hrsleft = (d-k)/s;
				
				if(hrsleft > slowpokehrs){
					slowpokehrs = hrsleft;
				}
			}
			double res = d/slowpokehrs;
			System.out.println(String.format("Case #%d: %.6f", t+1, res));
		}
	}

}
