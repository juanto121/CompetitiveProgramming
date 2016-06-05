import java.util.Scanner


public class A{

	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int h = scan.nextInt();
		int sum = n;
		for(int i = 0; i < n; i++){
			int hi = scan.nextInt();
			if(hi>h){
				sum+=1;
			}
		}
		System.out.println(sum);
	}
}

