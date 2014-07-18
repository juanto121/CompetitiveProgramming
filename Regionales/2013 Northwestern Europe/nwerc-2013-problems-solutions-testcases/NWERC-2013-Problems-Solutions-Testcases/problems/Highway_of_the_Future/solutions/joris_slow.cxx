/*
 * Solution for Quantum Highway.
 *
 * This solution simply considers all pairs of cars, but
 * otherwise it has very low overhead. 
 */

// @EXPECTED_RESULTS@: TIMELIMIT

#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>
#include <cassert>

using namespace std;

static const int RLEN = 100;
static const int MAXV = 100;

static int gcdtbl[RLEN+1][MAXV];

int gcd(int a, int b)
{
    while (b > 0) {
        int t = b;
        b = a % b;
        a = t;
    }
    return a;
}


void reduce(int *a, int *b) {

    assert(*a >= 0 && *a <= RLEN);
    assert(*b > 0 && *b < MAXV);

    int g = gcdtbl[*a][*b];
    *a /= g;
    *b /= g;
}


int main()
{
    // Precompute GCD function.
    for (int a = 0; a <= RLEN; a++) {
        for (int b = 1; b < MAXV; b++) {
            gcdtbl[a][b] = gcd(a, b);
        }
    }

    for (int n = 0; cin >> n; ) {

        vector<pair<int,int>> cars;
        cars.reserve(n);
        for (int i = 0; i < n; i++) {
            int t, v;
            cin >> t >> v;
            cars.push_back(make_pair(t, v));
        }

        sort(cars.begin(), cars.end());

        vector<pair<pair<int,int>,int>> uniqCars;
        uniqCars.reserve(n);
        for (int i = 0; i < n; i++) {
            if (i == 0 ||
                cars[i].first != cars[i-1].first ||
                cars[i].second != cars[i-1].second) {
                uniqCars.push_back(make_pair(cars[i], 1));
            } else {
                uniqCars.back().second++;
            }
        }

        int answer = 0;
        unordered_map<int,int> hitmap;
        hitmap.reserve(n);

        for (int i = 0, nn = uniqCars.size(); i < nn; i++) {
            int t = uniqCars[i].first.first;
            int v = uniqCars[i].first.second;
            int m = uniqCars[i].second;

            answer = max(answer, m);

            hitmap.clear();

            for (int j = 0; j < i; j++) {
                int t2 = uniqCars[j].first.first;
                int v2 = uniqCars[j].first.second;
                int m2 = uniqCars[j].second;
                if (v > v2) {
                    int ta = (t - t2) * v2;
                    int tb = v - v2;
                    if (ta * v <= RLEN * tb) {
                        reduce(&ta, &tb);
                        int k = ta * MAXV + tb;
                        int& h = hitmap[k];
                        h += m2;
                        answer = max(answer, h + m);
                    }
                }
            }
        }

        cout << answer << endl;
    }

    return 0;
}

