/**
 * @(#)FCTRLCerosFactorial2Division.java
 *
 *
 * @author 
 * @version 1.00 2011/4/30
 */

import java.util.Scanner;
import java.lang.Math;
public class FCTRLCerosFactorial2Division 
{

    public static void main(String args[])
    	
    {
    	Scanner scan=new Scanner(System.in);
    	int num=scan.nextInt();
    
    	for(int cont=0;cont<num;cont++)
    	{
    		int suma=0;
    		
    		int a=scan.nextInt();
    		for(long i=1;i<=13;i++)
    		{
    			
    				suma+=(a/Math.pow(5.0,i));
    				
    	    		if((a/Math.pow(5.0,i))<1)
    	    		{
    	    			System.out.println (suma);
    	    			break;
    	    		}
   			
    		}
    	}
    }
    
 }
    

    
    
    
    
  

    	
    
    
    
