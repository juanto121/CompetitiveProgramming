//Solution by lukasP (Lukáš Poláček)
// @EXPECTED_RESULTS@: CORRECT
#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <cstdio>
using namespace std;

#define rep(i,a,b) for(__typeof(b) i=a; i<(b); ++i)
#define trav(it,c) for(__typeof((c).begin()) it=(c).begin(); it!=(c).end(); ++it)

typedef unsigned long long ll;
typedef pair<int, int> pii;

map<int, int> factor(int a)
{
    map<int, int> res;
    for (int i = 2; i * i <= a; i++)
        while (a % i == 0)
        {
            a /= i;
            res[i]++;
        }
    if (a > 1)
        res[a] = 1;
    return res;
}

int main()
{
    // d[i] - how many times can i be expressed as a tower of height at least 1
    vector<ll> d(150000, 1);
    rep(j,2,20)
        for (ll i = 2; i <= d.size(); i++)
    {
        ll q = i * i;
        rep(k,2,j) q *= i;
        if (q >= d.size()) break;
        d[q] += d[j];
    }

    int a, b, c;
    while (scanf("%d^%d^%d", &a, &b, &c) == 3)
    {
        map<int, int> fa = factor(a);
        int q = 0;
        for (pii x : fa) q = __gcd(q, x.second);

        // normalize b
        map<int, int> fb = factor(b), fq = factor(q);
        trav(it,fb) it->second *= c;
        trav(it,fq) fb[it->first] += it->second;

        ll res = 0;
        rep(i,2,d.size())
        {
            ll tmp = 1;
            for (pii x : fb) tmp *= x.second / i + 1;
            if (tmp == 1) break;

            res += (tmp - 1) * d[i];
        }
        cout << res << endl;
    }
}
