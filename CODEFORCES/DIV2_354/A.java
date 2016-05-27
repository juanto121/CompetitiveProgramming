import java.util.Collections;
import java.util.Scanner;

public class A {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int nums [] = new int[n];
		
		int posmin = 0;
		int posmax = 0;
		
		for(int i = 0; i < n; i++){
			nums[i] = scan.nextInt();
			if(nums[i]==1)	posmin = i;
			if(nums[i]==n) posmax = i;
		}
			
		int res = 0;
		int poss[] = {posmin, posmax, n-1-posmin, n-1-posmax};
		res = max(poss);
		
		System.out.println(res);
				
	}
	
	public static int max(int arr[]){
		int max = Integer.MIN_VALUE;
		int len = arr.length;
		for(int i = 0; i < len; i++){
			max = arr[i] >= max ? arr[i] : max;
		}
		return max;
	}

}
