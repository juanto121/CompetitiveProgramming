// @EXPECTED_RESULTS@: CORRECT
#include <iostream>
#include <vector>
#include <algorithm>
#define FOR(i,a,b) for (int i = (a); i < (b); i++)
#define contains(a,b) (find((a).begin(), (a).end(), (b)) != (a).end())
#define MAX_V 6005

using namespace std;
vector<int> adjList[MAX_V];
int silver[MAX_V];
bool dis[MAX_V];

int chain(vector<int> curVs, vector<int> sel, int pos, int sum) {
	if (pos >= curVs.size()) return sum;
	int ret = max(sum, chain(curVs, sel, pos+1, sum));
	bool possible = true;
	FOR(i,0,sel.size()) if (!contains(adjList[curVs[pos]], sel[i])) possible = false;
	sel.push_back(curVs[pos]);
	if (possible) ret = max(ret, chain(curVs, sel, pos + 1, sum + silver[curVs[pos]]));
	return ret;
}

int main(int argc, char **argv) {
	while (cin.good() && !cin.eof()) {
		int numV, numE, from, to, best = 0;
		cin >> numV >> numE;
		if (cin.eof()) break; // hate this testcase format
		FOR(i,0,numV) {
			adjList[i].clear();
			dis[i] = false;
			cin >> silver[i];
			best = max(best, silver[i]);
		}
		FOR(i,0,numE) {
			cin >> from >> to;
			from--; to--;
			adjList[from].push_back(to);
			adjList[to].push_back(from);
		}
		vector<pair<int,int> > degrees;
		FOR(i,0,numV) degrees.push_back(make_pair(adjList[i].size(),i));
		sort(degrees.begin(), degrees.end());
		FOR(i,0,numV) {
			int curV = degrees[i].second;
			vector<int> curVs;
			FOR(j,0,adjList[curV].size()) {
				int next = adjList[curV][j];
				if (!dis[next]) curVs.push_back(next);
			}
			vector<int> sel;
			best = max(best, chain(curVs, sel, 0, silver[curV]));
			dis[curV] = true;
		}
		cout << best << endl;
	}
	return 0;
}
