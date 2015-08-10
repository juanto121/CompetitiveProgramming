import java.util.Scanner;


public class bus {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		int stops[] = new int[31];
		stops[0] = 1;
		for(int t = 0; t < cases; t++){
		for(int i = 1; i < 30; i++){
			stops[i] = (2<<i)-1;
		}
		
		System.out.println(stops[scan.nextInt()-1]);
		}

	}

}
