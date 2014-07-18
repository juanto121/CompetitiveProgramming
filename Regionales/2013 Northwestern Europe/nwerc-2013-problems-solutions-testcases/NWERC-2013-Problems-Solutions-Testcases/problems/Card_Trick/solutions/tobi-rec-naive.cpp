// @EXPECTED_RESULTS@: TIMELIMIT

#include <iostream>
#include <iomanip>
#include <cstdlib>
#include <vector>

#define UNKNOWN -1.0
#define FOR(i,a,b) for (int i = (a); i < (b); i++)
#define FORD(i,a,b) for (int i = int(b)-1; i >= (a); i--)
#define sz(c) int((c).size())
using namespace std;

vector<double> probs; // the probability that a card will lead to the final card
double rec(int index) {
	if (sz(probs) <= index) return 0.; // cards behind final card is not possible
	if (probs[index] == 1. || probs[index] == 0.) return probs[index]; // XXX: only use fixed value but not table itself
	probs[index] = 0.; // add all probabilities that can be reached
	FOR(j, 2, 10) { // cards 2 - 9
		probs[index] += rec(j + index);
	}
	probs[index] += 4 * rec(10 + index); // cards 10, J, Q, K
	probs[index] += rec(11 + index); // card A
	probs[index] /= 13.; // all 13 card faces have equal probability
	return probs[index];
}

int main() {
	string buf;
	int numSelCards, secret;

	while (cin >> numSelCards >> secret) {
		probs.clear();
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

		double total = 0.;
		FOR(i, 0, 10) total += rec(i); // Alice chose one of the first 10 cards
		cout << setprecision(15) << (total/10.) << endl;
	}

	return 0;
}
