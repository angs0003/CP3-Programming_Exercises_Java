public class BoyerMoore extends StringMatcher{
    String T;
    String P;
    int comparisons;

    public void setTarget(String target) {
        T = target;
    }

    public void setPattern(String pattern) {
        P = pattern;
    }

    public int match() {
        int n = T.length();
        int m = P.length();
        comparisons = 0;
        int[] last = buildLast(P);
        int i = m - 1;
        if (i > n - 1) {
            return -1;
        }
        int j = m - 1;
        do {
            if (P.charAt(j) == T.charAt(i)) {
                if (j == 0) {
                    return i;
                } else {
                    i--;
                    j--;
                }
            } else {
                i = i + m - Math.min(j, 1 + last[T.charAt(i)]);
                j = m - 1;
            }
            comparisons++;
        } while (i <= n - 1);
        comparisons--;
        return -1;
    }

    public int getNumberOfComparisons() {
        return comparisons+1; // Find out why +1
    }

    private int[] buildLast(String P) {
        final int R = 256;
        int[] last = new int[R];
        for (int i = 0; i < R; i++) {
            last[i] = -1;
        }
        for (int i = 0; i < P.length(); i++) {
            last[P.charAt(i)] = i;
        }
        return last;
    }


}
