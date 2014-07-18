//Solution by lukasP (Lukáš Poláček)
// @EXPECTED_RESULTS@: CORRECT
// Dynamic programming solution, complexity O(2^{2n} 8)
#include <iostream>
#include <vector>
#include <cstdio>
#include <cstring>
#include <bitset>
using namespace std;

#define rep(i,a,b) for(__typeof(b) i=a; i<(b); ++i)
#define trav(it,c) for(__typeof((c).begin()) it=(c).begin(); it!=(c).end(); ++it)

typedef long long ll;
typedef pair<int, int> pii;
typedef vector<int> vi;
int main()
{
    const int s = 1 << 7;
    vector<vi> val;
    vi col[10];
    vi ma;
    rep(k,0,int(s)) // create all columns
    {
        bitset<8> b(k);
        vi a;
        rep(i,0,8) if (b[i])
            a.push_back(i + 1);
        val.push_back(a);
        col[a.size()].push_back(k);

        if (k == 0)
            ma.push_back(0);
        else
            ma.push_back(a.back());
    }

    bool g[s][s];
    rep(i,0,int(s)) rep(j,0,int(s)) // build a graph of columns that can go behind each other
    {
        bool ok = false;
        if (val[j].size() <= val[i].size())
        {
            ok = true;
            rep(k,0,val[j].size())
                if (val[j][k] < val[i][k])
                    ok = false;
        }
        g[i][j] = ok;
    }

    int n, len;
    ll cnt[s][10];
    while (scanf("%d", &len) == 1)
    {
        vi l(len);
        rep(i,0,len) scanf("%d", &l[i]);
        scanf("%d", &n);

        vi m(l[0]);
        rep(i,0,l[0])
        {
            int cnt = 0;
            rep(j,0,len) if (l[j] > i) cnt++;
            m[i] = cnt;
        }

        // Dynamic programming
        memset(cnt, 0x00, sizeof(cnt));
        for (int p : col[m[0]]) if (ma[p] <= n)
            cnt[p][0] = 1;

        rep(j,0,m.size()-1)
            for (int p : col[m[j]])
                if (cnt[p][j] > 0)
        {
            for (int o : col[m[j + 1]])
                if (ma[o] <= n && g[p][o])
                    cnt[o][j + 1] += cnt[p][j];
        }

        ll res = 0;
        for (int p : col[m.back()])
            res += cnt[p][m.size() - 1];
        printf("%lld\n", res);
    }
}
