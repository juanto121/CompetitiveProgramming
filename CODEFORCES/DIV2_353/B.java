import java.util.ArrayList;
import java.util.Scanner;

public class B {
	

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();	int a = scan.nextInt();	int b = scan.nextInt();	int c = scan.nextInt();	int d = scan.nextInt();
		
		int e, f, h;
		int count = 0;
		
		for(int i = 1; i <= n; i++){
			f = d + i - a;
			if(f>n || f < 1)continue;
			e = c + f - b;
			if(e>n || e < 1)continue;
			h = e + a - d;
			if(h>n || h < 1)continue;
			Solution s = new Solution();
			s.e = e; s.f = f; s.h = h;	s.i = i;
			count++;
		}
		long res = (long)count * (long)n;
		System.out.println(res);
	}

}

class Solution{
	public Solution(){}
	int e,f,g,h,i;
}

