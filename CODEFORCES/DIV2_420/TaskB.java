package codes;

import java.util.Scanner;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int m = in.nextInt();
        int b = in.nextInt();

        long max = 0;

        for(long y = 0; y <= b; y++){
            long anchura = (y-b)*(-m);
            long r = ( anchura * (anchura+1) )/2;
            long ys = y*(y+1)/2;
            long ys1 = y+1;
            long sum = (anchura+1)*ys + ys1*r;
            if(sum > max) max = sum;
        }

        out.println(max);
    }
}
