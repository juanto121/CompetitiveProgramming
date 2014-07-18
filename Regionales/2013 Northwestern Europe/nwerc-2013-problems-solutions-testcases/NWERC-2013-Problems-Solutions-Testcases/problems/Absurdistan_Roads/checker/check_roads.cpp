#include <iostream>
#include <vector>
#include <set>
#include <algorithm>
#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <cmath>
using namespace std;

#define rep(i,a,b) for(__typeof(b) i=a; i<(b); ++i)
#define trav(it,c) for(__typeof((c).begin()) it=(c).begin(); it!=(c).end(); ++it)

typedef pair<int, int> pii;
const int MAXN = 2000;
bool ac = true;
int d[MAXN][MAXN], a[MAXN][MAXN];
void dijkstra(int start, int n, vector<pii> gr[])
{
    vector<int> dist(n, 1234567890);
    vector<bool> seen(n, false);
    dist[start] = 0;
    set<pii> q;
    rep(i,0,n) q.insert(pii(dist[i], i));

    while (!q.empty())
    {
        int u = q.begin()->second;
        q.erase(q.begin());
        d[start][u] = dist[u];
        trav(it,gr[u])
        {
            int ndist = it->second + dist[u];
            if (ndist < dist[it->first])
            {
                q.erase(pii(dist[it->first], it->first));
                dist[it->first] = ndist;
                q.insert(pii(dist[it->first], it->first));
            }
        }
    }
}

int main(int argc, char **argv)
{
    if(argc != 4){
        fprintf(stderr, "wrong number of arguments %d\n", argc);
        return -1;
    }
    FILE *input = fopen(argv[1], "r");
    FILE *output = fopen(argv[2], "r");
    FILE *ref = fopen(argv[3], "r");
    if(input == NULL || output == NULL || ref == NULL){
        printf("could not open file (in: %d out: %d ref: %d)\n", input != NULL, output != NULL, ref != NULL);
        return -1;
    }

    for (int tc = 0; ; tc++)
    {
        int n;
        if (fscanf(input, "%d", &n) != 1)
            break;
        rep(i,0,n) rep(j,0,n)
            fscanf(input, "%d", &a[i][j]);

        char poss_out[100];
        if (tc > 0)
        {
            if (fgets(poss_out, sizeof(poss_out), output) == NULL)
            {
                printf("TC %d: Expecting blank line but got end of output\n", tc + 1);
                ac = false;
                break;
            }
            if (string(poss_out) != "\n")
            {
                printf("TC %d: You need to print a blank line between test cases and not %s\n", tc +
                        1, poss_out);
                ac = false;
            }
        }

        vector<pii> gr[MAXN];
        rep(i,0,n)
        {
            int x, y, z;
            if (fgets(poss_out, sizeof(poss_out), output) == NULL)
            {
                if (ac)
                    printf("TC %d: Expecting 3 integers but got end of output\n", tc + 1);
                ac = false;
                continue;
            }
            if (sscanf(poss_out, "%d %d %d", &x, &y, &z) != 3)
            {
                printf("TC %d: Wrong output format, expecting 3 integers, got %s\n", tc + 1, poss_out);
                ac = false;
                continue;
            }

            if (x < 1 || x > n || y < 1 || y > n || x == y || z < 1 || z > 1000000)
            {
                printf("TC %d: Output out of range.\n", tc + 1);
                ac = false;
            }
            else
            {
                x--; y--;
                gr[x].push_back(pii(y, z));
                gr[y].push_back(pii(x, z));
            }
        }

        // all pair shortest distances
        rep(j,0,n)
            dijkstra(j, n, gr);


        rep(i,0,n) rep(j,0,n)
            if (d[i][j] != a[i][j])
            {
                if (ac) //only print one message
                    printf("TC %d: Wrong distance between cities %d and %d. Expecting %d, got %d.\n",
                        tc + 1, i + 1, j + 1, a[i][j], d[i][j]);
                ac = false;
            }
    }

    char trailing[101];
    int retval = fscanf(output, "%100s", trailing);
    if (retval != EOF && strlen(trailing) > 0)
    {
        printf("Trailing output\n");
        ac = false;
    }

    if(!ac)
        printf("Output is wrong\n");
}
