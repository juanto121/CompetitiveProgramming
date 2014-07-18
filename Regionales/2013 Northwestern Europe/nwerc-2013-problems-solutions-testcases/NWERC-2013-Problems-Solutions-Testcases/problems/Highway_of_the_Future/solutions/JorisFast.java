/*
 * Solution for Quantum Highway
 *
 * This solution requires time
 *   O( numCars * roadLength * log(maxSpeed) )
 */

// @EXPECTED_RESULTS@: CORRECT

import java.io.*;
import java.util.*;


class JorisCar implements Comparable
{
    public int start;
    public int speed;

    public JorisCar(int start, int speed)
    {
        this.start = start;
        this.speed = speed;
    }

    public boolean equals(Object o)
    {
        if (o == null || !(o instanceof JorisCar))
            return false;
        JorisCar c = (JorisCar) o;
        return this.start == c.start && this.speed == c.speed;
    }

    public int compareTo(Object o)
    {
        JorisCar c = (JorisCar) o;
        if (this.start < c.start)
            return -1;
        if (this.start > c.start)
            return 1;
        if (this.speed < c.speed)
            return -1;
        if (this.speed > c.speed)
            return 1;
        return 0;
    }
};


public class JorisFast
{

    static final int MAXN = 35000;
    static final int MAXT = 10000;
    static final int MAXV = 100;
    static final int RLEN = 100;

    // Precomputed GCD function.
    static int[][] gcdmap;

    // List of cars.
    static JorisCar[] cars;


    static final void ass(boolean a)
    {
        if (!a)
            Integer.parseInt(null);
    }


    static final int gcd(int a, int b)
    {
        while (b > 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }


    static int solve()
    {
        int answer = 0;

        // Sort cars by time of entry to highway.
        Arrays.sort(cars);

        // Initialize simulation.
        int curTime = 0;
        int nCarOnHighway = 0;
        int[][] highwayState = new int[RLEN][MAXV+1];

        int[][] hitmap = new int[RLEN][MAXV];

        // Process cars in order of entrance to the highway.
        for (int i = 0; i < cars.length; i++) {

            // Pull next car from list.
            int nt = cars[i].start;
            int nv = cars[i].speed;

            // Update simulation state to the time of next entrance.
            if (nt > curTime && nCarOnHighway > 0) {
                for (int v = 1; v <= MAXV; v++) {
                    for (int p = RLEN-1 - (RLEN-1) % v; p >= 0; p -= v) {
                        int np = p + v * (nt - curTime);
                        if (np < RLEN)
                            highwayState[np][v] += highwayState[p][v];
                        highwayState[p][v] = 0;
                    }
                }
            }

            curTime = nt;

            // Put new car on the highway.
            nCarOnHighway++;
            highwayState[0][nv]++;

            // Check all events where this car overtakes other cars.
            int a = 0;
            for (int v = 1; v < nv; v++) {
                for (int p = 0; p < RLEN; p += v) {
                    if (p * nv <= RLEN * (nv - v) && highwayState[p][v] > 0) {
                        // We overtake these cars p / (nv - v) seconds from now.
                        int g = gcdmap[p][nv - v];
                        int tnum = p / g;
                        int tden = (nv - v) / g;
                        hitmap[tnum][tden] += highwayState[p][v];
                        a = Math.max(a, hitmap[tnum][tden]);
                    }
                }
            }

            // Wipe scratch pad.
            for (int v = 1; v < nv; v++) {
                for (int p = 0; p < RLEN; p += v) {
                    if (p * nv <= RLEN * (nv - v) && highwayState[p][v] > 0) {
                        int g = gcdmap[p][nv - v];
                        int tnum = p / g;
                        int tden = (nv - v) / g;
                        hitmap[tnum][tden] = 0;
                    }
                }
            }

            // Maximum number of lanes needed in any event involving this car.
            a += highwayState[0][nv];
            answer = Math.max(answer, a);
        }

        return answer;
    }


    public static void main(String[] args)
    {
        // Precompute GCD function.
        gcdmap = new int[RLEN][MAXV];
        for (int p = 0; p < RLEN; p++) {
            for (int v = 1; v < MAXV; v++) {
                gcdmap[p][v] = gcd(p, v);
            }
        }

        try {

            BufferedReader ir = new BufferedReader(
                                  new InputStreamReader(System.in));

            String s = ir.readLine();

            while (s != null) {

                int n = Integer.parseInt(s);
                ass(n >= 1 && n <= MAXN);

                cars = new JorisCar[n];

                for (int i = 0; i < n; i++) {
                    s = ir.readLine();
                    String[] w = s.split(" ");
                    int t = Integer.parseInt(w[0]);
                    int v = Integer.parseInt(w[1]);
                    ass(t >= 1 && t <= MAXT);
                    ass(v >= 1 && v <= MAXV);
                    cars[i] = new JorisCar(t, v);
                }

                int answer = solve();
                System.out.println(answer);

                s = ir.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
