// @EXPECTED_RESULTS@: CORRECT
#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>
using namespace std;

#define FOR(i,a,b) for (int i = (a); i < (b); i++)
typedef pair<int, int> pii;
class piihash {
	public: size_t operator()(const pii &x) const throw() {
		size_t h = (x.first << 16) + x.second;
		return h;
	}
};

int nCars;
vector<pii> cars;
int road[105][105];
unordered_map<pii,int,piihash> coll;

int gcd(int o, int u) { return u == 0 ? o : gcd(u, o%u); }
pii frac(int o, int u) {
	int ggt = gcd(o, u);
	return pii(o/ggt, u/ggt);
}

int main(int argc, char **argv) {
	while (cin >> nCars) {
		cars.clear();
		FOR(i,0,105) FOR(j,0,105) road[i][j] = 0;
		FOR(i, 0, nCars) {
			pii c;
			cin >> c.first >> c.second;
			cars.push_back(c);
		}
		sort(cars.begin(), cars.end());

		int lastTime = 0, lanes = 0;
		FOR(i, 0, nCars) {
			int same = i;
			while (i+1 < nCars && cars[i] == cars[i+1]) i++;
			same = 1 + i - same;
			lanes = max(lanes, same);
			int diff = cars[i].first - lastTime;
			lastTime = cars[i].first;
			if (diff > 0) FOR(j, 1, 101) { // move cars along the highway
				for (int k = 100 - (100%j); k >= 0; k -= j) {
					if (k+diff*j <= 100) road[k+diff*j][j] += road[k][j];
					road[k][j] = 0;
				}
			}
			coll.clear();
			FOR(j, 1, cars[i].second) {
				for (int k = 100 - (100%j); k >= 0; k -= j) {
					if (road[k][j] == 0) continue;
					pii f = frac(k, cars[i].second - j);
					if (f.first * cars[i].second > 100 * f.second) continue; // after end of highway
					coll[f] += road[k][j]; // collision
					lanes = max(lanes, same + coll[f]);
				}
			}
			road[0][cars[i].second] += same; // add the new cars
		}

		cout << lanes << endl;
	}

	return 0;
}
