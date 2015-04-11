import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner (System.in);
		while(scan.hasNextInt()){
			int n, p;
			n = scan.nextInt();
			if(n == 0)break;
			p = scan.nextInt();
			int result[] = new int[3];
			
			if(p%2==0){
				
				result[0] = p-1; result[1] = n-p+1;result[2] = n-p+2;
			}else{
				
				result[0] = p+1; result[1] = n-p;result[2] = n-p+1;
			}
			
			Arrays.sort(result);
			
			System.out.println(result[0] + " " + result[1] + " "+ result[2]);
			
			
			
		}
	}

}
