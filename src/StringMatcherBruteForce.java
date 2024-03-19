public class StringMatcherBruteForce extends StringMatcher{
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
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && T.charAt(i + j) == P.charAt(j)) {
                j++;
                comparisons++;
            }
            comparisons++;
            if (j == m) {
                return i;
            }
        }
        comparisons++;
        return -1;
    }

    public int getNumberOfComparisons() {
        return comparisons-1; // Find out why -1
    }
}
