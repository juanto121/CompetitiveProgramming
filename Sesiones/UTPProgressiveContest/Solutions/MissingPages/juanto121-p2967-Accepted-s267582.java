import java.util.Scanner;

public class Main{

	public static void main(String[] args) {
		
		int primes[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397 };
		int numOfWays[] = new int[401];
		for(int i =  0; i < 401; i++){
		
			for(int k = 0; k < primes.length && primes[k] < i ; k++){
				for(int j = 0; j < primes.length && primes[j] < i && j <= k  ; j++){
					if ( (primes[k]+primes[j]) == i ){
						numOfWays[i] ++; 
					}
				}
			}
			
		}
		
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		for(int h = 0; h < cases; h++){
			int N = scan.nextInt();
			System.out.println(numOfWays[N]);
		}
		
	}

}
