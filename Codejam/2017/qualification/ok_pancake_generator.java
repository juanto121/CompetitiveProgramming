import java.util.BitSet;
import java.util.Random;

public class ok_pancake_generator {

	public static void main(String[] args) {
		int n = 1001;
		int k = 9;
		int t = 1000;
		BitSet bits = new BitSet(n);
		bits.flip(0,n);
		//bits.flip(generateIndex(n,1));
		
		System.out.println(t);
		for(int i = 0; i < t; i++){
			int randIndex = generateIndex(n,k);
			bits.flip(randIndex, randIndex+k);
			StringBuffer sb = new StringBuffer();
			for(int j = 0; j < n; j++){
				sb.append(bits.get(j)?"+":"-");
			}
			if(t>980)
				System.out.println(sb.append(" "+k).toString());
		}
	}

	private static int generateIndex(int n, int k) {
		Random r = new Random();
		int low = 0;
		int high = n-k;
		int res = r.nextInt(high-low) + low;
		return res;
	}
	
	

}
