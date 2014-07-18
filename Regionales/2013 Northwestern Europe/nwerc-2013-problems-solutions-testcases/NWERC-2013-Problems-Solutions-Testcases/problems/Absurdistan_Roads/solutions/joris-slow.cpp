/* Solution to problem Roads */

// @EXPECTED_RESULTS@: TIMELIMIT

#include <stdlib.h>
#include <stdio.h>
#include <limits.h>
#include <assert.h>


#define MAXN 2000
#define MAXD 1000000

int n;
int disttbl[MAXN][MAXN];
int needroad[MAXN][MAXN];
int nroads;


void solve(void)
{
    nroads = 0;

    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            needroad[i][j] = 0;

    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            int m = INT_MAX;
            for (int k = 0; k < n; k++) {
                if (k != i && k != j) {
                    int d = disttbl[i][k] + disttbl[k][j];
                    if (d < m)
                        m = d;
                }
            }
            assert(disttbl[i][j] <= m);
            if (disttbl[i][j] < m) {
                needroad[i][j] = 1;
                nroads++;
            }
        }
    }

    assert(nroads <= n);
}


int main(void)
{
    int testcase = 0;

    while (scanf("%d", &n) == 1) {
        assert(n >= 2 && n <= MAXN);

        if (testcase++)
            printf("\n");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                scanf("%d", &disttbl[i][j]);
                assert(i == j || disttbl[i][j] > 0);
                assert(i != j || disttbl[i][j] == 0);
                assert(disttbl[i][j] <= MAXD);
                if (i > j)
                    assert(disttbl[i][j] == disttbl[j][i]);
            }
        }

        solve();

        int k = n - nroads;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && (needroad[i][j] || k > 0)) {
                    printf("%d %d %d\n", i+1, j+1, disttbl[i][j]);
                    if (!needroad[i][j])
                        k--;
                }
            }
        }
    }

    return 0;
}

/* end */
