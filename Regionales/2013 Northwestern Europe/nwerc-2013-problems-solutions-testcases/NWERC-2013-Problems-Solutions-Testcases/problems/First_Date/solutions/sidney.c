
////////////////
// calendar.c //
////////////////

// @EXPECTED_RESULTS@: CORRECT

#include <stdio.h>
#include <time.h>
#include <assert.h>

// A sane 'mod' operation that returns a result in the range [ 0 ... (q - 1) ].
// C99 and up prescribe "round towards zero" for div(), and mod() is adapted to
// follow that strange convention via "div(p, q) * q + mod(p, q) == q".

int mod(int p, int q)
{
    assert(q > 0); // we only care for positive q
    int m = p % q;
    if (m < 0) m += q;
    return m;
}

// A sane 'div' operation that rounds down.
// C99 and up prescribe "round towards zero" --- a strange convention.

int div(int p, int q)
{
    return (p - mod(p, q)) / q;
}

// "count modular equivalents":
// Count the number of integers in the range [rLow .. rHigh] for which i === equalTo (mod modulus).
// If rHigh < rLow, we give back the negated value for the reversed range.
// Note that it is not possible to represent an empty range.

int cme(int rLow, int rHigh, int equalTo, int modulus)
{
    if (rLow > rHigh) return -cme(rHigh, rLow, equalTo, modulus);
    return 1 + div(rHigh - rLow - mod(equalTo - rLow, modulus), modulus);
}

// Assume that every month has 31 days, yield a sequence number for the date.
// A year would have 12*31 == 372 days in such a system.
// Note that we also count dates that do not exist, such as "April 31st".
// 1970-01-01 yields zero, earlier dates yield negative, later dates yield a positive integer.

int pseudoDate(int Y, int M, int D)
{
    return (Y - 1970) * 372 + (M - 1) * 31 + (D - 1);
}

// Calculate the sequence number for a given Julian date, by starting with a 'pseudoDate', and
// correcting for days that were over-counted, and leap-years.
// 1970-01-01 yields zero, earlier dates yield negative, later dates yield a positive integer.

int JulianDateToSequenceNumber(int JY, int JM, int JD)
{
    int pd = pseudoDate(JY, JM, JD);
    int seqnr  = pd;

    seqnr -= cme(0, pd, pseudoDate(1970,  2, 31), 372    ); // correct for over-counted 31 february
    seqnr -= cme(0, pd, pseudoDate(1970,  2, 30), 372    ); // correct for over-counted 30 february
    seqnr -= cme(0, pd, pseudoDate(1970,  2, 29), 372    ); // correct for over-counted 29 february (usually, Feb has 28 days)
    seqnr += cme(0, pd, pseudoDate(1972,  2, 29), 372 * 4); // .. but: add Feb 29 every fourth year
    seqnr -= cme(0, pd, pseudoDate(1970,  4, 31), 372    ); // correct for over-counted 31 april
    seqnr -= cme(0, pd, pseudoDate(1970,  6, 31), 372    ); // correct for over-counted 31 june
    seqnr -= cme(0, pd, pseudoDate(1970,  9, 31), 372    ); // correct for over-counted 31 september
    seqnr -= cme(0, pd, pseudoDate(1970, 11, 31), 372    ); // correct for over-counted 31 november
    return seqnr;
}

int main(void)
{
    unsigned JY, JM, JD; // Julian Year, Month, Day

    while (scanf("%u-%u-%u", &JY, &JM, &JD) == 3)
    {
        // The "+ 14" is to account for the 13-day offset on 1970-01-01, plus an extra day to proceed
        // to the next day.

        int seqnr = JulianDateToSequenceNumber(JY, JM, JD) + 14;

        // Translating from a day number to a Gregorian date is quite intricate.
        // We take a shortcut here by using the "gmtime()" function as provided by the standard library.
        //
        // The C99 standard does not specify if/how this function should handle the Julian->Gregorian transition.
        //
        // It turns out that the gmtime() implementation provided by glibc implements the so-called
        // 'proleptic' Gregorian calendar; this means that the Gregorian calendar is retroactively
        // extended into the past even before the point where it was actually adopted.
        //
        // In other words, the gmtime() function does not attempt to handle the Julian->Gregorian transition,
        // which is ideal for our application.
        //
        // Note that the correctness of this depends on the available range in time_t.
        // The code as given works for 64-bit time_t.

        time_t t = 86400LL * seqnr; // from days to seconds
        struct tm * parts = gmtime(&t); // determine year, month, day
        printf("%04d-%02d-%02d\n", parts->tm_year + 1900, parts->tm_mon + 1, parts->tm_mday);
    }

    return 0; // Success!
}
