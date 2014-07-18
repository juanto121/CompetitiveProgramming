/*  Solution in Java
    for Polish
    a problem for NWERC2013
        August 2013
*/
// @EXPECTED_RESULTS@: CORRECT

import java.io.*;
import java.util.*;
public class Polish{

   public static void main(String []args){
      run();
   }

   public static void run(){
      InputStream is = null;
      try{
         is = System.in;
         Scanner ir = new Scanner(is);
         int caseCount = 0;

         while (ir.hasNext()){
            String input = ir.next();

            PolishProblem ps = new PolishProblem(input);
            ps.solve();
            MinMax sol = ps.solution;
            if (sol == null)
              throw new RuntimeException("invalid inputstring");
            else
               System.out.println(sol.min + " " + sol.max);
        }
        is.close();
      }
      catch (IOException iox){}
   }
}

class PolishProblem {
   static final int MAXLEN = 9;

   String input;
   int length;
   boolean solvable;
   MinMax solution;
   MinMax [][] minmax;

   public PolishProblem(String in){
      input = in;
      length = in.length();
      if (length > 1000)
         throw new RuntimeException("input too long");
      minmax = new MinMax[length][];
   }

   public void solve(){
      for (int start = length - 1; start >= 0; start--){
         minmax[start] = new MinMax[length - start + 1];
         for(int len = 1; start + len <= length; len++)
            minmax[start][len] = parse(start, len);
      }
      solution = minmax[0][length];
    }

   private MinMax parse (int start, int len){

      char fst = input.charAt(start);
      if ('0' <= fst && fst <= '9'){    // number
         if (len > MAXLEN)
            return null;
         if (fst == '0'){
            if (len == 1)
               return new MinMax(0,0);
            else
               return null;
         }
         else{ // fst != '0'
           String numString = input.substring(start, start + len);
           int num;
           try{
              num = Integer.parseInt(numString);
           }
           catch(NumberFormatException nfe){
              return null;
           }
           return new MinMax(num,num);
         }
      }
      else{                             // operator
         if (fst == '-'){               //  minus
            MinMax un;  //  unary interpretation
            if (len > 1)
               un = minmax[start+1][len-1];
            else
               un = null;
            MinMax bin = parseBin(start, len); // binary interpretation
            // which one is best?
            if (un == null)
               return bin;
            if (bin == null)
               return new MinMax( -un.max, -un.min);
            //  both are non-null, which one is best?
            long min = Math.min(bin.min, -un.max);
            long max = Math.max(bin.max, -un.min);
            return new MinMax(min, max);
         }
         else   // fst = '+'
            return parseBin(start, len);
      }
   }

   private MinMax parseBin(int start, int len){  // parse binary expression
      char fst = input.charAt(start);    // fst = '+' or fst = '-'
      long min = Long.MAX_VALUE,
           max = Long.MIN_VALUE;
      for (int l1 = 1; l1 < len - 1; l1++){  // find the best splitting in lexp en rexp
          int l2 = len - l1 - 1;
          MinMax lexp = minmax[start + 1][l1];
          MinMax rexp = minmax[start + l1 + 1][l2];
          if (lexp != null && rexp != null)
            switch (fst){
             case '+': long lMax = lexp.max + rexp.max;
                       if (lMax > max)                // store champion
                           max = lMax;
                       long lMin = lexp.min + rexp.min;
                       if (lMin < min)                // store champion
                          min = lMin;
                       break;
             case '-': lMax = lexp.max - rexp.min;
                       if (lMax > max)                // store champion
                           max = lMax;
                       lMin = lexp.min - rexp.max;
                       if (lMin < min)                // store champion
                          min = lMin;
                       break;
          }
      }
      if (min <= max)
        return new MinMax(min, max);
      else
        return null; // don't remember what this was good for!
   }
}

class MinMax{
    long min, max;

    public MinMax(long minIn, long maxIn){
       min = minIn;
       max = maxIn;
    }

    public String toString(){
       return min + " " + max;
    }

    public boolean equals(Object other){
      if (other == null)
         return false;
      MinMax that = (MinMax) other;
      return this.max == that.max && this.min == that.min;
    }
}






