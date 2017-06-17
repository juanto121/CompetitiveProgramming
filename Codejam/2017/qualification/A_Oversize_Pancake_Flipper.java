import java.util.BitSet;
import java.util.Scanner;

public class A_Oversize_Pancake_Flipper {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int cases = Integer.parseInt(scan.nextLine());
		for(int t = 0; t < cases; t++){
			String desc[] = scan.nextLine().split(" ");
			String pancakes = desc[0];
			int n = pancakes.length();
			int k = Integer.parseInt(desc[1]);
			BitSet bits = new BitSet(n);
			loadBits(bits, pancakes);
			int moves = run(bits, k, n);
			int total = numOnesToLeft(bits, n);
			int best = n;
			if(best == total){
				System.out.println(String.format("Case #%d: %d",t+1, moves));
			}else{
				System.out.println(String.format("Case #%d: %s",t+1, "IMPOSSIBLE"));
			}
		}
	}

	private static int run(BitSet bits, int k, int n) {
		int moves = 0;
		
		for(int i = 0; i+k<=n; ){
			int qty = numOnesToLeft(bits.get(i, i+k), k);
			
			bits.flip(i,i+k); //flips
			int fqty = numOnesToLeft(bits.get(i, i+k), k);
			bits.flip(i, i+k); //unflips
			
			if(fqty > qty){
				bits.flip(i, i+k);
				moves ++;
				//System.out.println(i);
				i = bits.nextClearBit(i);
				
			}else{
				i++;
			}
		}
		return moves;
	}

	private static int numOnesToLeft(BitSet bitSet, int size) {
		int qty = 0;
		for(int i = 0; i < size; i++){
			if(bitSet.get(i)){
				qty ++;
			}else{
				break;
			}
		}
		return qty;
	}

	private static void loadBits(BitSet bits, String pancakes) {
		for(int i = 0; i < pancakes.length(); i++){
			bits.set(i,pancakes.charAt(i) == '+');
		}
	}

}
