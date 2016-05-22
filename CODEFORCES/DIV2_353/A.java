import java.util.Scanner;

public class A {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		long a = scan.nextLong() + 1_000_000_001;
		long b = scan.nextLong() + 1_000_000_001;
		long c = scan.nextLong();
		
		long boff, coff;
		String res = "NO";
	
		boff = b - a;
		if(c == 0 || a == b){
			if(a==b) res = "YES";
		}else{
			if( (b < a && c < 0) || (b > a && c > 0) ){
				if(boff%c == 0){
					res = "YES";
				}
			}
		}
		
		System.out.println(res);
		
	}

}
