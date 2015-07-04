import java.util.Scanner;


public class DIV2_310_ZerosOnes {

	public static void main(String[] args) {
		Scanner scan = new Scanner (System.in);
		int len = Integer.parseInt(scan.nextLine());
		String str = scan.nextLine();
		int zeroCount = 0;
		int oneCount  = 0;
		for(int i = 0; i < len; i++){
			int c = str.charAt(i)-'0';
			
			if(c == 0){
				zeroCount++;
			}else{
				oneCount++;
			}
		}
		System.out.println(Math.abs(zeroCount-oneCount));		
	}

}
