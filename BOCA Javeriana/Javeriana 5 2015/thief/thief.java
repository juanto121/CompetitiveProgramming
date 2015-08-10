import java.util.Scanner;


public class thief {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int cases = Integer.parseInt(scan.nextLine());
		for(int t = 0; t < cases ; t++){
			String line[] = scan.nextLine().split(" ");
			int n = Integer.parseInt(line[0]);
			int m = Integer.parseInt(line[1]);
			
			System.out.println( n*(m-n) + n*(n-1)/2 );
		}

	}

}
