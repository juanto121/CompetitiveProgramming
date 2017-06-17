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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, InputStreamReader bf, PrintWriter out) {
            BufferedReader in = new BufferedReader(bf);
            try {
                String desc[] = in.readLine().split(" ");
                int n = Integer.parseInt(desc[0]);
                int k = Integer.parseInt(desc[1]);
                int q = Integer.parseInt(desc[2]);

                int ntemps = 200000 + 2;
                int temps[] = new int[ntemps];

                for (int i = 0; i < n; i++) {
                    String bounds[] = in.readLine().split(" ");
                    int l = Integer.parseInt(bounds[0]);
                    int r = Integer.parseInt(bounds[1]);
                    temps[l]++;
                    temps[r + 1]--;
                }

                int cumsum = 0;
                for (int i = 0; i < ntemps; i++) {
                    cumsum += temps[i];
                    temps[i] = cumsum;
                }

                for (int t = 0; t < ntemps; t++) {
                    temps[t] = temps[t] >= k ? 1 : 0;
                }

                SparseRangeSum ranget = new SparseRangeSum(temps);
                int ktemps = ranget.log2(ntemps);

                for (int i = 0; i < q; i++) {
                    String bounds[] = in.readLine().split(" ");
                    int a = Integer.parseInt(bounds[0]);
                    int b = Integer.parseInt(bounds[1]);
                    out.println(ranget.rangeQuery(a, b, ktemps));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    static class SparseRangeSum {
        private final int[][] sparse;
        private final int n;
        private final int[] input;

        public SparseRangeSum(int[] input) {
            this.input = input;
            this.n = input.length;
            this.sparse = preprocess(input, this.n);
        }

        private int[][] preprocess(int[] input, int n) {
            int[][] sparse = new int[n][log2(n) + 1];
            for (int i = 0; i < input.length; i++) {
                sparse[i][0] = input[i];
            }

            for (int j = 1; 1 << j <= n; j++) {
                for (int i = 0; i + (1 << j) - 1 < n; i++) {
                    sparse[i][j] = sparse[i][j - 1] + sparse[i + (1 << (j - 1))][j - 1];
                }
            }

            return sparse;
        }

        public int log2(int n) {
            if (n <= 0) throw new IllegalArgumentException();
            return 31 - Integer.numberOfLeadingZeros(n);
        }

        public long rangeQuery(int low, int high, int k) {
            long ans = 0;
            for (int j = k; j >= 0; j--) {
                if (low + (1 << j) - 1 <= high) {
                    ans = ans + sparse[low][j];
                    low += 1 << j;
                }
            }
            return ans;
        }

    }
}

