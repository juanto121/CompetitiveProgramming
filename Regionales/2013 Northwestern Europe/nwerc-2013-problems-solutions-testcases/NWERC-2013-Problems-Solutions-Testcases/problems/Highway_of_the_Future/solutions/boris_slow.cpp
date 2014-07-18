// @EXPECTED_RESULTS@: TIMELIMIT

#include <cstdio>
#include <cstdlib>

#include <map>
#include <set>
#include <vector>
using namespace std;

typedef long long ll;
typedef pair<ll,ll> P;
struct PQcmp {
	bool operator()(const P& a, const P& b)
	const {
		return a.first * b.second < b.first * a.second;
	}
};
typedef struct {
	ll v, t;
} CAR;
typedef vector<CAR> CARS;
typedef map<P,int,PQcmp> MPI;

void solve() {
	int n; if( scanf("%d", &n) != 1 ) { exit(0); }
	CARS cars;
	const PQcmp cmp;
	int ans = 1;
	for( int i = 0; i < n; ++i ) {
		CAR car; if( scanf("%lld%lld", &car.t, &car.v) != 2 ) { exit(0); }
		cars.push_back(car);
		MPI counts;
		int nrsame = 1;
		for( int j = 0; j < i; ++j ) {
			const CAR &other = cars[j];
			if( car.v == other.v ) {
				if( car.t == other.t ) {
					nrsame += 1;
				}
				continue;
			}
			// x1 = v1*(t-t1)
			// x2 = v2*(t-t2)
			// v1*(t-t1) = v2*(t-t2)
			// v1*t - v1*t1 = v2*t - v2*t2
			// v1*t - v2*t = v1*t1 - v2*t2
			// t = (v1*t1 - v2*t2) / (v1 - v2)
			P t = P(car.v * car.t - other.v * other.t,
				car.v - other.v);
			if( t.second < 0ll ) {
				t.first *= -1ll;
				t.second *= -1ll;
			}
			// when is car starting
			P t1 = P(car.t, 1);
			if( cmp(t, t1) ) { 
				continue;
			}
			// when is car off the highway
			// 100 = v1*(t-t1)
			// 100 / v1 = t - t1
			// (100 + v1 * t1) / v1 = t
			P t2 = P(100 + car.v * car.t, car.v);
			if( cmp(t2, t) ) {
				continue;
			}
			MPI::iterator ptr = counts.find(t);
			if( ptr == counts.end() ) {
				counts[t] = 1;
			} else {
				ptr->second += 1;
			}
		}
		if( nrsame > ans ) {
			ans = nrsame;
		}
		for( MPI::iterator it = counts.begin(); it != counts.end(); ++it ) {
			if( it->second + nrsame > ans ) {
				ans = it->second + nrsame;
			}
		}
	}
	printf("%d\n", ans);
}

int main() {
	while( true ) {
		solve();
	}
}

