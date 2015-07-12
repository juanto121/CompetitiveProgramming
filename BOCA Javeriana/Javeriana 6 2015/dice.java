import java.util.Scanner;


public class dice {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int g1_1 = scan.nextInt();		int g1_2 = scan.nextInt();
		int g2_1 = scan.nextInt();		int g2_2 = scan.nextInt();
		
		double gunnar = expectedValue(g1_1, g1_2) + expectedValue(g2_1, g2_2); 
		
		int e1_1 = scan.nextInt();		int e1_2 = scan.nextInt();
		int e2_1 = scan.nextInt();		int e2_2 = scan.nextInt();
		
		double emma = expectedValue(e1_1, e1_2) + expectedValue(e2_1, e2_2);
		String out = "";
		if(gunnar>emma){
			out = "Gunnar";
		}else{
			out = "Emma";
		}
		if(gunnar == emma) out = "Tie";
		System.out.println(out);
		
	}
	public static double expectedValue(int a, int b){
		return (1.0/(b-a+1))*sum(a,b);
	}
	
	public static int sum(int a, int b){
		a = a-1;
		return (b*(b+1))/2 - (a*(a+1))/2;
	}

}
