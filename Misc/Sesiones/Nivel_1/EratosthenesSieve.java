Code snippets

Java implementation

public void runEratosthenesSieve(int upperBound) {
      int upperBoundSquareRoot = (int) Math.sqrt(upperBound);
      boolean[] isComposite = new boolean[upperBound + 1];
      for (int m = 2; m <= upperBoundSquareRoot; m++) {
            if (!isComposite[m]) {
                  System.out.print(m + " ");
                  for (int k = m * m; k <= upperBound; k += m)
                        isComposite[k] = true;
            }
      }
      for (int m = upperBoundSquareRoot; m <= upperBound; m++)
            if (!isComposite[m])
                  System.out.print(m + " ");
}
C++ implementation

void runEratosthenesSieve(int upperBound) {
      int upperBoundSquareRoot = (int)sqrt((double)upperBound);
      bool *isComposite = new bool[upperBound + 1];
      memset(isComposite, 0, sizeof(bool) * (upperBound + 1));
      for (int m = 2; m <= upperBoundSquareRoot; m++) {
            if (!isComposite[m]) {
                  cout << m << " ";
                  for (int k = m * m; k <= upperBound; k += m)
                        isComposite[k] = true;
            }
      }
      for (int m = upperBoundSquareRoot; m <= upperBound; m++)
            if (!isComposite[m])
                  cout << m << " ";
      delete [] isComposite;
}