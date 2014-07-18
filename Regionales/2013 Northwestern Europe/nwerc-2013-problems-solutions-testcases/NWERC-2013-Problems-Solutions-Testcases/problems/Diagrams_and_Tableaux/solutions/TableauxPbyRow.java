// @EXPECTED_RESULTS@: CORRECT
import java.io.*;
import java.util.*;

public class TableauxPbyRow{

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

        YoungDiagramA yd = new YoungDiagramR(N, lineLength);
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

class YoungDiagramR extends YoungDiagramA{ // by row
    public YoungDiagramR(int n, int [] l){
      super(n,l);
    }

    void solve(){
       int row = rowLength.length-1;
       while (row >= 0 && rowLength[row] == 0)
          row--;
       if (row < 0)
          count = 1;
       else
          fill(0, row);
    }

    private void fill(int col, int row){
       if (col >= rowLength[row]){
          if (row == 0)
             count++;
          else
             fill(0, row-1);
          return;
       }
       int min = row + 1;
       if (col > 0)
          min = Math.max(min, tableau[col-1][row]);
       int max = N;
       if (col < rowLength[row+1])
          max = Math.min(max, tableau[col][row+1] -1);
       for (int k = min; k <= max; k++){
           tableau[col][row] = k;
           fill(col+1, row);
       }
    }
}

