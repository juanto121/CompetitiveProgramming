package DIV2_299;

import java.util.Scanner;

public class A {

	public static void main(String[] args) {
		String units[] = {"zero","one","two","three","four","five","six","seven","eight","nine"};
		String teens[] = {"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
		String decs[] = {"","","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
		
		Scanner scan = new Scanner(System.in);
		int num = Integer.parseInt(scan.nextLine());

		int deci = num/10;
		int unit = num%10;
		if(deci>0){
			if(deci<2){
				System.out.println(teens[unit]);
			}else{
				System.out.println(decs[deci]+(unit==0?"":"-"+units[unit]));
			}
		}else{
			System.out.println(units[unit]);
		}
		
		
	}

}
