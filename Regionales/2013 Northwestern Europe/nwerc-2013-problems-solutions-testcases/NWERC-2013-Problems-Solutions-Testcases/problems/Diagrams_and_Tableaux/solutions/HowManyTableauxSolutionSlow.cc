// @EXPECTED_RESULTS@: TIMELIMIT
#include <cstdio>

using namespace std;

int N, a[8];
int t[8][8];
int k;
int go(int x, int y) {
    if (x == N || a[x] == 0) return 1;
    int re = 0;
    t[x][y] = y > 0 ? t[x][y] = t[x][y - 1] : 1;
    if (x > 0 && t[x][y] <= t[x - 1][y]) t[x][y] = t[x - 1][y] + 1;
    for (; t[x][y] <= N; ++t[x][y]) re += y + 1 < a[x] ? go(x, y + 1) : go(x + 1, 0);
    return re;
}

int main() {
	 while (scanf("%d", &k) == 1) {
		
        for (int i = 0; i < k; ++i) scanf("%d", a + i);
		for (int i = k; i < 8; i++)
			   a[i] = 0;
		scanf("%d", &N);
      
       
        printf("%d\n", go(0, 0));
    }
  

    return 0;
}
