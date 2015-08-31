import java.math.BigInteger;
import java.util.Scanner;
public class primes {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String num = scan.nextLine();
		String rev;
		int len = num.length();
		StringBuilder stb = new StringBuilder();
		boolean wrong = false;
		for(int i = 0; i < len; i++){
			char c = num.charAt(i);
			if(c == '3' || c == '4' || c == '7'){
				wrong = true;
				System.out.println("no");
				break;
			}
			if(c=='9') c = '6';
			else
				if(c=='6') c = '9';
			stb.append(c);
		}
		if(!wrong){
			boolean good = false;
			rev = stb.toString();
			long lnum = Long.parseLong(num);
			long rnum = Long.parseLong(stb.reverse().toString());
			if( isPrime(lnum) && isPrime(rnum)) 
				System.out.println("yes");
			else
				System.out.println("no");
						
		}
	}
	
	public static boolean isPrime(long n){ 
		BigInteger bi = new BigInteger(n+"");
		return bi.isProbablePrime(20);
	}
}
