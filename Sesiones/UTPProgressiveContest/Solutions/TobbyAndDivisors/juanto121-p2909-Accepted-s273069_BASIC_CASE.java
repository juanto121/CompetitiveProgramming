
import java.util.Scanner;

public class Main{

	public static void main(String[] args) {
		int numofdiv[] = new int[2502];
		for(int i =2; i < 2502; i++){
			for(int j = i; j < 2502; j+=i){
				numofdiv[j]+=1;
				
			}
		}
		
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		for(int t = 0; t < test ; t ++){
			int N = scan.nextInt();
			int maxIndex = N;
			int maxDiv = 0;
			for(int r = N; r > 0; r--){
				if( numofdiv[r] >= maxDiv ){
					maxDiv = numofdiv[r];
					maxIndex = r;
				}
			}
			System.out.println(maxIndex);
		}
		
		
		
	}

}
