import java.util.Scanner;


public class PlayingPaper{
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		long a,b;
		String line[] = scan.nextLine().split(" ");
		a = Long.parseLong(line[0]);
		b = Long.parseLong(line[1]);
		long count = 0;
		long times = 0;
		while( b>0 && a>b && a!=b){
			times = a/b;
			count += times;
			a-=times*b;
			if(b>a){
				long tempa = a;
				a = b;
				b = tempa;
			}
			//System.out.println(a + " " + b);
			
		}
		System.out.println(count);
	}

}
