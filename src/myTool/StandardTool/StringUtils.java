package myTool.StandardTool;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/2/12
 * Time: 2:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringUtils {
    public static int[] zAlgorithm(CharSequence s) {
        int length = s.length();
        int[] z = new int[length];
        z[0] = 0;

        int left = 0, right = 0;
        for (int i = 1; i < length; i++) {
            if (i > right) {
                int j;
                //noinspection StatementWithEmptyBody
                for (j = 0; i + j < length && s.charAt(i + j) == s.charAt(j); j++) ;
                z[i] = j;
                left = i;
                right = i + j - 1;
            } else if (z[i - left] < right - i + 1)
                z[i] = z[i - left];
            else {
                int j;
                //noinspection StatementWithEmptyBody
                for (j = 1; right + j < length && s.charAt(right + j) == s.charAt(right - i + j); j++) ;
                z[i] = right - i + j;
                left = i;
                right = right + j - 1;
            }
        }
        return z;
    }

    public static String reverse(String sample) {
        StringBuilder result = new StringBuilder(sample);
        result.reverse();
        return result.toString();
    }

    public static int[] prefixFunction(CharSequence s) {
        int l = s.length();
        int[] p = new int[l];
        int k = 0;
        for (int i = 1; i < l; i++) {
            while ((k > 0) && (s.charAt(k) != s.charAt(i)))
                k = p[k - 1];
            if (s.charAt(k) == s.charAt(i))
                k++;
            p[i] = k;
        }
        return p;
    }

    public static int[][] buildPrefixAutomaton(CharSequence s) {
        int[] prefixFunction = prefixFunction(s);
        int[][] result = new int[s.length() + 1][26];
        result[0][s.charAt(0) - 'a'] = 1;
        for (int i = 1; i <= s.length(); i++) {
            System.arraycopy(result[prefixFunction[i - 1]], 0, result[i], 0, 26);
            if (i != s.length())
                result[i][s.charAt(i) - 'a'] = i + 1;
        }
        return result;
    }

    public static String doubleToString(double x, int n) {
        StringBuilder sb = new StringBuilder();
        if(x < 0){
            sb.append('-');
            x = -x;
        }
        x += Math.pow(10, -n)/2;
//		if(x < 0){ x = 0; }
        sb.append((long)x);
        sb.append(".");
        x -= (long)x;
        for(int i = 0;i < n;i++){
            x *= 10;
            sb.append((int)x);
            x -= (int)x;
        }
        return sb.toString();
    }
}
