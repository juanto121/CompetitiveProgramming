// @EXPECTED_RESULTS@: CORRECT
#include <cstdio>

using namespace std;

int N, a[10];
int k;
long long go() {
    long long num = 1, den = 1;

    for (int i = 0; i + 1 < N; ++i) for (int j = i + 1; j < N; ++j) {
        num *= a[i] - a[j] + j - i;
        den *= j - i;
    }

    return num / den;
}

int main() {
    while (scanf("%d", &k) == 1) {
		
        for (int i = 0; i < k; ++i) scanf("%d", a + i);
		for (int i = k; i < 10; i++)
			   a[i] = 0;
		scanf("%d", &N);
        printf("%lld\n", go());
    }

    return 0;
}
