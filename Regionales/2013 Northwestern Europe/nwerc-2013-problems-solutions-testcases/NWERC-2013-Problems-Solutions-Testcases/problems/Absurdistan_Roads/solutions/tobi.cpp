// @EXPECTED_RESULTS@: CORRECT
#include <iostream>
#include <vector>
#include <algorithm>
#define MAX 2005
#define UNKNOWN -1

using namespace std;
typedef pair<int, int> pii;
#define FOR(i,a,b) for (int i = (a); i < (b); i++)

vector<pair<int,pii> > edges;
vector<pair<int,pii> > selEdges;
vector<pii> adjList[MAX];
int p[MAX];
int grid[MAX][MAX];
int mst[MAX][MAX];

int f(int x) {
	if (p[x] == x) return x;
	return p[x] = f(p[x]);
}
void u(int a, int b) {
	int pa = f(a);
	int pb = f(b);
	p[pb] = pa;
}
void dfs(int root, int cur, int dist) {
	if (mst[root][cur] != UNKNOWN) return;
	mst[root][cur] = dist;
	FOR(i, 0, adjList[cur].size()) dfs(root, adjList[cur][i].second, dist + adjList[cur][i].first);
}

int main(int argc, char **argv) {
	int N;
	bool first = true;

	while (cin >> N) {
		if (!cin.good() || cin.eof()) break;
		if (first) first = false; else cout << endl;
		edges.clear();
		selEdges.clear();
		FOR(i, 0, N) {
			p[i] = i;
			adjList[i].clear();
		}
		FOR(i, 0, N) FOR(j, 0, N) {
			cin >> grid[i][j];
			mst[i][j] = UNKNOWN;
			edges.push_back(make_pair(grid[i][j], make_pair(i, j)));
		}

		sort(edges.begin(), edges.end());
		for (int i = 0; selEdges.size() < N - 1; i++) {
			if (f(edges[i].second.first) == f(edges[i].second.second)) continue;
			u(edges[i].second.first, edges[i].second.second);
			selEdges.push_back(edges[i]);
			adjList[edges[i].second.first].push_back(make_pair(edges[i].first, edges[i].second.second));
			adjList[edges[i].second.second].push_back(make_pair(edges[i].first, edges[i].second.first));
		}
		FOR(i, 0, N) dfs(i,i,0);

		int cheapest = 1000001, f = 0, t = N-1; // a random road
		FOR(i, 0, N) FOR(j, 0, N) if (grid[i][j] < mst[i][j] && cheapest > grid[i][j]) {
			cheapest = grid[i][j];
			f = i; t = j;
		}
        if (cheapest == 1000001) cheapest = 1000000;
		selEdges.push_back(make_pair(cheapest, make_pair(f, t)));
		FOR(i, 0, N) FOR(j, 0, N) mst[i][j] = min(mst[i][j], min(mst[i][f] + cheapest + mst[t][j], mst[i][t] + cheapest + mst[f][j]));
		FOR(i, 0, N) FOR(j, 0, N) if (grid[i][j] != mst[i][j]) goto imp;

		FOR(i, 0, selEdges.size()) cout << 1 + selEdges[i].second.first << " " << 1 + selEdges[i].second.second << " " << selEdges[i].first << endl;
		continue;
imp: 		cout << "Impossible" << endl;
	}

	return 0;
}
