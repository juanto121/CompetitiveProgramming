/*
 *  Solution for problem Christmas Tree.
 *
 *  This program is designed to use a very fast implementation of
 *  a sub-optimal algorithm.
 *  It uses backtracking without memoization but with aggressive pruning.
 *  Worst-case run time is O(N^1.68) with low overhead.
 *
 *  This program requires stack space proportional to the depth of the tree.
 */

// @EXPECTED_RESULTS@: CORRECT

#include <stdlib.h>
#include <stdio.h>
#include <assert.h>


#define MINTWIGS 2
#define MAXTWIGS 1000000


int tree[MAXTWIGS][2];
int nnode;
int ntwig;
int nball;

int bufp;
char buf[MAXTWIGS*5+10];


int parse(void)
{
    assert(buf[bufp] == '(');

    if (buf[bufp+1] == ')') {
        /* empty twig */
        ntwig++;
        bufp += 2;
        return -1;
    }

    if (buf[bufp+1] == 'B' && buf[bufp+2] == ')') {
        /* ball */
        ntwig++;
        nball++;
        bufp += 3;
        return -2;
    }

    /* internal node */
    assert(nnode < MAXTWIGS);
    int t = nnode;
    nnode += 1;
    bufp += 1;
    tree[t][0] = parse();
    tree[t][1] = parse();
    assert(buf[bufp] == ')');
    bufp += 1;
    return t;
}


int solve(int t, int k)
{
    if (k == 0)
        return 0;

    if (t < 0) {

        /* twig */
        int ball = (t == -2);

        if (k == 1) {
            return (ball ? 0 : 1);
        } else {
            return -1;
        }

    } else {

        /* internal node */
        if (k % 2 == 0) {

            int a0 = solve(tree[t][0], k / 2);
            if (a0 >= 0) {
                int a1 = solve(tree[t][1], k / 2);
                if (a1 >= 0)
                    return a0 + a1;
            }
            return -1;

        } else {

            int a = -1;
            int p0 = solve(tree[t][0], k / 2);
            if (p0 >= 0) {
                int p1 = solve(tree[t][1], k / 2);
                if (p1 >= 0) {
                    int q1 = solve(tree[t][1], (k+1) / 2);
                    if (q1 >= 0)
                        a = p0 + q1;
                    int q0 = solve(tree[t][0], (k+1) / 2);
                    if (q0 >= 0 && (a == -1 || q0 + p1 < a))
                        a = q0 + p1;
                }
            }

            return a;
        }

    }
}


int main(void)
{
    while (fgets(buf, sizeof(buf), stdin) != NULL) {

        bufp = 0;
        nnode = 0;
        ntwig = 0;
        nball = 0;
        int root = parse();
        assert(root == 0);

        assert(buf[bufp] == '\n' && buf[bufp+1] == '\0');
        assert(ntwig >= MINTWIGS && ntwig <= MAXTWIGS);

        int answer = solve(root, nball);
        if (answer >= 0)
            printf("%d\n", answer);
        else
            printf("impossible\n");
    }

    return 0;
}

