/**
 * @(#)FactorialDigitosX.java
 *
 *
 * @author 
 * @version 1.00 2011/5/1
 */
import java.util.Scanner;
import java.math.BigInteger;

public class FactorialDigitosX {

   public static void main(String args[]) {
   	
   	Scanner scan=new Scanner(System.in);
   	 String numS=scan.next();
   	 
   	 BigInteger num=new BigInteger(numS);
   	/////Numero de numeros.
   	for(int i=0;i<100000;i++)
   	{
   		String cadenaj="";
   		int a=scan.nextInt();
   		/////////////// genera la cadena mas larga con X digitos
   		for(int j=1;j<=a;j++)
   		{
		
   			cadenaj=cadenaj+"9";
   			
   		}
   		
   		
   		BigInteger numero2=new BigInteger(cadenaj);
   		
   		
   		////////////Genera factorial y compara si es menor
   		
   		long b=100000 ;
   		long m=1;
   		BigInteger factorial=BigInteger.valueOf(m);
   		
           for (long  j=1;j<=b;j++)
           {
                BigInteger x = BigInteger.valueOf(j);
                factorial = factorial.multiply(x); 
                if((factorial.compareTo(numero2))==1)
                {
                	System.out.println (j-1);
                	break;
                }
        
           }
     
         }       
		
   		}
}
   		
   	
   	
    
