import java.util.Scanner;

public class basicgeometry {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextDouble()){
			double a = scan.nextDouble();
			double b = scan.nextDouble();
			if(a == 0 || b == 0){
				System.out.println(String.format("%.3f", 0.0));
			}else{
				double r = a + b;
				double h = a - b;
				double d = Math.sqrt(r*r- h*h);
				double res = Math.sqrt(r*r - d*d)*d - 2*d*(a-b) + (r*r/2) * Math.asin(d/r) - (r*r/2) * Math.asin(-d/r);
				System.out.println(String.format("%.3f", res - Math.PI*b*b));
			}
		}
	}

}
