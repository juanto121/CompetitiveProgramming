import java.util.Scanner;

public class PancakeRevenge {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int cases = Integer.parseInt(s.nextLine());
		for(int t = 0; t < cases; t++){
			char line[] = s.nextLine().toCharArray(); int len = line.length;
			char first =  line[0];
			char current = first;
			char prev = first;
			int blocks = 1;
			
			int res = 0;
			
			if(len == 1){
				if(first == '-'){
					res = 1;
				}else{
					res = 0;
				}
			}else{
				for(int i = 1; i < len; i++){
					current = line[i];
					if(current!=prev){
						blocks ++;
					}
					prev = current;
				}
				
				if(first == '-'){
					if(isOdd(blocks))res = blocks;
					else res = blocks - 1;
				}
				if(first == '+'){
					if (isOdd(blocks)) res = blocks - 1;
					else res = blocks;
				}
			}
			System.out.println(String.format("Case #%d: %d", t+1, res));
		}
	}
	
	public static boolean isOdd(int n){return n%2 != 0;}
	public static boolean isEven(int n){return !isOdd(n);}

}
