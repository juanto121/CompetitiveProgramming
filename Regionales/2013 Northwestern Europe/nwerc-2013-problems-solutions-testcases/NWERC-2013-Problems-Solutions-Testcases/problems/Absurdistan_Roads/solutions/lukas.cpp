//Solution by lukasP (Lukáš Poláček)
// @EXPECTED_RESULTS@: CORRECT
#include <vector>
#include <algorithm>
#include <cstdio>
using namespace std;

#define rep(i,a,b) for(__typeof(b) i=a; i<(b); ++i)
#define trav(it,c) for(__typeof((c).begin()) it=(c).begin(); it!=(c).end(); ++it)

typedef pair<int, int> pii;
typedef vector<int> vi;
int INF = 1234567890;
const int MAXN = 2000;

vector<bool> seen;
int d[MAXN][MAXN], a[MAXN][MAXN];
void dfs(int j, int dis, int start, vector<pii> g[])
{
    seen[j] = true;
    d[start][j] = dis;
    trav(i,g[j]) if (!seen[i->first])
        dfs(i->first, dis + i->second, start, g);
}
int main()
{
    for (int cas = 0, n; scanf("%d", &n) == 1; cas++)
    {
        if (cas > 0) printf("\n");
        rep(i,0,n) rep(j,0,n)
            scanf("%d", &a[i][j]);

        // minimum spanning tree
        vi dist(n, INF), pr(n, -1);
        seen.assign(n, false);
        dist[0] = 0;
        vector<pii> gr[MAXN]; //graph
        rep(i,0,n)
        {
            int mi = -1;
            rep(j,0,n) if (!seen[j])
                if (mi == -1 || dist[j] < dist[mi])
                    mi = j;
            seen[mi] = true;

            // add the shortest edge to the spanning tree
            if (pr[mi] != -1)
            {
                gr[mi].push_back(pii(pr[mi], dist[mi]));
                gr[pr[mi]].push_back(pii(mi, dist[mi]));
            }

            rep(j,0,n)
                if (a[mi][j] < dist[j])
                {
                    dist[j] = a[mi][j];
                    pr[j] = mi;
                }
        }

        // all pair shortest distances in the spanning tree
        rep(j,0,n)
        {
            fill(seen.begin(), seen.end(), false);
            dfs(j, 0, j, gr);
        }

        // find n-th edge to add
        int len = INF, x = 0, y = 1;
        rep(i,0,n) rep(j,0,n)
            if (a[i][j] < d[i][j] && a[i][j] < len)
        {
            len = a[i][j];
            x = i; y = j;
        }

        gr[x].push_back(pii(y, a[x][y]));
        gr[y].push_back(pii(x, a[x][y]));

        rep(i,0,n) trav(j,gr[i])
            if (j->first > i)
                printf("%d %d %d\n", j->first + 1, i + 1, j->second);
    }
}
