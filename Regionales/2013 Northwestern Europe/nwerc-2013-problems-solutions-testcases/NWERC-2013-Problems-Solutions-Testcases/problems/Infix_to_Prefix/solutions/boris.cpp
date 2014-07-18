// @EXPECTED_RESULTS@: CORRECT
#include <cstdio>
#include <cstring>
#include <cctype>
#include <climits>

#include <algorithm>
using namespace std;

typedef long long ll;

ll mx[1001][1001];
ll mn[1001][1001];
bool can[1001][1001];
char s[1001];

int main() {
	int casenr = 0;
	while( scanf("%s", s) == 1 ) {
		memset(can, false, sizeof(can));
		int len = strlen(s);
		for( int l = 1; l <= len; ++l ) {
			for( int i = 0; i+l <= len; ++i ) {
				ll &MX = mx[i][l], &MN = mn[i][l];
				MX = LLONG_MIN;
				MN = LLONG_MAX;
				if( s[i] == '-' ) {
					if( l < 2 ) { continue; }
					if( can[i+1][l-1] ) {
						MX = max(MX, -mn[i+1][l-1]);
						MN = min(MN, -mx[i+1][l-1]);
					}
					for( int l1 = 1, l2; (l2 = l-1-l1) > 0; ++l1 ) {
						if( !can[i+1][l1] ) { continue; }
						int j = i+1+l1;
						if( !can[j][l2] ) { continue; }
						MX = max(MX, mx[i+1][l1]-mn[j][l2]);
						MN = min(MN, mn[i+1][l1]-mx[j][l2]);
					}
				} else if( s[i] == '+' ) {
					for( int l1 = 1, l2; (l2 = l-1-l1) > 0; ++l1 ) {
						if( !can[i+1][l1] ) { continue; }
						int j = i+1+l1;
						if( !can[j][l2] ) { continue; }
						MX = max(MX, mx[i+1][l1]+mx[j][l2]);
						MN = min(MN, mn[i+1][l1]+mn[j][l2]);
					}
				} else {
					// must be digit
					if( l > 9 ) { continue; }
					if( l > 1 && s[i] == '0' ) { continue; }
					ll x = 0LL;
					for( int j = i; j < i+l; ++j ) {
						if( !isdigit(s[j]) ) {
							x = -1LL;
							break;
						}
						x *= 10LL;
						x += (s[j]-'0');
					}
					if( x >= 0LL ) {
						MX = MN = x;
					}
				}
				if( MX != LLONG_MIN || MN != LLONG_MAX ) {
					if( MX == LLONG_MIN || MX == LLONG_MAX ) {
						fprintf(stderr, "WTF, MX=%lld and MN=%lld\n", MX, MN);
					}
					can[i][l] = true;
				}
			}
		}
		//printf("Case %d: ", ++casenr);
		if( !can[0][len] ) {
			puts("invalid");
		} else {
			printf("%lld %lld\n", mn[0][len], mx[0][len]);
		}
	}
	return 0;
}

