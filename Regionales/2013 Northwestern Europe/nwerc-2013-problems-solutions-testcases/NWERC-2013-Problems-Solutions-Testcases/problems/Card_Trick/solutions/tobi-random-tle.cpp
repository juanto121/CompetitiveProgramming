// @EXPECTED_RESULTS@: TIMELIMIT

#include <iostream>
#include <iomanip>
#include <cstdlib>
#include <vector>

#define MAX_TRIES 1000000
#define UNKNOWN -1.0
#define FOR(i,a,b) for (int i = (a); i < (b); i++)
#define FORD(i,a,b) for (int i = int(b)-1; i >= (a); i--)
#define sz(c) int((c).size())
using namespace std;

int main() {
	string buf;
	int numSelCards, secret;

	srand(42);
	while (cin >> numSelCards >> secret) {
		vector<double> probs; // the probability that a card will lead to the final card
		FOR(i, 0, secret - 1) probs.push_back(UNKNOWN); // is computed later
		probs.push_back(1.); // next card in chain, leads definitely to final card

		FOR(i, 0, numSelCards - 1) {
			cin >> buf;
			switch (buf[0]) { // determine number of skipped cards
			case 'A':
				secret = 11;
				break;
			case 'J':
			case 'Q':
			case 'K':
				secret = 10;
				break;
			default:
				secret = atoi(buf.c_str());
			}
			
			FOR(j, 1, secret) probs.push_back(UNKNOWN); // again skip cards
			probs.push_back(1.); // leads to final card
		}
		cin >> buf; // last card does not matter at all

		int cnt = 0; // how many times did we find the final card?
		int tries = MAX_TRIES;
		FOR(i, 0, tries) {
			// start with selection of a random card
			int pt = rand() % 10;
			while (true) {
				if (pt >= sz(probs)) break; // jumped over final card
				if (probs[pt] == 1.) {
					cnt++; // final card (or one in the 'chain')
					break;
				}
				// draw a random card
				int r = (rand() % 13) + 2;
				if (r == 14) r = 11; // A
				else if (r > 10) r = 10; // J, Q, K

				pt += r; // skip cards
			}
		}
		cout << setprecision(15) << ((double) cnt/(double) tries) << endl;
	}

	return 0;
}
