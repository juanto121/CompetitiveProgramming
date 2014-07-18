/*
 * Solution for Quantum Highway.
 *
 * This is a hybrid approach, based on Peter's fast method for
 * collision discovery and Joris' fast method for car counting.
 */

// @EXPECTED_RESULTS@: CORRECT

#include <iostream>
#include <vector>
#include <list>
#include <algorithm>
#include <cassert>

using namespace std;

static const int RLEN = 100;
static const int MAXV = 100;

static int gcdtbl[RLEN+1][MAXV];

struct Car {
    int t, v, m;
    Car() { }
    Car(int t, int v, int m) : t(t), v(v), m(m) { }
};


bool orderByArrival(const Car& a, const Car& b)
{
    return (a.t < b.t) || (a.t == b.t && a.v < b.v);
}


bool orderByDeparture(const Car& a, const Car& b)
{
    int dt = (a.t - b.t) * a.v * b.v + RLEN * (b.v - a.v);
    return (dt < 0) || (dt == 0 && a.v > b.v);
}


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


int solve(const vector<Car>& inputCars)
{
    // Sort cars by time of arrival on the highway;
    // if cars arrive at the same time, the slower car goes first.
    vector<Car> cars(inputCars);
    sort(cars.begin(), cars.end(), orderByArrival);

    // Merge identical cars.
    int n = 0;
    for (size_t i = 0, ni = cars.size(); i < ni; i++) {
        if (n == 0 ||
            cars[n-1].t != cars[i].t ||
            cars[n-1].v != cars[i].v) {
            cars[n++] = cars[i];
        } else {
            cars[n-1].m++;
        }
    }
    cars.resize(n);

    // Make a linked list of cars sorted by time of arrival.
    list<Car> carsByArrival(cars.begin(), cars.end());

    // Sort cars by time of departure from the highway;
    // if cars depart at the same time, the faster car goes first.
    sort(cars.begin(), cars.end(), orderByDeparture);

    // Create scratch pad to mark passed cars.
    int hitmap[RLEN+1][MAXV];
    int hitmapalloc[RLEN+1][MAXV];
    for (int a = 0; a <= RLEN; a++) {
        for (int b = 0; b < MAXV; b++) {
            hitmapalloc[a][b] = -1;
        }
    }

    int answer = 0;

    // Process cars by time of departure.
    for (int i = 0, ni = cars.size(); i < ni; i++) {

        const Car& ci = cars[i];

        // Need at least enough lanes for this bunch of cars.
        answer = max(answer, ci.m);

        // Consider all cars which arrive earlier than this car
        // but depart later. These are precisely the cars that we
        // pass on the highway.
        for (list<Car>::iterator pj = carsByArrival.begin(); ; pj++) {

            const Car& cj = *pj;

            if (ci.t == cj.t && ci.v == cj.v) {
                // We reached ourselves on the list. This means we have
                // seen all passed cars.
                // Remove this car from the arrival list; it will not be
                // part of any further collisions we may discover.
                carsByArrival.erase(pj);
                break;
            }

            assert(ci.v > cj.v);
            assert(ci.t >= cj.t);

            // Calculate relative time where we pass this car.
            int ta = (ci.t - cj.t) * cj.v;
            int tb = ci.v - cj.v;
            reduce(&ta, &tb);

            // Mark the passed cars on the map.
            assert(ta >= 0 && ta <= RLEN);
            assert(tb > 0 && tb < MAXV);
            if (hitmapalloc[ta][tb] != i) {
                hitmapalloc[ta][tb] = i;
                hitmap[ta][tb] = 0;
            }
            hitmap[ta][tb] += cj.m;

            // Make sure we have enough lanes for all cars involved.
            answer = max(answer, hitmap[ta][tb] + ci.m);
        }    

    }

    return answer;
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

        vector<Car> cars;
        cars.reserve(n);
        for (int i = 0; i < n; i++) {
            int t, v;
            cin >> t >> v;
            cars.push_back(Car(t, v, 1));
        }

        int answer = solve(cars);
        cout << answer << endl;
    }

    return 0;
}

