/*  Solution in Java
    for Tree
    a problem for NWERC2013
  
    August 2013
    memo version.
*/
// @EXPECTED_RESULTS@: CORRECT
import java.io.*;
import java.util.*;

public class TreePKMemo{

   public static void main (String [] args){
       run();
   }

   private static void run(){
      try{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         String treeS = br.readLine();
         while (treeS != null){
             TreeElementMemo tree = TreeElementMemo.parse(treeS);
             int moves = tree.add(0);
             if (moves < 0)
                System.out.println("impossible");
             else
                System.out.println(moves);
             treeS = br.readLine();
         }
      }
      catch(IOException iox){
          System.out.println(iox);
      }
   }

}

abstract class TreeElementMemo{

    static char [] inString;
    static int index = 0;

    static TreeElementMemo parse(String input){
       inString = input.toCharArray();
       index = 0;
       return parseh();
    }

    static TreeElementMemo parseh(){
       if (inString[index + 0] != '(')
          throw new RuntimeException("parse error at " + index);
       if (inString[index + 1] == 'B'){
          index = index + 3;
          return new TwigMemo(1);
       }
       if (inString[index + 1] == ')'){
          index = index + 2;
          return new TwigMemo(0);
       }
       index++;
       TreeElementMemo leftTree  = parseh();
       TreeElementMemo rightTree = parseh();
       index++;
       return new MemoNode(leftTree, rightTree);
    }

    int balls;
    int twigs;

    abstract int add(int x);
    //adds x , returns number of added Balls
    // if adding is impossible: return -1
}


class MemoNode extends TreeElementMemo{
   TreeElementMemo left;
   TreeElementMemo right;
   Hashtable<Integer, Integer> memo = new  Hashtable<Integer, Integer>();

   public MemoNode(TreeElementMemo l, TreeElementMemo r){

      left = l;
      right = r;
      balls = left.balls + right.balls;
      twigs = left.twigs + right.twigs;
   }

   public int add(int x){
      int total =  x + balls;
      if (total == 0)  //Joris'optimization
        return 0;

      if (total < 0)
         return -1;
      if (total > twigs)
        return -1;

      if (memo.containsKey(total))
         return memo.get(total);

      int lb = left.balls;
      int rb = right.balls;
      int res;
      if (total % 2 == 0){
            int each = total/2;
            int leftresult = left.add(each - lb);
            if (leftresult < 0)
               return -1;
            int rightresult = right.add(each - rb);
            if (rightresult <0)
               return -1;
            res = leftresult + rightresult;
      }
      else{// total is odd
           int small = total/2;
           int big = small + 1;

           int leftsmall = -1;
           int leftresult = left.add(small -lb);
           if (leftresult >= 0){
              int rightresult = right.add(big - rb);
              if (rightresult >= 0)
                 leftsmall = leftresult + rightresult;
           }
           int rightsmall = -1;
           leftresult = left.add(big -lb);
           if (leftresult >= 0){
              int rightresult = right.add(small - rb);
              if (rightresult >= 0)
                 rightsmall = leftresult + rightresult;
           }
           if (leftsmall < 0)
              res = rightsmall;
           else if (rightsmall < 0)
              res = leftsmall;
           else
             res =  Math.min(rightsmall, leftsmall);
      }
      memo.put(total, res);
      return res;
   }

   public String toString(){
     return '('+ left.toString() + right.toString() + ')';
   }
}

class TwigMemo extends TreeElementMemo{

   public TwigMemo(int b){
      balls = b;
      twigs = 1;
   }

   public String toString(){
      if (balls == 1)
        return "(B)";
      else
        return "()";
   }

   public int add(int x){
      if (x == 0) // do nothing
         return 0;
      if (x == -1 && balls == 1) // remove ball
          return 0;
      if (x == 1 && balls == 0) // add bal
          return 1;
      return -1;  // impossible
   }

}
