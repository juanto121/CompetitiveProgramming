
// @EXPECTED_RESULTS@: CORRECT

#include <iostream>
#include <iomanip>
#include <vector>
#include <cassert>
#include <algorithm>
#include <string>

using namespace std;

int main()
{
    cout.precision(15);

    int n, m;

    while (cin >> n >> m)
    {
        vector<int> v;
        while (n--)
        {
            while (--m)
            {
                v.push_back(0);
            }

            string c;
            cin >> c;
            m = (c == "A") ? 11 : (c == "J" || c == "Q" || c == "K") ? 10 : stoi(c);

            v.push_back(m);
        }

        vector<double> p(v.size(), 0.0);

        for (unsigned i = 0; i < min<unsigned>(10, p.size()); ++i)
        {
            p[i] = 0.1; // this presumes there are at least 10 cards.
        }

        for (unsigned i = 0; i < v.size(); ++i)
        {
            if (v[i]) // we know which card is here
            {
                if (i + v[i] < p.size())
                {
                    p[i + v[i]] += p[i];
                }
            }
            else // we don't know which card is here
            {
                for (unsigned j = 2; j <= 14; ++j)
                {
                    int cval = (j <= 11) ? j : 10;
                    if (i + cval < p.size())
                    {
                        p[i + cval] += p[i] / 13;
                    }
                }
            }
        }

        cout << p.back() << endl;

    } // trial loop

    return 0;
}
