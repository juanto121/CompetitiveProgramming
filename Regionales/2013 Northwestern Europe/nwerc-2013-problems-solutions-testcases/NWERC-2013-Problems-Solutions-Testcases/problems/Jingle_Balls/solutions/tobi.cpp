// @EXPECTED_RESULTS@: CORRECT
#include <iostream>
#include <string>
#include <map>
using namespace std;

#define MAX 2000
#define FOR(i,a,b) for (int i = (a); i < (b); i++)
#define ABS(x) ((x) > 0 ? (x) : -(x))
typedef pair<int, int> pii;
const int oo = 0x3f3f3f3f;

string s;
int numNodes, root, best, sum[MAX];
bool twig[MAX], ball[MAX];
pii childs[MAX];
map<pii,int> t;

int parse(int a, int o) { // both parses and sums up the balls
	int node = numNodes++;
	twig[node] = (a + 2 >= o);
	if (twig[node]) sum[node] = ball[node] = o - a - 1;
	else {
		int pt, cnt = 0;
		for (pt = a + 1; cnt > 0 || pt == a + 1; pt++) if (s[pt] != 'B') cnt += (s[pt] == '(' ? +1 : -1);
		int l = parse(a+1, pt-1);
		int r = parse(pt,o-1);
		childs[node] = pii(l, r);
		sum[node] = sum[l] + sum[r];
	}
	return node;
}

int balance(int node, int given) { // balance this tree with given additional nodes
	if (t.find(pii(node,given)) != t.end()) return t[pii(node,given)];
	if (twig[node]) {
		if (ball[node]) return (given > 0 || given < -1) ? oo : 0; // try ball removal
		return (given >= 0 && given <= 1) ? 0 : oo; // try ball addition
	}
	int l, r, best = oo;
	pii c = childs[node];
	int diff = sum[c.first] - sum[c.second];
	int low = (given < 0) ? given : 0;
	int upp = (given > 0) ? given : 0;

	FOR(g,low,upp+1) {
		FOR(i,diff/2 - 2 - ABS(given), diff/2 + 2 + 1 + ABS(given)) {
			int diffl = -i + g, diffr = +i + (given - g);
			if (ABS(sum[c.first] + diffl - (sum[c.second] + diffr)) > 1) continue;
			if ((l = balance(c.first, diffl)) > oo/2
				|| (r = balance(c.second, diffr)) > oo/2) continue;
			best = min(best, min(oo, ABS(i) + l + r));
		}
	}
	return t[pii(node,given)] = best;
}

int main(int argc, char **argv) {
	while (cin >> s) {
		numNodes = 0;
		t.clear();
		root = parse(0, s.size() - 1);
		if ((best = balance(root, 0)) == oo) cout << "impossible" << endl;
		else cout << best << endl;
	}
	return 0;
}
