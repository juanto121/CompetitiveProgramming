/*
 *  Incorrect solution for problem Christmas Tree.
 *
 *  This program visits all nodes of the tree in top-down order, choosing
 *  at each internal node the minimum number of balls to transport between
 *  its left and right branches. This algorithm has linear run time, but
 *  produces an answer which may not be globally optimal.
 *
 *  This program requires stack space proportional to the depth of the tree.
 */

// @EXPECTED_RESULTS@: WRONG-ANSWER

#include <iostream>
#include <cassert>

using namespace std;


#define MINTWIGS 2
#define MAXTWIGS 1000000


struct Tree
{
    Tree *left, *right;
    bool ball;
    int  ntwig;
    int  nball;

    Tree(Tree *l, Tree *r)
      : left(l), right(r), ball(false),
        ntwig(l->ntwig + r->ntwig), nball(l->nball + r->nball)
    { }

    Tree(bool b)
      : left(0), right(0), ball(b), ntwig(1), nball(b ? 1 : 0)
    { }
};


Tree * parse(istream& is)
{
    char c;
    is.get(c);

    if (c == ')') {
        /* empty twig */
        return new Tree(false);
    } else if (c == 'B') {
        /* ball */
        is.get(c);
        assert(c == ')');
        return new Tree(true);
    } else {
        /* internal node */
        Tree *l = parse(is);
        is.get(c);
        assert(c == '(');
        Tree *r = parse(is);
        is.get(c);
        assert(c == ')');
        return new Tree(l, r);
    }
}


int solve(Tree *t, int k)
{
    if (t->left) {
        /* internal node */

        int a, b;
        if (t->left->nball > t->right->nball ||
            (t->left->nball == t->right->nball && t->left->ntwig > t->right->ntwig)) {
            a = (k + 1) / 2;
            b = k / 2;
        } else {
            a = k / 2;
            b = (k + 1) / 2;
        }

        int c = max(0, max(min(t->left->nball - a, b - t->right->nball),
                           min(a - t->left->nball, t->right->nball - b)));

        int cl = solve(t->left, a);
        int cr = solve(t->right, b);

        return (cl >= 0 && cr >= 0) ? (c + cl + cr) : -1;

    } else {
        /* twig */

        return (k >= 0 && k <= 1) ? 0 : -1;
    }
}


int main()
{
    while (true) {

        char c;
        cin.get(c);
        if (cin.eof())
            break;
        assert(c == '(');

        Tree *tree = parse(cin);

        cin.get(c);
        assert(c == '\n');

        assert(tree->ntwig >= MINTWIGS && tree->ntwig <= MAXTWIGS);

        int answer = solve(tree, tree->nball);
        if (answer >= 0)
            cout << answer << endl;
        else
            cout << "impossible" << endl;
    }

    return 0;
}

