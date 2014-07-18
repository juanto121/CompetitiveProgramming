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

public class RoadsScanner
{

  static boolean[] seen;
  static void dfs(int j, int dis, int start, ArrayList<ArrayList<Edge>> g, int[] d)
  {
    seen[j] = true;
    d[j] = dis;
    for (Edge x : g.get(j)) if (!seen[x.to])
      dfs(x.to, dis + x.length, start, g, d);
  }

  static boolean solve(boolean print, Scanner sc)
  {
    if (!sc.hasNext()) return false;
    if (print) System.out.println();

    int n = sc.nextInt();
    int[][] a = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        a[i][j] = sc.nextInt();

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
        System.out.println((x.to + 1) + " " + (i + 1) + " " + x.length);

    return true;
  }

  public static void main (String[] args)
  {
    Scanner sc = new Scanner(new BufferedInputStream(System.in));

    for (int i = 0; solve(i > 0, sc); i++);
  }
}

