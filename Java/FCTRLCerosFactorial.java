/**
 * @(#)FCTRLCerosFactorial.java
 *
 *
 * @author 
 * @version 1.00 2011/4/30
 */

import java.math.BigInteger;
import java.util.Scanner;


public class FCTRLCerosFactorial {
 public static void main (String args[])
 {
 	Scanner scan = new Scanner(System.in);
 	//////Numero de numeros.
 	int numero= scan.nextInt();
    int ceros=0;
    int cont;
   
    while (scan.hasNextLong())
    {
    
 	for (cont=1;cont<=numero;cont++)
 	{
 	
 	    BigInteger factorial = BigInteger.valueOf(1);
       long  a= scan.nextInt();
       BigInteger n1=BigInteger.valueOf(a);
 	   for (long  i=1;i<=a;i++)
 	   {
 	   	BigInteger x = BigInteger.valueOf(i);
 	   	factorial = factorial.multiply(x);
 	   	
 	   }//////////////////////////////////////FIN FACTORIAL 
 	   
 	   
 	   
 	   ///////////////////////////////////////////////////// CEROS
 	   String cadena;
 	   int longitud;
 	   
 	   cadena=factorial.toString();
 	   
 	   longitud=factorial.toString().length(); 
 	   	   
 	   for(int j=longitud-1;j>0;j--)
 	   	
 	   {
 	   
 	   	char simbolo=cadena.charAt(j);
 	   	
 	   
 	   	 	   	if(simbolo=='0')
 	   			ceros++;
 	   			else
 	   			  if(simbolo!='0')
 	   			  {
 	   			  	System.out.println (ceros);
 	   			  	System.exit(0);
 	   			  }
 	   }
 	   
 	   System.out.println (ceros);
 	   
 	  }	
 		System.exit(0);
 		
 	 
    }	
 }
    
}