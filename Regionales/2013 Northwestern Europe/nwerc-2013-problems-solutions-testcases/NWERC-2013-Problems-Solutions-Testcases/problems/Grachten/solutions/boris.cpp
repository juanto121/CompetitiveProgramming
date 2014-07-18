// @EXPECTED_RESULTS@: CORRECT

#include <cstdio>
#include <cstdlib>
#include <cmath>

int main() {
	while( 1 ) {
		int a, d, c, e;
		if( scanf("%d%d%d", &a, &c, &d) != 3 ) {
			return 0;
		}
		int p = c*a, q = d-c;
		for( int i = 2; i <= q; ++i ) {
			while( (p%i) == 0 && (q%i) == 0 ) {
				p /= i; q /= i;
			}
		}
		printf("%d/%d\n", p, q);
	}
	return 0;
}

