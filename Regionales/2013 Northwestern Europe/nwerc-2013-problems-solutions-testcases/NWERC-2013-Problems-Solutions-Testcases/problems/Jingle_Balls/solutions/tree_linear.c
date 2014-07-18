/*
 *  Solution for problem Christmas Tree.
 *
 *  This program is designed to be very fast.
 *  It should pass all test cases easily.
 *  It solves the problem with a single recursive pass through the tree.
 *  Run time is O(N) with low overhead.
 *
 *  This program requires stack space proportional to the depth of the tree.
 */

// @EXPECTED_RESULTS@: CORRECT

#include <stdlib.h>
#include <stdio.h>
#include <assert.h>


#define MINTWIGS 2
#define MAXTWIGS 1000


struct answer {
    int a, b;
};

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


static inline int aplus(int a, int b)
{
    if (a >= 0 && b >= 0)
        return a + b;
    else
        return -1;
}


static inline int amin(int a, int b)
{
    if (a < 0)
        return b;
    else if (b < 0)
        return a;
    else if (a < b)
        return a;
    else
        return b;
}


struct answer solve(int t, int k)
{
    if (t < 0) {

        /* twig */
        int ball = (t == -2);

        switch (k) {
            case 0:  return (struct answer) { 0, (ball ? 0 : 1) };
            case 1:  return (struct answer) { (ball ? 0 : 1), -1 };
            default: return (struct answer) { -1, -1 };
        }

    } else {

        /* internal node */
        struct answer a0 = solve(tree[t][0], k / 2);
        struct answer a1 = solve(tree[t][1], k / 2);

        if (k % 2 == 0) {
            return (struct answer) {
                aplus(a0.a, a1.a),
                amin(aplus(a0.a, a1.b), aplus(a0.b, a1.a)) };
        } else {
            return (struct answer) {
                amin(aplus(a0.a, a1.b), aplus(a0.b, a1.a)),
                aplus(a0.b, a1.b) };
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

        struct answer answer = solve(root, nball);
        if (answer.a >= 0)
            printf("%d\n", answer.a);
        else
            printf("impossible\n");
    }

    return 0;
}

