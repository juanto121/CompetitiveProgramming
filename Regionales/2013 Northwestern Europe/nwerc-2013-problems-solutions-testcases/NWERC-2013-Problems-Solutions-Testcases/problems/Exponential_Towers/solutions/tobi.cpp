// @EXPECTED_RESULTS@: CORRECT
#include <iostream>
#include <string>
#include <sstream>
#include <map>
#include <algorithm>
#include <vector>
using namespace std;

#define FOR(i,a,b) for (int i = (a); i < (b); i++)
#define FORIT(i,c) for (__typeof__((c).begin()) i = (c).begin(); i != (c).end(); i++)
typedef long long ll;
typedef pair<ll, ll> pll;

string s;
ll a, b, c, primes[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397};

map<ll,ll> fact(ll n) { // factorize
	map<ll,ll> ret;
	FOR(i,0,sizeof(primes)/sizeof(primes[0])) {
		if (n == 1 || n < primes[i]) break;
		while (n % primes[i] == 0) {
			ret[primes[i]]++;
			n /= primes[i];
		}
	}
	if (n > 1) ret[n]++;
	return ret;
}

ll rec(ll n) { // "towers" of length >= 1
	if (n == 1) return 1LL;
	map<ll,ll> f = fact(n);
	ll ret = 0;
	ll e = f.begin()->second;
	FORIT(it,f) e = max(e, it->second);
	FOR(i,1,e+1) {
		FORIT(it,f) if (it->second % (ll)i != 0) goto imp;
		ret += rec(i);
imp:	continue;
	}
	return ret;
}

int main(int argc, char **argv) {
	while (cin >> s) {
		FOR(i,0,s.size()) if (s[i] == '^') s[i] = ' ';
		istringstream ss(s);
		ss >> a >> b >> c;
		map<ll,ll> fa = fact(a);
		ll maxP = 1;
		FOR(i,2,14) {
			bool possible = true;
			FORIT(it,fa) if (it->second % i != 0) possible = false;
			if (possible) maxP = i;
		}
		map<ll,ll> fb = fact(b); // normalize a and b, later c
		map<ll,ll> add = fact(maxP);
		FORIT(it,fb) fb[it->first] *= c;
		FORIT(it,add) fb[it->first] += it->second;
		maxP = 1;
		FORIT(it,fb) maxP = max(maxP, it->second);
		ll sum = 0;
		FOR(i,2,maxP + 1) { // try to match that power
			ll pos = 1LL; // calc. possibilities for prime factors of b, other factors go down to a
			FORIT(it,fb) if (it->second/(ll)i > 0) pos *= 1LL + (ll) (it->second/(ll)i);
			if (pos > 1LL) sum += (pos - 1LL) * rec(i); // c can be expressed as a tower again
		}
		cout << sum << endl;
	}
	return 0;
}
