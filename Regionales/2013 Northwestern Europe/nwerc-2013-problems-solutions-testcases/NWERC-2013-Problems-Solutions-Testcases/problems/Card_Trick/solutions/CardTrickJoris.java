/* Solution to NWERPC-2013 problem Card Trick by Joris. */

// @EXPECTED_RESULTS@: CORRECT

import java.io.*;
import java.util.*;


public class CardTrickJoris
{

    public static void main(String[] args)
    {

        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

            String s = reader.readLine();
            while (s != null) {

                String[] words = s.split(" ");

                int n = Integer.parseInt(words[0]);
                int m = Integer.parseInt(words[1]);

                assert (n >= 1 && n <= 100);
                assert (m >= 1 && m <= 10);

                s = reader.readLine();
                words = s.split(" ");
                assert (n == words.length);

                int[] knownCards = new int[n];
                for (int i = 0; i < n; i++) {
                    if (words[i].equals("J") || words[i].equals("Q") ||
                        words[i].equals("K"))
                        knownCards[i] = 10;
                    else if (words[i].equals("A"))
                        knownCards[i] = 11;
                    else {
                        knownCards[i] = Integer.parseInt(words[i]);
                        assert (knownCards[i] >= 2 && knownCards[i] <= 11);
                    }
                }

                int finalCardPos = m;
                for (int i = 0; i < n - 1; i++)
                    finalCardPos += knownCards[i];

                assert (finalCardPos + knownCards[n-1] > 10);

                int[] cardValue = new int[finalCardPos+1];
                int j = m;
                for (int i = 0; i < n; i++) {
                    cardValue[j] = knownCards[i];
                    j += knownCards[i];
                }

                double[] cardProb = new double[finalCardPos+1];
                for (int i = 1; i <= 10 && i <= finalCardPos; i++)
                    cardProb[i] = 0.1;

                for (int i = 1; i < finalCardPos; i++) {
                    if (cardValue[i] > 0) {
                        if (i + cardValue[i] <= finalCardPos)
                            cardProb[i + cardValue[i]] += cardProb[i];
                    } else {
                        for (int k = 2; k <= 10; k++) {
                            if (i + k <= finalCardPos)
                                cardProb[i + k] += cardProb[i] / 13.0;
                        }
                        if (i + 10 <= finalCardPos)
                            cardProb[i + 10] += cardProb[i] * 3 / 13.0;
                        if (i + 11 <= finalCardPos)
                            cardProb[i + 11] += cardProb[i] / 13.0;
                    }
                }

                System.out.println(cardProb[finalCardPos]);

                s = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

}
