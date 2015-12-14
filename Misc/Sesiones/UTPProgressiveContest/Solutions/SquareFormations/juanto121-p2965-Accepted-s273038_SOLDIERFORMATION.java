
import java.util.Scanner;

public class Main{

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		
		int maxSq = 10000;
		int maxN = 100000001;
		
		int squares[] = new int[maxSq];
		boolean isSquare[] = new boolean[maxN];
		
		
		for(int i = 0; i < maxSq; i++){
			int sq = i*i;
			squares[i] = sq;
			isSquare[sq] = true;
		}
		
		isSquare[0] = false;
		
		for(int t = 0; t < test; t++){
			int k = scan.nextInt();
			for(int h = 0; h < maxSq; h++){
				if(  (squares[h]-k)%2 == 0 && (squares[h]-k) > 0 && isSquare[ ((squares[h]-k)/2)+k ] ){
					System.out.println("Case "+ (t+1) +": "+ (squares[h]-k));
					break;
				}
					
			}
		}
			
		
	}

}
