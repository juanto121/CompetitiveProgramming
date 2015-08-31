package DIV2_AIMFUND;

import java.util.Scanner;

public class Arrays {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int na = scan.nextInt();
		int nb = scan.nextInt();
		int k = scan.nextInt();
		int m = scan.nextInt();
		int maxa = Integer.MIN_VALUE;
		int minb = Integer.MAX_VALUE;
		int maxb = Integer.MIN_VALUE;
		int minbi = 0;
		
		for(int i = 0; i < na; i++){
			int ai = scan.nextInt();
			if(i < k) maxa = ai;
		}
		
		for(int i = 0; i < nb; i++){
			int bi = scan.nextInt();
			if(bi>maxa){minb = bi; minbi = i; break;}
		}
		
		if(maxa < minb && m<=nb-minbi && minb<Integer.MAX_VALUE){
			System.out.println("YES");
		}else{
			System.out.println("NO");
		}
	}

}
