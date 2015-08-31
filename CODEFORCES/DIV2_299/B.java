package DIV2_299;

import java.util.Scanner;

public class B {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String number = (scan.nextLine());
		int o_l = number.length();
		int sum = 0;
		
		for(int i = 0; i < o_l; i++){
			int parc = 1<< o_l-i;
			sum += parc;
			if(number.charAt(i)-'0' == 4){
				parc/=2;
				sum-=parc;
			}
			
		}
		System.out.println(sum);
	}

}
