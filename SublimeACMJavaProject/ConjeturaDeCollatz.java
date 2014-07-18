/**
 * @(#)ConjeturaDeCollatz.java
 *
 *
 * @author 
 * @version 1.00 2011/4/14
 */

import java.util.Scanner;

public class ConjeturaDeCollatz {

public static void main(String args[]) {
	
	Scanner scan = new Scanner(System.in);
	int cycle=0;
	int a=scan.nextInt();
	while(a>1)
	{
		if (a%2==1)
		{ cycle++;
			a=3*a+1;
			System.out.print (" "+a);
		}
		else
			if(a%2==0)
			{ 
			   cycle++;
			   	a=a/2;
				System.out.print (" "+ a);
			}
	}
	cycle++;
System.out.println ("\n"+cycle);
    
}
    
    
}