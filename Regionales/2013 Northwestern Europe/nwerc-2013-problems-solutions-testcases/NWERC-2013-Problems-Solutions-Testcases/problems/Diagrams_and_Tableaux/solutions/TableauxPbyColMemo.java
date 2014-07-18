// @EXPECTED_RESULTS@: CORRECT
import java.io.*;
import java.util.*;

public class TableauxPbyColMemo{

   public static void main (String [] args){
      run();
   }

   private static void run(){
      Scanner ir = new Scanner(System.in);
      while (ir.hasNext()){
        int m = ir.nextInt();
        int [] lineLength = new int[m + 1];
        for (int k = 0; k < m; k++)
           lineLength[k] = ir.nextInt();
        lineLength[m] = 0;
        int N = ir.nextInt();

        YoungDiagramA yd = new YoungDiagramD(N, lineLength);
        System.out.println(yd.solution());
      }
   }
}

abstract class YoungDiagramA{

   int [] rowLength;
   int [][] tableau = new int [8][8];
   int N;

   int count;

   public YoungDiagramA(int n, int [] l){
      this.N = n;
      rowLength = l;
   }

   int solution(){
      solve();
      return count;
   }

   abstract void solve();
}

class YoungDiagramC extends YoungDiagramA{ //by column
   int [] cols;

   public YoungDiagramC(int n, int [] l){
      super(n,l);
      cols = new int[rowLength[0]];
      int row = rowLength.length-1;
      int col = 0;
      while (row >= 0 && col < cols.length){
         if(rowLength[row] <= col)
            row--;
         else{
            cols[col] = row +1;
            col++;
         }
      }
   }

   void solve(){
      if (cols.length == 0)
        count = 1;
      else
        fill(0, cols[0]-1);
   }

   private void fill(int col, int row){
      if (row < 0){
         if (col + 1 >= cols.length)
            count++;
         else
            fill(col+1, cols[col+1] - 1);
         return;
      }
      int max;
      int min = row + 1;
      max = N;
      if (row < cols[col] - 1)
         max = tableau[col][row+1] - 1;
      if (col > 0)
         min = Math.max(min, tableau[col-1][row]);
      for (int k = max; k >= min; k--){
          tableau[col][row] = k;
          fill(col, row - 1);
      }
   }

   private void report(){
     System.out.println("-------------------");
      for (int row = 0; row < cols[0]; row++){
          for (int col = 0; tableau[col][row] > 0 ; col++)
             System.out.print(tableau[col][row] + " ");
          System.out.println("|");
      }
      System.out.println("-------------------");
   }
}

class YoungDiagramD extends YoungDiagramC{     // memo
   Hashtable<Integer, Integer> table = new  Hashtable<Integer, Integer>();
    // remember the number of extensions of this column
    // column represented by key.

   public YoungDiagramD(int n, int [] l){
      super(n,l);
   }
    void solve(){
      if (cols.length == 0)
        count = 1;
      else
         count = fill(0, cols[0]-1);
   }

   int fill (int col, int row){
      if (row < 0)
         return extendCol(col);
      int result = 0;
      int max;
      int min = row + 1;
      max = N;
      if (row < cols[col] - 1)
         max = tableau[col][row+1] - 1;
      if (col > 0)
         min = Math.max(min, tableau[col-1][row]);
      for (int k = max; k >= min; k--){
          tableau[col][row] = k;
          result += fill(col, row - 1);
      }
      return result;
   }

   int extendCol(int col){
      if (col + 1 >= cols.length)
         return 1;
      else{
         int key = getKey(col);
         int value;
         if (table.containsKey(key))
            value = table.get(key);
         else{
            value = fill(col+1, cols[col+1] - 1);
            table.put(key, value);
         }
         return value;
      }
   }
   
   private int getKey(int c){
      int result = c + 1;
      for (int k = 0; k < cols[c] && tableau[c][k] > 0; k++){
         result = 8 * result + tableau[c][k];
      }
      return result;
   }

}
