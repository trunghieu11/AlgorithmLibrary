package myTool.StandardTool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/1/12
 * Time: 5:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class MiscUtils {
    public static final int[] DX4 = {1, 0, -1, 0};
    public static final int[] DY4 = {0, -1, 0, 1};

    public static final int[] DX8 = {1, 1, 1, 0, -1, -1, -1, 0};
    public static final int[] DY8 = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static long josephProblem(long n, int k) {
        if (n == 1)
            return 0;
        if (k == 1)
            return n - 1;
        if (k > n)
            return (josephProblem(n - 1, k) + k) % n;
        long count = n / k;
        long result = josephProblem(n - count, k);
        result -= n % k;
        if (result < 0)
            result += n;
        else
            result += result / (k - 1);
        return result;
    }

    public static boolean isValidCell(int row, int column, int rowCount, int columnCount) {
        return row >= 0 && row < rowCount && column >= 0 && column < columnCount;
    }

    public static List<Integer> getPath(int[] last, int destination) {
        List<Integer> path = new ArrayList<Integer>();
        while (destination != -1) {
            path.add(destination);
            destination = last[destination];
        }
        Collections.reverse(path);
        return path;
    }

    public static List<Integer> getPath(int[][] lastIndex, int[][] lastPathNumber, int destination, int pathNumber) {
        List<Integer> path = new ArrayList<Integer>();
        while (destination != -1 || pathNumber != 0) {
            path.add(destination);
            int nextDestination = lastIndex[destination][pathNumber];
            pathNumber = lastPathNumber[destination][pathNumber];
            destination = nextDestination;
        }
        Collections.reverse(path);
        return path;
    }

    public static long maximalRectangleSum(long[][] array) {
        int n = array.length;
        int m = array[0].length;
        long[][] partialSums = new long[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            long rowSum = 0;
            for (int j = 0; j < m; j++) {
                rowSum += array[i][j];
                partialSums[i + 1][j + 1] = partialSums[i][j + 1] + rowSum;
            }
        }
        long result = Long.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                long minPartialSum = 0;
                for (int k = 1; k <= n; k++) {
                    long current = partialSums[k][j + 1] - partialSums[k][i];
                    result = Math.max(result, current - minPartialSum);
                    minPartialSum = Math.min(minPartialSum, current);
                }
            }
        }
        return result;
    }

    public static int parseIP(String ip) {
        String[] components = ip.split("[.]");
        int result = 0;
        for (int i = 0; i < 4; i++)
            result += (1 << (24 - 8 * i)) * Integer.parseInt(components[i]);
        return result;
    }

    public static long binarySearch(long from, long to, Function<Long, Boolean> function) {
        while (from < to) {
            long argument = from + (to - from) / 2;
            if (function.value(argument))
                to = argument;
            else
                from = argument + 1;
        }
        return from;
    }

    public static<T> boolean equals(T first, T second) {
        return first == null && second == null || first != null && first.equals(second);
    }

    public static boolean isVowel(char ch) {
        ch = Character.toUpperCase(ch);
        return ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U' || ch == 'Y';
    }

    public static boolean isStrictVowel(char ch) {
        ch = Character.toUpperCase(ch);
        return ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
    }

    public static void decreaseByOne(int[]...arrays) {
        for (int[] array : arrays) {
            for (int i = 0; i < array.length; i++)
                array[i]--;
        }
    }

    public static void increaseByOne(int[]...arrays) {
        for (int[] array : arrays) {
            for (int i = 0; i < array.length; i++)
                array[i]++;
        }
    }

    public static String buildIP(int mask) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (i != 0)
                result.append('.');
            result.append(mask >> (24 - 8 * i) & 255);
        }
        return result.toString();
    }

    public static String removeLeadingZero(String s) {
        while (s.length() > 1 && s.charAt(0) == '0')
            s = s.substring(1);
        return s;
    }

    public boolean isPalindrome(String word) {
        if (word.length() <= 1)
            return true;
        if (word.charAt(0) != word.charAt(word.length() - 1))
            return false;
        int i = 0;
        int j = word.length() - 1;
        while (i < j) {
            if (word.charAt(i) != word.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
