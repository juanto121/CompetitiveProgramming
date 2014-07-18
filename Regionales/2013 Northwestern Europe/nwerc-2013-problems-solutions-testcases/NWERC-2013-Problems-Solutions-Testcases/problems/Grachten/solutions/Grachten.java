// @EXPECTED_RESULTS@: CORRECT
import java.io.*;
import java.util.*;

public class Grachten{

   public static void main(String [] args){
      InputStream is = System.in;

      Scanner ir = new Scanner(is);
      while (ir.hasNext()){
         int a = ir.nextInt();
         int b = ir.nextInt();
         int c = ir.nextInt();
         int t = a * b;
         int n = c - b;
         for (int i = 2; i <= t&& i <= n; i++)
            while(t%i == 0 && n%i == 0){
                t /= i;
                n /= i;
            }
         System.out.println(t + "/"+ n );
      }
   }

}