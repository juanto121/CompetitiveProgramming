// @EXPECTED_RESULTS@: TIMELIMIT
#include <iostream>
#include <algorithm>
#include <map>
#include <string.h>
#include <cassert>

#define FOR(i,a,b) for (int i = (a); i < (b); i++)
#define MAGICROW 2
using namespace std;

int k, N, total, key, b[9][9], l[9];

int recur(int row, int col) {
	if (row >= k) return 1;
	if (col >= l[row]) return recur(row+1, 0);
	int ret = 0;
	int s = 0;
	if (row > 0) s = max(s, 1 + b[row - 1][col]);
	if (col > 0) s = max(s, b[row][col -1]);
	FOR(i,s,N+1) {
		b[row][col] = i;
		ret += recur(row, col + 1);
	}
	return ret;
}

int main(int argc, char **argv) {
	while (true) {
		cin >> k;
		if (cin.eof() || !cin.good()) break;
		FOR(i, 0, k) cin >> l[i];
		FOR(i, 0, k-1) assert(l[i] >= l[i + 1]);
		total = 0;
		cin >> N;
		FOR(i, 1, N+1) {
			b[0][0] = i;
			total += recur(0,1);
		}
		cout << total << endl;
	}
	return 0;
}
