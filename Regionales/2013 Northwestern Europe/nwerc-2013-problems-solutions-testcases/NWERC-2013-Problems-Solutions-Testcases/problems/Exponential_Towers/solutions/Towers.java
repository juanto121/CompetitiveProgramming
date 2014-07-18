/*
  Solution for Towers
  NWERC2013
  language: java
  date    : june 2013
  @EXPECTED_RESULTS@: CORRECT
*/

import java.util.*;
import java.io.*;
public class Towers{

   public static void main(String [] args){
      run();
   }

   private static void run(){
      InputStream is = null;
      try{

         is = System.in;
         Scanner ir = new Scanner(is);
         int caseCount = 0;

         while (ir.hasNext()){
            //caseCount++;
            String s = ir.next( "\\d*\\^\\d*\\^\\d*");
            Scanner ss = new Scanner(s).useDelimiter("\\^");
            int a = ss.nextInt();
            int b = ss.nextInt();
            int c = ss.nextInt();
            Tower t = new Tower(a,b,c);
            System.out.println(t.representations());
         }
         is.close();
      }
      catch (IOException iox){}
   }

}

class Tower{
    int a, b, c;

    public Tower(int aIn, int bIn, int cIn){
       a = aIn;
       b = bIn;
       c = cIn;
    }

    public long representations(){
       Number aNumber = new Number(a);
       int t = aNumber.expGgd();
       Number bb = Number.makeNumber(t, b, c);
       return bb.divReps();
    }
}

class Number{

   private int [] primefactor = new int [20];
   int [] exp = new int [20];
   int primefactors;

   public Number(){
   }

   public Number (int n){
      int p = 2;
      primefactors = 0;
      if (n % p == 0){
            primefactor[primefactors] = p;
            while (n % p == 0){
               n /= p;
               exp[primefactors]++;
            }
            primefactors++;
      }
      p = 3;
      while (p * p <= n){
         if (n % p == 0){
            primefactor[primefactors] = p;
            while (n % p == 0){
               n /= p;
               exp[primefactors]++;
            }
            primefactors++;
         }
         p += 2;
      }
      if (n > 1){
         primefactor[primefactors] = n;
         exp[primefactors] = 1;
         primefactors++;
      }
   }

   public long divReps(){
      long totaal = 0;
      int maxE = maxExp();
      for (int w = 2; w <= maxE; w++){
          long mul = 1;
          for (int k = 0; k < primefactors; k++)
             mul *= exp[k]/w + 1;
          mul--; // 1 is not allowed
          Number wNumber = new Number(w);
          totaal += mul * (wNumber.reps());
      }
      return totaal;
   }

   private int maxExp(){
       int champ = 0;
       for (int k = 0; k < primefactors; k++)
          if (exp[k] > champ)
             champ = exp[k];
       return champ;
   }

   private long reps(){
      // number of ways to write this as a tower of arbitrary height -including height 1.
      long total = 0;
      int t = expGgd();
      if (t == 1)
         return 1;
      //t > 1
      for(int d = 1; d <= t; d++)
         if (t % d == 0){
            Number dNumber = new Number(d);
            total += dNumber.reps();
         }
      return total;
   }

   public static Number makeNumber(int t, int b, int c){
      // returns t * (b^c)
      Number res = new Number();

      int p = 2;
      res.primefactors = 0;
      if (t % p == 0 || b % p == 0){
          res.primefactor[res.primefactors] = p;
          while (t % p == 0){
             t /= p;
             res.exp[res.primefactors]++;
          }
          while (b % p == 0){
             b /= p;
             res.exp[res.primefactors] += c;
          }
          res.primefactors++;
      }
      p = 3;
      while (p < 25 || p * p <= b){
         if (t % p == 0 || b % p == 0){
            res.primefactor[res.primefactors] = p;
            while (t % p == 0){
               t /= p;
               res.exp[res.primefactors]++;
            }
            while (b % p == 0){
               b /= p;
               res.exp[res.primefactors] += c;
            }
            res.primefactors++;
         }
         p += 2;
      }

      if (b > 1){
         res.primefactor[res.primefactors] = b;
         res.exp[res.primefactors] = c;
         res.primefactors++;
      }
      return res;
   }

   public int expGgd(){
      if (primefactors == 0)
        return 1;
      int answer = exp[0];
      for (int k = 1; k < primefactors; k++)
         answer = ggd(answer, exp[k]);
      return answer;
   }

   public static int ggd(int grootste, int kleinste)   //gcd
   {
		  while (kleinste != 0){
		     int restant = grootste % kleinste;
		     grootste    = kleinste;
		     kleinste    = restant;
		  }
		  return grootste;
	 }

   public String toString(){
      String answer = "" + getTerm(0);
      for (int k = 1; k < primefactors; k++)
         answer += " * " + getTerm(k);
      return answer;
   }

   private String getTerm(int k){
      if (primefactor[k] == 0)
         return "1";
      String answer = "" + primefactor[k];
      if (exp[k] > 1)
         answer += "^"+exp[k];
      return answer;
   }
}

