public class KMP extends StringMatcher{ // Knuth-Morris-Pratt
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
        int[] fail = computeFail(P);
        int i = 0;
        int j = 0;
        while (i < n) {
            if (P.charAt(j) == T.charAt(i)) {
                if (j == m - 1) {
                    return i - m + 1;
                }
                i++;
                j++;
            } else if (j > 0) {
                j = fail[j - 1];
            } else {
                i++;
            }
            comparisons++;
        }
        comparisons--;
        return -1;
    }

    public int getNumberOfComparisons() {
        return comparisons+1; // Find out why +1
    }

    private int[] computeFail(String P) {
        int m = P.length();
        int[] fail = new int[m];
        int i = 1;
        int j = 0;
        while (i < m) {
            if (P.charAt(j) == P.charAt(i)) {
                fail[i] = j + 1;
                i++;
                j++;
            } else if (j > 0) {
                j = fail[j - 1];
            } else {
                fail[i] = 0;
                i++;
            }
        }
        return fail;
    }
    

}
