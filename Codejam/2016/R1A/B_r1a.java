import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class B_r1a {
	
	static int maxn = 2500+3;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(bf.readLine());
		for(int t = 0; t < cases; t++){
			int n = Integer.parseInt(bf.readLine());
			
			int nums[] = new int[2500+3];
			
			for(int i = 0; i < 2*n-1; i++){
				String _list[] = bf.readLine().split(" ");
				for(int l = 0; l < _list.length; l++){
					int num = Integer.parseInt(_list[l]);
					nums[num]++;
				}
			}
			StringBuilder sb = new StringBuilder();
			for(int p = 0; p < maxn; p++){
				if(nums[p]%2!=0){
					sb.append(p+" ");
				}
			}
			
			System.out.println(String.format("Case #%d: %s", t + 1, sb.toString()));
		}
	}

}
