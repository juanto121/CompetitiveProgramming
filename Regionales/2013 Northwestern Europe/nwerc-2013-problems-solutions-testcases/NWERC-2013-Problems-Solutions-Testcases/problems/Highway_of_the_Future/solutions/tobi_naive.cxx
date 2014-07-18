// @EXPECTED_RESULTS@: TIMELIMIT
#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;

#define FOR(i,a,b) for (int i = (a); i < (b); i++)
#define FORIT(i,c) for (__typeof__((c).begin()) i = (c).begin(); i != (c).end(); i++)
typedef pair<int, int> pii;
typedef pair<pii, int> piii;
typedef pair<pii, pii> piiii;

int nCars;
vector<piii> cars;
map<pii,int> uncompressed;
map<piiii,int> coll;
map<piii,bool> used;

int gcd(int o, int u) { return u == 0 ? o : gcd(u, o%u); }
pii frac(int o, int u) {
	int ggt = gcd(o, u);
	return pii(o/ggt, u/ggt);
}

int main(int argc, char **argv) {
	while (cin >> nCars) {
		cars.clear();
		uncompressed.clear();
		FOR(i, 0, nCars) {
			pii c;
			cin >> c.first >> c.second;
			uncompressed[c]++;
		}
		FORIT(i, uncompressed) cars.push_back(make_pair(i->first, i->second));
		sort(cars.begin(), cars.end());

		nCars = cars.size();
		int lanes = 0;
		coll.clear();
		used.clear();
		FOR(i, 0, nCars) {
			lanes = max(lanes, cars[i].second);
			int t1 = cars[i].first.first;
			int v1 = cars[i].first.second;
			FOR(j, i+1, nCars) {
				int t2 = cars[j].first.first;
				int v2 = cars[j].first.second;
				if (v2 <= v1) continue; // later car must be faster
				pii time = frac((t2-t1)*v1 + t2*(v2-v1),v2-v1);
				pii loc = frac((t2-t1)*v1*v2,v2-v1);
				if (loc.second * 100 < loc.first) continue; // after end of highway
				piiii tl = piiii(time, loc);
				if (!used[piii(time,i)]) {
					coll[tl] += cars[i].second;
					used[piii(time,i)] = true;
				}
				if (!used[piii(time,j)]) {
					coll[tl] += cars[j].second;
					used[piii(time,j)] = true;
				}
				lanes = max(lanes, coll[tl]);
			}
		}
		cout << lanes << endl;
	}

	return 0;
}
