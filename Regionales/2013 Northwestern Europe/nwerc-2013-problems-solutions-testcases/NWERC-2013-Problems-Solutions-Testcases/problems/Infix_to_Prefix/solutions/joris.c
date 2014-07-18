/* Solution for Polish. */

// @EXPECTED_RESULTS@: CORRECT

#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <inttypes.h>
#include <assert.h>


#define MAXLENGTH   1000


char    solution_valid[MAXLENGTH][MAXLENGTH];
int64_t solution_low[MAXLENGTH][MAXLENGTH];
int64_t solution_high[MAXLENGTH][MAXLENGTH];


int main(void)
{
    char lbuf[MAXLENGTH+10];

    for (int c = 1; fgets(lbuf, sizeof(lbuf), stdin) != NULL; c++) {

        int n = strlen(lbuf);

        assert(n > 0);
        if (lbuf[n-1] == '\n')
            lbuf[--n] = '\0';

        assert(n > 0 && n <= MAXLENGTH);
        assert(strspn(lbuf, "0123456789+-") == n);

        for (int k = 1; k <= n; k++) {
            for (int i = 0; i + k <= n; i++) {
                solution_valid[i][k] = 0;
                if (k <= 9 && (k == 1 || lbuf[i] != '0')) {
                    int64_t v = 0;
                    int p;
                    for (p = 0; p < k; p++) {
                        if (!isdigit(lbuf[i+p]))
                            break;
                        v = 10 * v + lbuf[i+p] - '0';
                    }
                    solution_valid[i][k] = (p == k);
                    solution_low[i][k] = v;
                    solution_high[i][k] = v;
                }
                if (lbuf[i] == '-' && solution_valid[i+1][k-1]) {
                    solution_valid[i][k] = 1;
                    solution_low[i][k]  = -solution_high[i+1][k-1];
                    solution_high[i][k] = -solution_low[i+1][k-1];
                }
                if (lbuf[i] == '+' || lbuf[i] == '-') {
                    for (int j = 1; j <= k-2; j++) {
                        if (solution_valid[i+1][j] && solution_valid[i+1+j][k-1-j]) {
                            int64_t vl = solution_low[i+1][j] + ((lbuf[i] == '+') ? (solution_low[i+1+j][k-1-j]) : (-solution_high[i+1+j][k-1-j]));
                            int64_t vh = solution_high[i+1][j] + ((lbuf[i] == '+') ? (solution_high[i+1+j][k-1-j]) : (-solution_low[i+1+j][k-1-j]));
                            if (!solution_valid[i][k] || vl < solution_low[i][k])
                                solution_low[i][k] = vl;
                            if (!solution_valid[i][k] || vh > solution_high[i][k])
                                solution_high[i][k] = vh;
                            solution_valid[i][k] = 1;
                        }
                    }
                }
            }
        }

        assert(solution_valid[0][n]);
        printf("%"PRId64" %"PRId64"\n", solution_low[0][n], solution_high[0][n]);
    }

    return 0;
}
