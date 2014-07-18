/*
 * Solution to How Many Tableaux.
 *
 * This solution uses dynamic programming over columns.
 * The maximum number of admissible columns is N!/((N/2)!^2),
 * for example N=7 admits at most 35 column fillings.
 *
 * An upper bound for the number of steps in this program
 * is 8 * 7 * 35^2 = 68600.
 */

// @EXPECTED_RESULTS@: CORRECT

#include <stdlib.h>
#include <stdio.h>
#include <stdint.h>
#include <inttypes.h>
#include <assert.h>

#define MAXN    7
#define MAXL    8


int N;
int K;
int L[MAXN];
int clen[MAXL+1];
int64_t atbl[2][1<<(3*MAXN)];     // this assumes MAXN <= 8


static inline int get_column_elem(int c, int i)
{
    return (c >> (3*i)) & 7;
}

static inline int set_column_elem(int c, int i, int v)
{
    return (c & ~(7 << (3*i))) | (v << (3*i));
}


/* Return next admissible column. */
int next_column(int c, int collen)
{
    if (c == -1) {
        c = 0;
        for (int k = 0; k < collen; k++)
            c = set_column_elem(c, k, k);
        return c;
    } else if (collen == 0) {
        return -1;
    } else {
        int k, v;
        for (k = collen - 1; k >= 0; k--) {
            v = get_column_elem(c, k);
            v++;
            if (v - k + collen <= N) {
                c = set_column_elem(c, k, v);
                break;
            }
        }
        if (k < 0)
            return -1;
        for (k++; k < collen; k++) {
            v++;
            c = set_column_elem(c, k, v);
        }
        return c;
    }
}


/* Return 1 if these columns are allowed to be adjacent. */
int allowed_columns(int c1, int c2, int collen)
{
    for (int k = 0; k < collen; k++) {
        if (get_column_elem(c1, k) > get_column_elem(c2, k))
            return 0;
    }
    return 1;
}


int main(void)
{
    char s[4096];

    while (fgets(s, sizeof(s), stdin) != NULL) {
        char *p;
        long v;
        int c;

        /* Read test case. */

        v = strtol(s, &p, 10);
        assert(p != s && v >= 1 && v <= MAXN);
        K = v;

        for (int i = 0; i < K; i++) {
            assert(p[0] == ' ' && p[1] != ' ');
            v = strtol(p+1, &p, 10);
            assert(v >= 0 && v <= MAXL);
            assert(i == 0 || v <= L[i-1]);
            L[i] = v;
        }

        assert(p[0] == '\n');

        p = fgets(s, sizeof(s), stdin);
        assert(p != NULL);

        v = strtol(s, &p, 10);
        assert(p != s && *p == '\n' && v >= K && v <= MAXN);
        N = v;

        /* Calculate length of each column. */
        for (int i = 0; i < MAXL; i++) {
            int k = 0;
            while (k < K && L[k] > i)
                k++;
            clen[i] = k;
        }
        clen[MAXL] = 0;

        /* Enumerate all possible options for the first column. */
        c = -1;
        while ((c = next_column(c, clen[0])) >= 0) {
            atbl[0][c] = 1;
        }

        /* Work towards the last column. */
        for (int i = 0; i < MAXL; i++) {
            c = -1;
            while ((c = next_column(c, clen[i+1])) >= 0) {
                atbl[(i+1)&1][c] = 0;
            }
            c = -1;
            while ((c = next_column(c, clen[i])) >= 0) {
                int64_t a = atbl[i&1][c];
                int cc = -1;
                while ((cc = next_column(cc, clen[i+1])) >= 0) {
                    if (allowed_columns(c, cc, clen[i+1])) {
                        atbl[(i+1)&1][cc] += a;
                    }
                }
            }
        }

        int64_t answer = atbl[MAXL&1][0];
        printf("%"PRId64"\n", answer);
    }

    return 0;
}
