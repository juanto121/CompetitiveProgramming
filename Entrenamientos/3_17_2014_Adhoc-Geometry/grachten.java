import java.awt.geom.Line2D;
import java.util.Scanner;


public class grachten {

	public static int gcd(int a, int b){
		if(b == 0)return a;
		else{
			return gcd(b,a%b);
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLine()){
			String abc[] = scan.nextLine().split(" ");
			int a = Integer.parseInt(abc[0]); int b = Integer.parseInt(abc[1]); int c=Integer.parseInt(abc[2]);
			int num = a*b;
			int den = c-b;
			int g = gcd(num,den);
			System.out.println(num/g+"/"+den/g);
		}

	}

}
