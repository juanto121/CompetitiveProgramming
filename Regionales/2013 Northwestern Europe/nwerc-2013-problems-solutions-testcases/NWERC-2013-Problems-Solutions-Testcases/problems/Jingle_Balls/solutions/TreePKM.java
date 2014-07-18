/*  Solution in Java
    for Tree
    a problem for NWERC2013
    August 2013
    full recursive version.
*/
// @EXPECTED_RESULTS@: CORRECT
import java.io.*;

public class TreePKM{

   public static void main (String [] args){
       run();
   }

   private static void run(){
      try{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         String treeS = br.readLine();

         int count = 0;
         while (treeS != null){
             TreeElement tree = TreeElement.parse(treeS);
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

abstract class TreeElement{

    static char [] inString;
    static int index = 0;

    static TreeElement parse(String input){
       inString = input.toCharArray();
       index = 0;
       return parseh();
    }

    static TreeElement parseh(){

       if (inString[index + 0] != '(')
          throw new RuntimeException("parse error at " + index);
       if (inString[index + 1] == 'B'){
          index = index + 3;
          return new Twig(1);
       }
       if (inString[index + 1] == ')'){
          index = index + 2;
          return new Twig(0);
       }
       index++;
       TreeElement leftTree  = parseh();
       TreeElement rightTree = parseh();
       index++;
       return new Node(leftTree, rightTree);
    }

    int balls;
    int twigs;
    abstract int add(int x);
    //adds x , returns number of added Balls
    // if adding is impossible: return -1
}

class Node extends TreeElement{

   TreeElement left;
   TreeElement right;

   public Node(TreeElement l, TreeElement r){
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
      if (total > twigs) //hardly helping
        return -1;

      int lb = left.balls;
      int rb = right.balls;

      if (total % 2 == 0){
          int each = total/2;
          int leftresult = left.add(each - lb);
          if (leftresult < 0)
             return -1;
          int rightresult = right.add(each - rb);
          if (rightresult < 0)
             return -1;
          return leftresult + rightresult;
      }
      else{// total is odd
         int small = total/2;
         int big = small + 1;

         int leftsmall = -1;
         int leftresult = left.add(small - lb);
         if (leftresult >= 0){
            int rightresult = right.add(big - rb);
            if (rightresult >= 0)
               leftsmall = leftresult + rightresult;
         }
         int rightsmall = -1;
         leftresult = left.add(big - lb);
         if (leftresult >= 0){
            int rightresult = right.add(small - rb);
            if (rightresult >= 0)
               rightsmall = leftresult + rightresult;
         }
         if (leftsmall < 0)
            return rightsmall;
         if (rightsmall < 0)
             return leftsmall;
         return Math.min(rightsmall, leftsmall);
      }
   }

   public String toString(){
     return '('+ left.toString() + right.toString() + ')';
   }
}

class Twig extends TreeElement{

   public Twig(int b){
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


