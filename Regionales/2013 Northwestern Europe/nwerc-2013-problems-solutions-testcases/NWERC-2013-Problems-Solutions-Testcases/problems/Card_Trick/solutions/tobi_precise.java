// @EXPECTED_RESULTS@: CORRECT

import java.io.*;
import java.util.*;
import java.math.*;

public class tobi_precise {
	public static final int SCALE = 100;

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int numSelCards = sc.nextInt();
			int secret = sc.nextInt();

			ArrayList<BigDecimal> probs = new ArrayList<>();
			for (int i = 0; i < secret - 1; i++) probs.add(null);
			probs.add(new BigDecimal(1, new MathContext(SCALE)));

			for (int i = 0; i < numSelCards - 1; i++) {
				String buf = sc.next();
				switch (buf.charAt(0)) {
					case 'A':
						secret = 11;
						break;
					case 'J':
					case 'Q':
					case 'K':
						secret = 10;
						break;
					default:
						secret = Integer.parseInt(buf);
				}
				for (int j = 1; j < secret; j++) probs.add(null);
				probs.add(new BigDecimal(1, new MathContext(SCALE)));
			}
			sc.next();
			for (int i = 0; i < 10; i++) {
				probs.add(null);
			}

			// use dynamic programming
			for (int i = probs.size() - 1; i >= 0; i--) {
				if (probs.get(i) == null) {
					BigDecimal cur = new BigDecimal(0, new MathContext(SCALE));
					for (int j = 2; j < 10; j++) { // cards 2 - 9
						if (j + i < probs.size()) {
							cur = cur.add(probs.get(j + i));
						}
					}
					if (i + 10 < probs.size()) { // cards 10, J, Q, K
						cur = cur.add(new BigDecimal(4, new MathContext(SCALE)).multiply(probs.get(10 + i)));
					}
					if (i + 11 < probs.size()) { // card A
						cur = cur.add(probs.get(11 + i));
					}
					probs.set(i, cur.divide(new BigDecimal(13., new MathContext(SCALE)), SCALE, RoundingMode.HALF_UP)); // all 13 card faces have equal probability
				}
			}

			BigDecimal total = new BigDecimal(0., new MathContext(SCALE));
			for (int i = 0; i < 10; i++) {
				total = total.add(probs.get(i)); // Alice chose one of the first 10 cards
			}
			System.out.println(total.divide(new BigDecimal(10, new MathContext(SCALE)), 25, RoundingMode.HALF_UP));
		}
	}
}
