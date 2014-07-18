//Solution by lukasP (Lukáš Poláček)
// @EXPECTED_RESULTS@: CORRECT
import java.io.*;
import java.util.*;

class Edge
{
  public int to, length;
  public Edge(int t, int l)
  {
    to = t;
    length = l;
  }
}

public class Roads
{

  static boolean[] seen;
  static void dfs(int j, int dis, int start, ArrayList<ArrayList<Edge>> g, int[] d)
  {
    seen[j] = true;
    d[j] = dis;
    for (Edge x : g.get(j)) if (!seen[x.to])
      dfs(x.to, dis + x.length, start, g, d);
  }

  static boolean solve(boolean print, Kattio io)
  {
    if (!io.hasMoreTokens()) return false;
    if (print) io.println();

    int n = io.getInt();
    int[][] a = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        a[i][j] = io.getInt();

    int[] dist = new int[n];
    Arrays.fill(dist, 1 << 28);
    dist[0] = 0;

    int[] pr = new int[n];
    Arrays.fill(pr, -1);
    seen = new boolean[n];
    Arrays.fill(seen, false);

    ArrayList<ArrayList<Edge>> gr = new ArrayList<ArrayList<Edge>> ();
    for (int i = 0; i < n; i++)
      gr.add(new ArrayList<Edge> ());

    for (int i = 0; i < n; i++)
    {
      int mi = -1;
      for (int j = 0; j < n; j++) if (!seen[j])
        if (mi == -1 || dist[j] < dist[mi])
          mi = j;

      seen[mi] = true;
      if (pr[mi] != -1)
      {
        gr.get(mi).add(new Edge(pr[mi], dist[mi]));
        gr.get(pr[mi]).add(new Edge(mi, dist[mi]));
      }

      for (int j = 0; j < n; j++) if (!seen[j] && a[mi][j] < dist[j])
      {
        dist[j] = a[mi][j];
        pr[j] = mi;
      }
    }

    int[][] d = new int[n][n];
    for (int j = 0; j < n; j++)
    {
      Arrays.fill(seen, false);
      dfs(j, 0, j, gr, d[j]);
    }

    int len = 1 << 25, X = 0, Y = 1;
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        if (a[i][j] < d[i][j] && a[i][j] < len)
      {
        X = i; Y = j;
        len = a[i][j];
      }
    if (len == 1 << 25) len = a[0][1];

    gr.get(X).add(new Edge(Y, len));
    gr.get(Y).add(new Edge(X, len));

    for (int i = 0; i < n; i++)
      for (Edge x : gr.get(i)) if (x.to > i)
        io.println((x.to + 1) + " " + (i + 1) + " " + x.length);

    return true;
  }

  public static void main (String[] args)
  {
    Kattio io = new Kattio(System.in, System.out);

    for (int i = 0; solve(i > 0, io); i++);

    io.close();
  }
}


/** Simple yet moderately fast I/O routines.
 *
 * Example usage:
 *
 * Kattio io = new Kattio(System.in, System.out);
 *
 * while (io.hasMoreTokens()) {
 *    int n = io.getInt();
 *    double d = io.getDouble();
 *    double ans = d*n;
 *
 *    io.println("Answer: " + ans);
 * }
 *
 * io.close();
 *
 *
 * Some notes:
 *
 * - When done, you should always do io.close() or io.flush() on the
 *   Kattio-instance, otherwise, you may lose output.
 *
 * - The getInt(), getDouble(), and getLong() methods will throw an
 *   exception if there is no more data in the input, so it is generally
 *   a good idea to use hasMoreTokens() to check for end-of-file.
 *
 * @author: Kattis
 */


class Kattio extends PrintWriter {
  public Kattio(InputStream i) {
    super(new BufferedOutputStream(System.out));
    r = new BufferedReader(new InputStreamReader(i));
  }
  public Kattio(InputStream i, OutputStream o) {
    super(new BufferedOutputStream(o));
    r = new BufferedReader(new InputStreamReader(i));
  }

  public boolean hasMoreTokens() {
    return peekToken() != null;
  }

  public int getInt() {
    return Integer.parseInt(nextToken());
  }

  public double getDouble() {
    return Double.parseDouble(nextToken());
  }

  public long getLong() {
    return Long.parseLong(nextToken());
  }

  public String getWord() {
    return nextToken();
  }


  private BufferedReader r;
  private String line;
  private StringTokenizer st;
  private String token;

  private String peekToken() {
    if (token == null)
      try {
        while (st == null || !st.hasMoreTokens()) {
          line = r.readLine();
          if (line == null) return null;
          st = new StringTokenizer(line);
        }
        token = st.nextToken();
      } catch (IOException e) { }
    return token;
  }

  private String nextToken() {
    String ans = peekToken();
    token = null;
    return ans;
  }
}

