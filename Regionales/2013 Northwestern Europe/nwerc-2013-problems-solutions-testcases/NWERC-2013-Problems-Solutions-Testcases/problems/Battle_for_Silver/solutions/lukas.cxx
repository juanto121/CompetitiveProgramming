//Solution by lukasP (Lukáš Poláček)
// @EXPECTED_RESULTS@: CORRECT
// Complexity O(V^2 E), but probably much better
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
        rep(i,0,m)
        {
            int x, y;
            scanf("%d %d", &x, &y);
            x--; y--;
            g[x].insert(y);
            g[y].insert(x);
        }

        int res = *max_element(w.begin(), w.end());
        rep(i,0,n) for (int j : g[i])
        {
            int weight = w[i] + w[j];
            res = max(res, weight);
            for (int k : g[i]) if (k < j && g[k].count(j))
            {
                res = max(res, weight + w[k]);
                for (int l : g[i]) if (l < k && g[l].count(j) && g[l].count(k))
                    res = max(res, weight + w[k] + w[l]);
            }
        }
        cout << res << endl;
    }
}
