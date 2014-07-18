//Solution by lukasP (Lukáš Poláček)
// @EXPECTED_RESULTS@: TIMELIMIT
// Complexity O(n^4)
#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include <cstdio>
using namespace std;

#define rep(i,a,b) for(__typeof(b) i=a; i<(b); ++i)
#define trav(it,c) for(__typeof((c).begin()) it=(c).begin(); it!=(c).end(); ++it)

typedef long long ll;
typedef pair<int, int> pii;
typedef vector<int> vi;
int main()
{
    int n, m;
    while (scanf("%d %d", &n, &m) == 2)
    {
        vi w(n);
        vector<set<int> > g(n);
        rep(i,0,n) scanf("%d", &w[i]);
        int res = *max_element(w.begin(), w.end());
        rep(i,0,m)
        {
            int x, y;
            scanf("%d %d", &x, &y);
            x--; y--;
            g[x].insert(y);
            g[y].insert(x);
            res = max(res, w[x] + w[y]);
        }

        rep(i,0,n)
            rep(j,0,i)
                rep(k,0,j)
        {
            int weight = w[i] + w[j] + w[k];
            if (g[i].count(k) && g[j].count(k) && g[i].count(j))
                res = max(res, weight);

            rep(l,0,k)
                if (g[i].count(k) && g[j].count(k) && g[i].count(j) &&
                    g[l].count(i) && g[l].count(j) && g[l].count(k))
                    res = max(res, w[i] + w[j] + w[k] + w[l]);
        }
        cout << res << endl;
    }
}
