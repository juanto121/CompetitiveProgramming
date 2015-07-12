import java.util.Arrays;
import java.util.Scanner;


public class lode {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		int pow[] = new int[15];
		Arrays.fill(pow, 1);
		for(int i = 1; i <= 14; i++)pow[i]*=pow[i-1]*3;
		
		for(int i = 0; i < cases ; i++){
			int total = scan.nextInt();
			int tto = total;
			int max = (int) (Math.log(total)/Math.log(3));
			
			int res[] = new int[max+1];
			Arrays.fill(res, 0);
			int currentIndex = max;
			int current = pow[max];
			
			while( true ){
				if(tto<=0)break;
				if(tto-current>=0){
					tto -= pow[currentIndex];
					res[max-currentIndex]++;
				}else{
					while(tto <  current){
						currentIndex --;
						current = pow[currentIndex];
					}
				}
				
			}
			for(int k = 0; k < max+1; k++){
				if(k == 0) System.out.print(res[k]);
				else System.out.print(" "+res[k]);
			}
				
			System.out.println("");
		}
	}

}
