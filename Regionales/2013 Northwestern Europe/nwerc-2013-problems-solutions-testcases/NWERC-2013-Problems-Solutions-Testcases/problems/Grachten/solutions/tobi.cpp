// @EXPECTED_RESULTS@: CORRECT
#include <iostream>
using namespace std;

int gcd(int a, int b) {
	return (b == 0) ? a : gcd(b, a % b);
}

int main(int argc, char **argv) {
	int AB, AC, BD;
	while (cin >> AB >> AC >> BD) {
		if (cin.eof() || !cin.good()) break;
		int e = AB*AC;
		int d = BD-AC;
		int ggt = gcd(e,d);
		cout << e/ggt << "/" << d/ggt << endl;
	}
	return 0;
}
