import java.math.BigInteger;
import java.util.Scanner;

public class JamCoin {
	
	public static final long INNER = 32_768;
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int T = s.nextInt();
		int N = s.nextInt();		int J = s.nextInt();
		int count = 0;
		System.out.println("Case #1:");
		for(int i = 0; i < INNER ; i++){
			if(count < J){
				String in = Integer.toBinaryString(i);
				int padding = N-in.length()-2;
				String coin = "1"+String.format(pad(padding)+"%s", in) + "1";
				int p = 0;
				String interpretations[] = new String[11];
				if(coin.charAt(0) == '1'){
					String interpretation = "";
					for(int b = 2; b <= 10; b++){
						interpretation = base(coin, b);
						interpretations[b] = interpretation;
						//System.out.println(coin + " " + interpretation);
						if(!new BigInteger(interpretation).isProbablePrime(15)){
							p ++;
						}
					}
					if(p == 9){ 
						String divisors = findDivisors(interpretations);
						System.out.println(String.format("%s %s", coin, divisors));
						count++;
					}
					
				}
			}
		}
	}
		
	static BigInteger zero = new BigInteger("0");
	
	private static String findDivisors(String interpretations[]) {
		StringBuilder div = new StringBuilder();;
		
		for(int k = 2; k < interpretations.length; k++){
		BigInteger num = new BigInteger(interpretations[k]);
		
			if(!num.mod(new BigInteger("2")).equals(zero)){
				for(long i = 3; i < INNER; i+=2){
					BigInteger bigI = new BigInteger(""+i);
					if(num.mod(bigI).equals(zero) && !bigI.equals(num)){ 
						div.append(String.valueOf(i)+" ");
						//System.out.println(k + " "+ i + " " +num + " " +num/i);
						break;
					}
				}
			}else{div.append("2 ");}
		}
		return div.toString();
	}


	public static String pad(int padding){
		StringBuilder stb = new StringBuilder();
		for(int i = 0; i < padding; i++)
		stb.append("0");
		return stb.toString();
	}
	
	public static String base(String coin, int b){
		int len = coin.length(); // 16 or 32
		BigInteger num = new BigInteger("0");
		long pow = 1;
		for(int i = len-1; i >= 0 ; i--){
			String val = String.valueOf((coin.charAt(i)-'0')*pow);
			num.add(new BigInteger(val));
			pow *= b;
		}
		return String.valueOf(num);
	}
}
