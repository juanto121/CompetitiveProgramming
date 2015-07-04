/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class center
{
	static double sqr_2 = 1.4142135623730950488016887242097;
	static int max_sn = 10000000;
	static int centers[] = new int[max_sn];
	static long A[] = new long[max_sn];
	static long B[] = new long[max_sn];
	static long precalc_centers[] = {8,49,288,1681,9800,57121,332928,1940449,11309768,65918161,384199200,2239277041l,13051463048l,76069501249l,443365544448l,2584123765441l,15061377048200l,87784138523761l};
	public static void main (String[] args)
	{
		findCenters();
		Scanner scan = new Scanner(System.in);
		while(true){
			
		long n = Long.parseLong(scan.nextLine());
		if(n == 0) break;
		int num_centers = 0;
		for(int i = 0; i < 18 ; i++){
			if(n>=precalc_centers[i]) num_centers++;
			else{
				break;
			}
		}
		System.out.println(num_centers);
		
		}
	}

	private static void findCenters() {
		int center_acumulator = 0;
		for(int i = 2; i < max_sn; i++){
			long i_squared = (long)i*(long)i;
			long j_dsquared = 2*i_squared;
			A[i] = i_squared;
			B[i] = j_dsquared;
			int half_i = (int) Math.round(i/sqr_2); //:S hoping i to be <= 10'0000
			long min = B[half_i]<A[i]?B[half_i]:A[i];
			long max = (B[half_i]+A[i]-min);
			if(min+1 == max){
				center_acumulator++;
				centers[i] += center_acumulator;
				System.out.print(min+",");
			}
		}
	}
}











/*
 * 		while(true){
			long n = Long.parseLong(scan.nextLine());
			if(n == 0) break;
			int centers[] = {6,35,204,1189,6930,40391,235416,1372105,7997214};
			long maxs[] = {8,49,288,1681,9800,57121,332928,1940449,100000000000000L,100000000000002L};
			int count = 0;
			
			for(long k = 2; k*k<100000000000000l; k++){
				long c = 2*k*k;
				double sp = (-1 + Math.sqrt( 1 + 4 * c ) ) / 2;
				if(sp>n)break;
				long ns = (long) (sp*(sp+1));
				
				if(sp%1 == 0 && ns == c){
					count++;
					System.out.println(k + " " + sp);
				}
			
						
		}
		for(int i = 0; i < 10; i++){
			if(n < maxs[i]){
				//System.out.println(i); break;
			}
		}
		
	}
 * 
 * 
 * */
