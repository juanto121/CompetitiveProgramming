/* Solution to Towers. */

// @EXPECTED_RESULTS@: CORRECT

#include <stdlib.h>
#include <stdio.h>
#include <stdint.h>
#include <inttypes.h>
#include <assert.h>


#define MAX_FACTORS 16
#define MAX_EXP     (16 * 9585)


struct factors {
    int nf;
    int p[MAX_FACTORS];
    int k[MAX_FACTORS];
};


int64_t countMiniTowers[MAX_EXP+1];


/* Find prime factors of n. */
void factorize(int n, struct factors *fact)
{
    fact->nf = 0;

    for (int p = 2; p * p <= n; p++) {
        if (n % p == 0) {
            fact->p[fact->nf] = p;
            fact->k[fact->nf] = 1;
            n = n / p;
            while (n % p == 0) {
                fact->k[fact->nf]++;
                n = n / p;
            }
            fact->nf++;
        }
    }

    if (n > 1) {
        fact->p[fact->nf] = n;
        fact->k[fact->nf] = 1;
        fact->nf++;
    }
}


/* Find factors of the product of two factorized numbers. */
void fact_mul(const struct factors *a, const struct factors *b,
              struct factors *c)
{
    c->nf = 0;

    int i = 0, j = 0;
    while (i < a->nf || j < b->nf) {
        if (i < a->nf && (j >= b->nf || a->p[i] < b->p[j])) {
            c->p[c->nf] = a->p[i];
            c->k[c->nf] = a->k[i];
            c->nf++;
            i++;
        } else if (j < b->nf && (i >= a->nf || b->p[j] < a->p[i])) {
            c->p[c->nf] = b->p[j];
            c->k[c->nf] = b->k[j];
            c->nf++;
            j++;
        } else {
            c->p[c->nf] = a->p[i];
            c->k[c->nf] = a->k[i] + b->k[j];
            c->nf++;
            i++;
            j++;
         }
    }
}


/* Greatest common divisor. */
int gcd(int a, int b)
{
    while (b > 0) {
        int t = a % b;
        a = b;
        b = t;
    }

    return a;
}


/* Solve one testcase. */
int64_t solve(int a, int b, int c)
{
    struct factors fa;
    struct factors fb;
    struct factors fh;
    struct factors fq;

    // Factorize A.
    factorize(a, &fa);

    // Factorize B^C.
    factorize(b, &fb);
    for (int i = 0; i < fb.nf; i++) {
        fb.k[i] *= c;
    }

    // Determine maximum H such that A == X^H for some X.
    int h = 0;
    for (int i = 0; i < fa.nf; i++) {
        if (h == 0) 
            h = fa.k[i];
        else
            h = gcd(h, fa.k[i]);
    }

    // Determine factorization of Q = H*B^C.
    factorize(h, &fh);
    fact_mul(&fh, &fb, &fq);

    // Count the number of ways to write X^(H*B^C) as a tower of
    // at least three powers (for any X).

    // Determine highest exponent in Q.
    int maxt = 0;
    for (int i = 0; i < fq.nf; i++) {
        if (fq.k[i] > maxt)
            maxt = fq.k[i];
    }

    int64_t answer = 0;
    for (int t = 2; t <= maxt; t++) {

        // Count the number of ways in which X^Q can be written as
        // (X^R)^(S^T) where S > 1 (for any X).
        // Equivalently, count the number of ways in which Q can be written
        // as R*S^T.
        int64_t a = 1;
        for (int i = 0; i < fq.nf; i++) {
            a *= 1 + fq.k[i] / t;
        }
        a -= 1;

        // Multiply by the number of ways in which T can be written as
        // a tower of at least one power.
        assert(t <= MAX_EXP);
        assert(a < INT64_MAX / countMiniTowers[t]);
        a *= countMiniTowers[t];

        assert(a < INT64_MAX - answer);
        answer += a;
    }

    return answer;
}


int main(void)
{
    char inp[80];

    // For all T > 1, count the number of ways in which T can be written
    // as a tower of at least one power.
    for (int t = 2; t <= MAX_EXP; t++) {

        struct factors ft;
        factorize(t, &ft);

        int wgcd = ft.k[0];
        for (int i = 1; i < ft.nf; i++) {
            wgcd = gcd(wgcd, ft.k[i]);
        }

        int64_t a = 1;
        for (int w = 2; w <= wgcd; w++) {
            if (wgcd % w == 0) {
                a += countMiniTowers[w];
                assert(a < INT64_MAX / 2);
            }
        }

        countMiniTowers[t] = a;
    }

    while (fgets(inp, sizeof(inp), stdin) != NULL) {
        char *p, *q;

        long a = strtol(inp, &p, 10);
        assert(p != inp && p[0] == '^');
        long b = strtol(p+1, &q, 10);
        assert(q != p+1 && q[0] == '^');
        long c = strtol(q+1, &p, 10);
        assert(p != q+1 && p[0] == '\n');

        assert(a > 1 && a <= 9585);
        assert(b > 1 && b <= 9585);
        assert(c > 1 && c <= 9585);

        int64_t answer = solve(a, b, c);
        printf("%"PRId64"\n", answer);
    }

    return 0;
}

/* end */
