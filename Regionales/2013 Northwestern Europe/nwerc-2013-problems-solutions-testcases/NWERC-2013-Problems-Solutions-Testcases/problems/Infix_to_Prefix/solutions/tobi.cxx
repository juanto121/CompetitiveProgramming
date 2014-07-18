// @EXPECTED_RESULTS@: CORRECT
#include <iostream>
#include <string>
#include <algorithm>
#include <climits>
using namespace std;

#define MAX 1005
#define FOR(i,a,b) for (int i = (a); i < (b); i++)
#define imp(a,b) do { (a).first = min((a).first, (b).first); (a).second = max((a).second, (b).second); } while(0)
typedef pair<long long, long long> pll;

string s;
int N;
bool valid[MAX][MAX], known[MAX][MAX];
pll t[MAX][MAX], unknown = pll(LLONG_MAX, LLONG_MIN);

pll op(char o, pll l, pll r)  {
	if (o == '+') return pll(l.first + r.first, l.second + r.second);
	return pll(l.first - r.second, l.second - r.first);
}
long long num(int a, int o) {
	if (o < a || (o + 1 - a > 9) || (a < o && s[a] == '0')) return LLONG_MIN;
	FOR(i,a,o+1) if (!isdigit(s[i])) return LLONG_MIN;
	return stoll(s.substr(a,o+1 - a));
}

bool ex(int a, int o) {
	if (known[a][o]) return valid[a][o];
	known[a][o] = true;
	valid[a][o] = false;
	if (s[a] == '+' || s[a] == '-') { // binary expression
		t[a][o] = unknown;
		FOR(i,a+1,o) if (ex(a+1,i) && ex(i+1,o)) { // search split point
			valid[a][o] = true;
			imp(t[a][o], op(s[a], t[a+1][i], t[i+1][o])); // make op
		}
		if (s[a] == '+') return valid[a][o];
	}
	if (s[a] == '-') { // unary expression?
		if (ex(a+1, o)) {
			valid[a][o] = true;
			imp(t[a][o], pll(-t[a+1][o].second, -t[a+1][o].first));
		}
		return valid[a][o];
	}
	t[a][o].first = t[a][o].second = num(a, o); // only numbers
	return valid[a][o] = (t[a][o].first != LLONG_MIN);
}

int main(int argc, char **argv) {
	while (cin >> s) {
		N = s.size();
		FOR(i, 0, N) FOR(j, 0, N) known[i][j] = false;
		ex(0,N-1);
	       	cout << t[0][N-1].first << " " << t[0][N-1].second << endl;
	}
	return 0;
}
