import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author juan.torres
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputStreamReader in = new InputStreamReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        public void solve(int testNumber, InputStreamReader bf, PrintWriter out) {
            BufferedReader in = new BufferedReader(bf);
            try {
                String time[] = in.readLine().split(":");
                String hour = time[0];
                String min = time[1];

                if (hour.equals(reverse(min))) {
                    out.println("0");
                } else {
                    int target = Integer.parseInt(reverse(hour));
                    int curr = Integer.parseInt(min);
                    if (target < 60 && curr <= target) {
                        out.println(target - curr);
                    } else {
                        int nextTarget = Integer.parseInt(reverse(nextHour(hour)));
                        int invalidTime = invalidTime(hour);
                        int minLeft = 60 - curr + nextTarget + invalidTime;
                        out.println(minLeft);
                    }
                }
            } catch (IOException e) {

            }

        }

        public int invalidTime(String cur) {
            int c = Integer.parseInt(cur) + 1;
            int r = 0;
            if (c >= 6 && c <= 9) r = 10 - c;
            if (c >= 16 && c <= 19) r = 20 - c;
            return r * 60;
        }

        public String reverse(String ori) {
            StringBuffer bf = new StringBuffer(ori);
            return bf.reverse().toString();
        }

        public String nextHour(String hour) {
            int h = Integer.parseInt(hour);
            if (h == 23) {
                return "00";
            } else {
                int c = Integer.parseInt(hour) + 1;
                if (c >= 6 && c <= 9) return "10";
                if (c >= 16 && c <= 19) return "20";
                return String.format("%02d", h + 1);
            }
        }

    }
}

