
/////////////////////
// calendar-slow.c //
/////////////////////

// @EXPECTED_RESULTS@: TIMELIMIT

#include <assert.h>
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

static unsigned month_days(const unsigned M, const bool is_leapyear)
{
    if (M == 4 || M == 6 || M == 9 || M == 11)
    {
        return 30;
    }

    if (M != 2)
    {
        return 31;
    }

    return 28 + is_leapyear;
}

static void progress_date(unsigned * Y, unsigned * M, unsigned * D, const bool is_leapyear)
{
    if (++(*D) > month_days(*M, is_leapyear))
    {
        *D = 1;
        if (++(*M) > 12)
        {
            *M = 1;
            ++(*Y);
        }
    }
}

static void progress_gregorian_date(unsigned * GY, unsigned * GM, unsigned * GD)
{
    const bool is_leapyear = ((*GY) % 4 == 0) ^ ((*GY) % 100 == 0) ^ ((*GY) % 400 == 0);
    progress_date(GY, GM, GD, is_leapyear);
}

static void progress_julian_date(unsigned * JY, unsigned * JM, unsigned * JD)
{
    const bool is_leapyear = ((*JY) % 4 == 0);
    progress_date(JY, JM, JD, is_leapyear);
}

int main(void)
{
    unsigned XJY,  XJM, XJD;
    while (scanf("%u-%u-%u", &XJY, &XJM, &XJD) == 3)
    {
        unsigned JY = 1582, JM = 10, JD =  4; // last day of Julian calendar (Julian date)
        unsigned GY = 1582, GM = 10, GD = 15; // next day Gregorian calendar (Gregorian date)

	while (true)
	{
	    if (XJY == JY && XJM == JM && XJD == JD)
	    {
	        printf("%04d-%02d-%02d\n", GY, GM, GD);
		break;
	    }

	    progress_julian_date(&JY, &JM, &JD);
	    progress_gregorian_date(&GY, &GM, &GD);
	}
    }

    return 0;
}
