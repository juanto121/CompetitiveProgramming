/* Solution to Calendar. */

// @EXPECTED_RESULTS@: CORRECT

import java.io.*;
import java.util.*;


public class Joris
{

    /* Return number of days in specified year/month on Julian calendar. */
    static int julianMonthLength(int y, int m)
    {
        switch (m) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                return (y % 4 == 0) ? 29 : 28;
            default:
                return 30;
        }
    }


    /* Convert Julian date to absolute day number.
       Day 0 is 0001-01-01. */
    static int julianToDaynr(int y, int m, int d)
    {
        int a = (y - 1) * 365 + (y - 1) / 4;
        for (int i = 1; i < m; i++)
            a += julianMonthLength(y, i);
        return a + d - 1;
    }


    /* Determine if this is a leap year on the Gregorian calendar. */
    static boolean gregorianLeapYear(int y)
    {
        return (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0));
    }


    /* Return number of days in specified month/year on Gregorian calendar. */
    static int gregorianMonthLength(int y, int m)
    {
        switch (m) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                return gregorianLeapYear(y) ? 29 : 28;
            default:
                return 30;
        }
    }


    /* Convert absolute day number to Gregorian date.
       Day 0 is 0001-01-01. */
    static int[] daynrToGregorian(int a)
    {
        int y = a / 366;
        a -= y * 365 + y / 4 - y / 100 + y / 400;
        y += 1;
        while (a > 365 || (a == 365 && !gregorianLeapYear(y))) {
            a -= gregorianLeapYear(y) ? 366 : 365;
            y += 1;
        }
        int m = 1;
        while (true) {
            int mlen = gregorianMonthLength(y, m);
            if (a < mlen)
                break;
            a -= mlen;
            m += 1;
        }
        return new int[] { y, m, a + 1 };
    }


    /* Main program. */
    public static void main(String[] args)
    {

        try {

            BufferedReader ir = new BufferedReader(
                                  new InputStreamReader(System.in));

            String s = ir.readLine();

            while (s != null) {

                assert (s.length() == 10);
                assert (s.charAt(4) == '-' && s.charAt(7) == '-');

                int jy = Integer.parseInt(s.substring(0, 4));
                int jm = Integer.parseInt(s.substring(5, 7));
                int jd = Integer.parseInt(s.substring(8, 10));

                assert (jy > 1582 || (jy == 1582 && (jm > 10 || (jm == 10 && jd >= 4))));
                assert (jy < 9999 || (jy == 9999 && (jm < 10 || (jm == 10 && jd <= 18))));
                assert (jm >= 1 && jm <= 12);
                assert (jd >= 1 && jd <= julianMonthLength(jy, jm));

                int d = julianToDaynr(jy, jm, jd);
                int[] g = daynrToGregorian(d - 1);
                int gy = g[0];
                int gm = g[1];
                int gd = g[2];

                System.out.println(String.format("%04d-%02d-%02d", gy, gm, gd));

                s = ir.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}

/* end */
