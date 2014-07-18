// @EXPECTED_RESULTS@: CORRECT

#include <iostream>
#include <iomanip>
#include <cstdlib>
#include <vector>

#define UNKNOWN -1.0
#define FOR(i,a,b) for (int i = (a); i < (b); i++)
#define FORD(i,a,b) for (int i = int(b)-1; i >= (a); i--)
#define sz(c) int((c).size())
using namespace std;

int main() {
	string buf;
	int numSelCards, secret;

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
		FOR(i, 0, 10) probs.push_back(UNKNOWN); // at least ten cards

		// use dynamic programming
		FORD(i, 0, sz(probs)) { // FORD -> compute from the back
			if (probs[i] == UNKNOWN) {
				double cur = 0.; // add all probabilities that can be reached
				FOR(j, 2, 10) { // cards 2 - 9
					if (j + i < sz(probs)) {
						cur += probs[j + i];
					}
				}
				if (i + 10 < sz(probs)) { // cards 10, J, Q, K
					cur += 4 * probs[10 + i];
				}
				if (i + 11 < sz(probs)) { // card A
					cur += probs[11 + i];
				}
				probs[i] = cur / 13.; // all 13 card faces have equal probability
			}
		}

		double total = 0.;
		FOR(i, 0, 10) total += probs[i]; // Alice chose one of the first 10 cards
		cout << setprecision(15) << (total/10.) << endl;
	}

	return 0;
}
