package codes;

import java.util.Scanner;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int grid[][] = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                grid[i][j] = in.nextInt();
            }
        }

        boolean allGood = true;

        top : for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                boolean good = true;
                if(grid[i][j] != 1){

                    good = false;
                    midTop : for(int k = 0; k < n; k++){
                        for(int r = 0; r < n; r++){

                            if(grid[i][j] == grid[i][k] + grid[r][j]){
                                good = true;
                                break midTop;
                            }
                        }
                    }
                    if(!good){ allGood = false; break top; }
                }
            }
        }
        out.println(allGood ? "Yes" : "No");
    }
}
