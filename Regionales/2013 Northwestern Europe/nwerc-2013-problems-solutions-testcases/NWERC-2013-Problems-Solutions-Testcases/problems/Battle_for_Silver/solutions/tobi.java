// @EXPECTED_RESULTS@: CORRECT
import java.io.*;
import java.util.*;

public class tobi {
	public static final int MAX_V = 6005;
	public static ArrayList<ArrayList<Integer> > adjList;
	public static int silver[] = new int[MAX_V];
	public static boolean dis[] = new boolean[MAX_V];

	public static int chain(ArrayList<Integer> curVs, ArrayList<Integer> sel, int pos, int sum) {
		if (pos >= curVs.size()) return sum;
		int ret = Math.max(sum, chain(curVs, sel, pos+1, sum));
		boolean possible = true;
		for (Integer seli : sel) {
			if (!adjList.get(curVs.get(pos)).contains(seli)) possible = false;
		}
		sel.add(curVs.get(pos));
		if (possible) ret = Math.max(ret, chain(curVs, sel, pos + 1, sum + silver[curVs.get(pos)]));
		sel.remove(sel.size() - 1);
		return ret;
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int numV, numE, from, to, best = 0;
			numV = sc.nextInt();
			numE = sc.nextInt();
			adjList = new ArrayList<>();
			for (int i = 0; i < numV; i++) {
				adjList.add(new ArrayList<Integer>());
				dis[i] = false;
				silver[i] = sc.nextInt();
				best = Math.max(best, silver[i]);
			}
			for (int i = 0; i < numE; i++) {
				from = sc.nextInt();
				to = sc.nextInt();
				from--; to--;
				adjList.get(from).add(to);
				adjList.get(to).add(from);
			}
			ArrayList<Pair> degrees = new ArrayList<>();
			for (int i = 0; i < numV; i++) {
				degrees.add(new Pair(adjList.get(i).size(), i));
			}
			Collections.sort(degrees, new PairComp());
			for (int i = 0; i < numV; i++) {
				int curV = degrees.get(i).second;
				ArrayList<Integer> curVs = new ArrayList<>();
				for (Integer next : adjList.get(curV)) {
					if (!dis[next]) curVs.add(next);
				}
				ArrayList<Integer> sel = new ArrayList<>();
				best = Math.max(best, chain(curVs, sel, 0, silver[curV]));
				dis[curV] = true;
			}
			System.out.println(best);
		}
	}
}
class PairComp implements Comparator<Pair> {
	public int compare(Pair p1, Pair p2) {
		if (p1 == null && p2 == null) return 0;
		if (p1 == null) return -1;
		if (p2 == null) return 1;
		if (p1.first == p2.first) return p1.second - p2.second;
		return p1.first - p2.first;
	}
}
class Pair {
	int first, second;
	Pair(int f, int s) {
		first = f;
		second = s;
	}
}
