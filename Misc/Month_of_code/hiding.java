import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class hiding {
	
	static long sum_of_zeros = 0;
	static long max_num = 1000000;
	static long nums[] = new long[max_num];

	public static void main(String[] args) {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		try{
			long num_of_nums = Long.parseLong(scan.readLine());
			long count[] = new long[num_of_nums];
			long dp[] = new long[num_of_nums];
			String line_nums[] = scan.readLine().split(" ");
			for(int n = 0; n < num_of_nums; n++) nums[n] = Long.parseLong(line_nums[n]);
			dp[0] = nums[0];
			for(int i = 1; i < num_of_nums; i++){
				dp[i] = nums[i] + dp[i-1];
				count[dp[i]]++;
			}
			
			for(int i = 0; i < num_of_nums; i++){
				if(dp[i] == 0)
					sum_of_zeros++;
				sum_of_zeros+=count[i];
			}
			
			System.out.println(sum_of_zeros);
						
		}catch(IOException e){}
	}
}
