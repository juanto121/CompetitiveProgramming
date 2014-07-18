
/////////////////////
// calendar-lut.cc //
/////////////////////

// @EXPECTED_RESULTS@: CORRECT

#include <iostream>
#include <cstdio>
#include <map>
#include <string>

using namespace std;

int month_days(const int M, const bool is_leapyear)
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

void progress_date(int & Y, int & M, int & D, const bool is_leapyear)
{
    if (++D > month_days(M, is_leapyear))
    {
        D = 1;
        if (++M > 12)
        {
            M = 1;
            ++Y;
        }
    }
}

void progress_gregorian_date(int & GY, int & GM, int & GD)
{
    const bool is_leapyear = (GY % 4 == 0) ^ (GY % 100 == 0) ^ (GY % 400 == 0);
    progress_date(GY, GM, GD, is_leapyear);
}

void progress_julian_date(int & JY, int & JM, int & JD)
{
    const bool is_leapyear = (JY % 4 == 0);
    progress_date(JY, JM, JD, is_leapyear);
}

int main()
{
    // Assemble lookup table

    int JY = 1582, JM = 10, JD =  4; // last day of Julian calendar (Julian date)
    int GY = 1582, GM = 10, GD = 15; // next day Gregorian calendar (Gregorian date)

    map<string, string> js_to_gs_nextday;

    while (GY < 10000)
    {
        char js[20], gs[20];

        sprintf(js, "%04d-%02d-%02d", JY, JM, JD);
        sprintf(gs, "%04d-%02d-%02d", GY, GM, GD);

        js_to_gs_nextday.insert(make_pair(string(js), string(gs)));

        progress_julian_date(JY, JM, JD);
        progress_gregorian_date(GY, GM, GD);
    }

    // Use lookup table

    string julian;
    while (cin >> julian)
    {
        cout << js_to_gs_nextday[julian] << endl;
    }

    return 0;
}
