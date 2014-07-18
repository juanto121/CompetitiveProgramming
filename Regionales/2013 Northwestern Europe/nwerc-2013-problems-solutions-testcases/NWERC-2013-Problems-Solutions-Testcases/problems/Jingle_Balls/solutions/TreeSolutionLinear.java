/*
 *  Solution to problem Christmas Tree Decoration.
 *
 *  This program is designed to use a medium-fast implementation of
 *  an optimal algorithm. It should easily pass all test cases.
 *  It solves the problem with a single recursive pass through the tree.
 *  Run time is O(N) with some overhead.
 *
 *  This program requires stack space proportional to the depth of the tree.
 */

// @EXPECTED_RESULTS@: CORRECT

import java.io.*;
import java.util.*;


/* Represents a (sub)tree in the problem. */
class Tree
{
    public Tree left, right;
    public boolean ball;
}


public class TreeSolutionLinear
{

    static final int MAX_TWIGS = 500000;


    /* Decode string description of a decorated tree. */
    static Tree parseTree(String s) throws IOException
    {
        StringReader sr = new StringReader(s);

        int c = sr.read();
        assert (c == '(');

        Tree tree = parseSubTree(sr);
        c = sr.read();
        assert (c == -1);

        return tree;
    }


    /* Recursively decode string description of a decorated subtree. */
    static Tree parseSubTree(StringReader sr) throws IOException
    {
        Tree tree = new Tree();

        int c = sr.read();
        if (c == ')') {
            // empty twig
        } else if (c == 'B') {
            // twig with ball
            c = sr.read();
            assert (c == ')');
            tree.ball = true;
        } else {
            // pair of subtrees
            assert (c == '(');
            tree.left = parseSubTree(sr);
            c = sr.read();
            assert (c == '(');
            tree.right = parseSubTree(sr);
            c = sr.read();
            assert (c == ')');
        }

        return tree;
    }


    /* Count number of twigs in the tree. */
    static int countTwigs(Tree tree)
    {
        if (tree.left == null)
            return 1;
        else
            return countTwigs(tree.left) + countTwigs(tree.right);
    }


    /* Count number of balls in the tree. */
    static int countBalls(Tree tree)
    {
        if (tree.left == null)
            return tree.ball ? 1 : 0;
        else
            return countBalls(tree.left) + countBalls(tree.right);
    }


    /* Recursively determine the number of balls that must be moved
       to get an even distribution of K balls in this part of the tree. */
    static int[] findCost(Tree tree, int k)
    {
        if (tree.left == null) {

            // This is a twig.
            // Only two options available: 0 balls or 1 ball.

            if (k == 0) {
                // place zero or one balls on the twig
                return new int[] { 0, (tree.ball ? 0 : 1) };
            } else if (k == 1) {
                // place one or two balls on the twig (two is impossible)
                return new int[] { (tree.ball ? 0 : 1), -1 };
            } else {
                // impossible to have more than one ball on a twig
                return new int[] { -1, -1 };
            }

        } else {

            // Determine cost for the left and right subtrees.
            int[] leftAnswer  = findCost(tree.left, k / 2);
            int[] rightAnswer = findCost(tree.right, k / 2);

            // Construct answers for the combined tree.
            int[] answer = new int[2];
            for (int q = 0; q <= 1; q++) {

                // Put K+Q balls in the tree by putting ((K+Q) / 2) balls in
                // one subtree and ((K+Q+1) / 2) balls in the other subtree.
                // These numbers are either the same or they differ by one.

                int n0 = (k + q) / 2 - k / 2;
                int n1 = (k + q + 1) / 2 - k / 2;
                int a0 = leftAnswer[n0];
                int a1 = leftAnswer[n1];
                int b0 = rightAnswer[n0];
                int b1 = rightAnswer[n1];

                int v = -1;
                if (a0 >= 0 && b1 >= 0) {
                    if (v == -1 || a0 + b1 < v)
                        v = a0 + b1;
                }
                if (a1 >= 0 && b0 >= 0) {
                    if (v == -1 || a1 + b0 < v)
                        v = a1 + b0;
                }

                answer[q] = v;
            }

            return answer;
        }
    }


    /* Determine minimum number of moves necessary to get an even
       distribution of balls. */
    static int solve(Tree tree)
    {
        int nball = countBalls(tree);
        int[] answer = findCost(tree, nball);
        return answer[0];
    }


    /* Main program. */
    public static void main(String[] args)
    {

        try {

            BufferedReader ir = new BufferedReader(
                                  new InputStreamReader(System.in));

            String s = ir.readLine();

            while (s != null) {

                Tree tree = parseTree(s);
                int ntwig = countTwigs(tree);
                assert (ntwig >= 2 && ntwig <= MAX_TWIGS);

                int answer = solve(tree);
                if (answer < 0)
                    System.out.println("impossible");
                else
                    System.out.println(answer);

                s = ir.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}

/* end */
