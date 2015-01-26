package myTool.DataStructureTool;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 9/30/12
 * Time: 12:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArrayUtils {
    private static double epsilon = 1e-8;
    private static int[] tempInt = new int[0];

    public static int[] createOrder(int size) {
        int[] order = new int[size];
        for (int i = 0; i < size; i++)
            order[i] = i;
        return order;
    }

    public static int[] sort(int[] array, IntComparator comparator) {
        return sort(array, 0, array.length, comparator);
    }

    public static int[] sort(int[] array, int from, int to, IntComparator comparator) {
        if (from == 0 && to == array.length)
            new IntArray(array).inPlaceSort(comparator);
        else
            new IntArray(array).subList(from, to).inPlaceSort(comparator);
        return array;
    }

    public static void reverse(int[] array) {
        reverse(array, 0, array.length);
    }

    public static void reverse(long[] array) {
        reverse(array, 0, array.length);
    }

    public static void reverse(double[] array) {
        reverse(array, 0, array.length);
    }

    public static void reverse(int[] array, int from, int to) {
        for (int i = from, j = to - 1; i < j; i++, j--) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void reverse(long[] array, int from, int to) {
        for (int i = from, j = to - 1; i < j; i++, j--) {
            long temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void reverse(double[] array, int from, int to) {
        for (int i = from, j = to - 1; i < j; i++, j--) {
            double temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private static void ensureCapacityInt(int size) {
        if (tempInt.length >= size)
            return;
        size = Math.max(size, tempInt.length << 1);
        tempInt = new int[size];
    }

    private static void sortImpl(int[] array, int from, int to, int[] temp, int fromTemp, int toTemp, IntComparator comparator) {
        if (to - from <= 1)
            return;
        int middle = (to - from) >> 1;
        int tempMiddle = fromTemp + middle;
        sortImpl(temp, fromTemp, tempMiddle, array, from, from + middle, comparator);
        sortImpl(temp, tempMiddle, toTemp, array, from + middle, to, comparator);
        int index = from;
        int index1 = fromTemp;
        int index2 = tempMiddle;
        while (index1 < tempMiddle && index2 < toTemp) {
            if (comparator.compare(temp[index1], temp[index2]) <= 0)
                array[index++] = temp[index1++];
            else
                array[index++] = temp[index2++];
        }
        if (index1 != tempMiddle)
            System.arraycopy(temp, index1, array, index, tempMiddle - index1);
        if (index2 != toTemp)
            System.arraycopy(temp, index2, array, index, toTemp - index2);
    }

    public static Integer[] order(int size, Comparator<Integer> comparator) {
        Integer[] order = generateOrder(size);
        Arrays.sort(order, comparator);
        return order;
    }

    public static int[] order(final int[] array) {
        return sort(createOrder(array.length), new IntComparator() {
            public int compare(int first, int second) {
                if (array[first] < array[second])
                    return -1;
                if (array[first] > array[second])
                    return 1;
                return 0;
            }
        });
    }

    public static int[] order(final long[] array) {
        return sort(createOrder(array.length), new IntComparator() {
            public int compare(int first, int second) {
                if (array[first] < array[second])
                    return -1;
                if (array[first] > array[second])
                    return 1;
                return 0;
            }
        });
    }

    public static Integer[] generateOrder(int size) {
        Integer[] order = new Integer[size];
        for (int i = 0; i < size; i++)
            order[i] = i;
        return order;
    }

    public static long sumArray(int[] array) {
        long result = 0;
        for (int element : array)
            result += element;
        return result;
    }

    public static long sumArray(int[] array, int from, int to) {
        long result = 0;
        to = Math.min(to, array.length - 1);
        for (int i = from; i <= to; i++)
            result += array[i];
        return result;
    }

    public static long sumArray(long[] array) {
        long result = 0;
        for (long element : array)
            result += element;
        return result;
    }

    public static long sumArray(long[] array, int from, int to) {
        long result = 0;
        to = Math.min(to, array.length - 1);
        for (int i = from; i <= to; i++)
            result += array[i];
        return result;
    }

    public static void fill(char[][] array, char value) {
        for (char[] row : array)
            Arrays.fill(row, value);
    }

    public static void fill(byte[][] array, byte value) {
        for (byte[] row : array)
            Arrays.fill(row, value);
    }

    public static void fill(byte[][][] array, byte value) {
        for (byte[][] row : array)
            fill(row, value);
    }

    public static void fill(int[][] array, int value) {
        for (int[] row : array)
            Arrays.fill(row, value);
    }

    public static void fill(long[][] array, long value) {
        for (long[] row : array)
            Arrays.fill(row, value);
    }

    public static void fill(int[][][] array, int value) {
        for (int[][] subArray : array)
            fill(subArray, value);
    }

    public static void fill(int[][][][] array, int value) {
        for (int[][][] subArray : array)
            fill(subArray, value);
    }

    public static void fillColumn(long[][] array, int index, long value) {
        for (long[] row : array)
            row[index] = value;
    }

    public static void fillColumn(int[][] array, int index, int value) {
        for (int[] row : array)
            row[index] = value;
    }

    public static void fill(boolean[][] array, boolean value) {
        for (boolean[] row : array)
            Arrays.fill(row, value);
    }

    public static int[] range(int from, int to) {
        int[] result = new int[Math.max(from, to) - Math.min(from, to) + 1];
        int index = 0;
        if (to > from) {
            for (int i = from; i <= to; i++)
                result[index++] = i;
        } else {
            for (int i = from; i >= to; i--)
                result[index++] = i;
        }
        return result;
    }

    /*Them boi Nguyen Trung Hieu*/


    public static int[] radixSort(int[] f)
    {
        int[] to = new int[f.length];
        {
            int[] b = new int[65537];
            for(int i = 0;i < f.length;i++)b[1+(f[i]&0xffff)]++;
            for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
            for(int i = 0;i < f.length;i++)to[b[f[i]&0xffff]++] = f[i];
            int[] d = f; f = to;to = d;
        }
        {
            int[] b = new int[65537];
            for(int i = 0;i < f.length;i++)b[1+(f[i]>>>16)]++;
            for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
            for(int i = 0;i < f.length;i++)to[b[f[i]>>>16]++] = f[i];
            int[] d = f; f = to;to = d;
        }
        return f;
    }

    public static long[] radixSort(long[] f)
    {
        long[] to = new long[f.length];
        {
            int[] b = new int[65537];
            for(int i = 0;i < f.length;i++)b[1+(int)(f[i]&0xffff)]++;
            for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
            for(int i = 0;i < f.length;i++)to[b[(int)(f[i]&0xffff)]++] = f[i];
            long[] d = f; f = to;to = d;
        }
        {
            int[] b = new int[65537];
            for(int i = 0;i < f.length;i++)b[1+(int)(f[i]>>>16&0xffff)]++;
            for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
            for(int i = 0;i < f.length;i++)to[b[(int)(f[i]>>>16&0xffff)]++] = f[i];
            long[] d = f; f = to;to = d;
        }
        {
            int[] b = new int[65537];
            for(int i = 0;i < f.length;i++)b[1+(int)(f[i]>>>32&0xffff)]++;
            for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
            for(int i = 0;i < f.length;i++)to[b[(int)(f[i]>>>32&0xffff)]++] = f[i];
            long[] d = f; f = to;to = d;
        }
        {
            int[] b = new int[65537];
            for(int i = 0;i < f.length;i++)b[1+(int)(f[i]>>>48&0xffff)]++;
            for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
            for(int i = 0;i < f.length;i++)to[b[(int)(f[i]>>>48&0xffff)]++] = f[i];
            long[] d = f; f = to;to = d;
        }
        return f;
    }

    public static void safeSort(int[] array) {
        Collections.shuffle(asList(array));
        Arrays.sort(array);
    }

    private static List<Integer> asList(int[] array) {
        return new IntList(array);
    }

    public static long sumColumn(int[][] array, int x) {
        long answer = 0;
        for (int i = 0; i < array.length; i++) {
            answer += array[i][x];
        }
        return answer;
    }

    private static class IntList extends AbstractList<Integer> implements RandomAccess {

        int[] array;

        private IntList(int[] array) {
            this.array = array;
        }

        public Integer get(int index) {
            return array[index];
        }

        public Integer set(int index, Integer element) {
            int result = array[index];
            array[index] = element;
            return result;
        }

        public int size() {
            return array.length;
        }
    }

    public static void safeSort(long[] array) {
        Collections.shuffle(asList(array));
        Arrays.sort(array);
    }

    private static List<Long> asList(long[] array) {
        return new LongList(array);
    }

    private static class LongList extends AbstractList<Long> implements RandomAccess {

        long[] array;

        private LongList(long[] array) {
            this.array = array;
        }

        public Long get(int index) {
            return array[index];
        }

        public Long set(int index, Long element) {
            long result = array[index];
            array[index] = element;
            return result;
        }

        public int size() {
            return array.length;
        }
    }

    public static long[] partialSums(int[] array) {
        long[] result = new long[array.length + 1];
        for (int i = 0; i < array.length; i++)
            result[i + 1] = result[i] + array[i];
        return result;
    }

    public static int[] unique(int[] array) {
        return unique(array, 0, array.length);
    }

    public static int[] unique(int[] array, int from, int to) {
        if (from == to)
            return new int[0];
        int count = 1;
        for (int i = from + 1; i < to; i++) {
            if (array[i] != array[i - 1])
                count++;
        }
        int[] result = new int[count];
        result[0] = array[from];
        int index = 1;
        for (int i = from + 1; i < to; i++) {
            if (array[i] != array[i - 1])
                result[index++] = array[i];
        }
        return result;
    }

    public static void orderBy(int[] base, int[]... arrays) {
        int[] order = ArrayUtils.order(base);
        order(order, base);
        for (int[] array : arrays)
            order(order, array);
    }

    public static void order(int[] order, int[] array) {
        ensureCapacityInt(order.length);
        for (int i = 0; i < order.length; i++)
            tempInt[i] = array[order[i]];
        System.arraycopy(tempInt, 0, array, 0, array.length);
    }

    public static int[] reversePermutation(int[] permutation) {
        int[] result = new int[permutation.length];
        for (int i = 0; i < permutation.length; i++)
            result[permutation[i]] = i;
        return result;
    }

    //update 24/8/2013


    public static void fill(double[][] array, double value) {
        for (double[] row : array)
            Arrays.fill(row, value);
    }

    public static void fill(double[][][] array, double value) {
        for (double[][] row : array)
            fill(row, value);
    }

    public static void fill(double[][][][] array, double value) {
        for (double[][][] row : array)
            fill(row, value);
    }

    public static void compress(int[]...arrays) {
        int totalLength = 0;
        for (int[] array : arrays)
            totalLength += array.length;
        int[] all = new int[totalLength];
        int delta = 0;
        for (int[] array : arrays) {
            System.arraycopy(array, 0, all, delta, array.length);
            delta += array.length;
        }
        sort(all, IntComparator.DEFAULT);
        all = unique(all);
        Map<Integer, Integer> map = new EHashMap<Integer, Integer>();
        for (int i = 0; i < all.length; i++)
            map.put(all[i], i);
        for (int[] array : arrays) {
            for (int i = 0; i < array.length; i++)
                array[i] = map.get(array[i]);
        }
    }

    public static boolean nextPermutation(int[] array) {
        int count = array.length;
        for (int i = count - 2; i >= 0; --i) {
            if (array[i] < array[i + 1]) {
                for (int j = count - 1; ; --j) {
                    if (array[j] > array[i]) {
                        int temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                        for (++i, j = count - 1; i < j; ++i, --j) {
                            temp = array[i];
                            array[i] = array[j];
                            array[j] = temp;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int maxElement(int[] array) {
        return maxElement(array, 0, array.length);
    }

    public static int maxElement(int[] array, int from, int to) {
        int result = Integer.MIN_VALUE;
        for (int i = from; i < to; i++)
            result = Math.max(result, array[i]);
        return result;
    }

    public static int minElement(int[] array) {
        return minElement(array, 0, array.length);
    }

    public static int minElement(int[] array, int from, int to) {
        int result = Integer.MAX_VALUE;
        for (int i = from; i < to; i++)
            result = Math.min(result, array[i]);
        return result;
    }

    public static long maxElement(long[] array) {
        return maxElement(array, 0, array.length);
    }

    public static long maxElement(long[] array, int from, int to) {
        long result = Long.MIN_VALUE;
        for (int i = from; i < to; i++)
            result = Math.max(result, array[i]);
        return result;
    }

    public static long minElement(long[] array) {
        return minElement(array, 0, array.length);
    }

    public static long minElement(long[] array, int from, int to) {
        long result = Long.MAX_VALUE;
        for (int i = from; i < to; i++)
            result = Math.min(result, array[i]);
        return result;
    }

    public static int count(int[] array, int value) {
        int result = 0;
        for (int i : array) {
            if (i == value)
                result++;
        }
        return result;
    }

    public static int count(char[] array, char value) {
        int result = 0;
        for (char i : array) {
            if (i == value)
                result++;
        }
        return result;
    }

    public static int find(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value)
                return i;
        }
        return -1;
    }


    /* Searching */
    public static int searchLower(int[] array, int value) {
        return searchLower(array, value, 0, array.length - 1);
    }

    public static int searchLower(int[] array, int value, int from, int to) {
        if (array.length == 0)
            return -1;

        int left = from;
        int right = to;

        while (right - left > 1) {
            int mid = (right + left) >> 1;
            if (value <= array[mid])
                right = mid;
            else
                left = mid;
        }

        if (array[right] < value)
            return right;
        else if (array[left] < value)
            return left;
        else
            return -1;
    }

    public static int searchLower(long[] array, long value) {
        return searchLower(array, value, 0, array.length - 1);
    }

    public static int searchLower(long[] array, long value, int from, int to) {
        if (array.length == 0)
            return -1;

        int left = from;
        int right = to;

        while (right - left > 1) {
            int mid = (right + left) >> 1;
            if (value <= array[mid])
                right = mid;
            else
                left = mid;
        }

        if (array[right] < value)
            return right;
        else if (array[left] < value)
            return left;
        else
            return -1;
    }

    public static int searchLower(double[] array, double value) {
        return searchLower(array, value, 0, array.length - 1);
    }

    public static int searchLower(double[] array, double value, int from, int to) {
        if (array.length == 0)
            return -1;

        int left = from;
        int right = to;

        while (right - left > 1) {
            int mid = (right + left) >> 1;
            if (array[mid] - value > -epsilon)
                right = mid;
            else
                left = mid;
        }

        if (value - array[right] > epsilon)
            return right;
        else if (value - array[left] > epsilon)
            return left;
        else
            return -1;
    }

    public static int searchLowerAndEqual(int[] array, int value) {
        return searchLowerAndEqual(array, value, 0, array.length - 1);
    }

    public static int searchLowerAndEqual(int[] array, int value, int from, int to) {
        if (array.length == 0)
            return -1;

        int left = from;
        int right = to;

        while (right - left > 1) {
            int mid = (right + left) >> 1;
            if (value < array[mid])
                right = mid;
            else
                left = mid;
        }

        if (array[right] <= value)
            return right;
        else if (array[left] <= value)
            return left;
        else
            return -1;
    }

    public static int searchLowerAndEqual(long[] array, long value) {
        return searchLowerAndEqual(array, value, 0, array.length - 1);
    }

    public static int searchLowerAndEqual(long[] array, long value, int from, int to) {
        if (array.length == 0)
            return -1;

        int left = from;
        int right = to;

        while (right - left > 1) {
            int mid = (right + left) >> 1;
            if (value < array[mid])
                right = mid;
            else
                left = mid;
        }

        if (array[right] <= value)
            return right;
        else if (array[left] <= value)
            return left;
        else
            return -1;
    }

    public static int searchLowerAndEqual(double[] array, double value) {
        return searchLowerAndEqual(array, value, 0, array.length - 1);
    }

    public static int searchLowerAndEqual(double[] array, double value, int from, int to) {
        if (array.length == 0)
            return -1;

        int left = from;
        int right = to;

        while (right - left > 1) {
            int mid = (right + left) >> 1;
            if (array[mid] - value > epsilon)
                right = mid;
            else
                left = mid;
        }

        if (value - array[right] > -epsilon)
            return right;
        else if (value - array[left] > -epsilon)
            return left;
        else
            return -1;
    }

    public static int searchGreater(int[] array, int value) {
        return searchGreater(array, value, 0, array.length - 1);
    }

    public static int searchGreater(int[] array, int value, int from, int to) {
        if (array.length == 0)
            return -1;

        int left = from;
        int right = to;

        while (right - left > 1) {
            int mid = (right + left) >> 1;
            if (value < array[mid])
                right = mid;
            else
                left = mid;
        }

        if (array[left] > value)
            return left;
        else if (array[right] > value)
            return right;
        else
            return -1;
    }

    public static int searchGreater(long[] array, long value) {
        return searchGreater(array, value, 0, array.length - 1);
    }

    public static int searchGreater(long[] array, long value, int from, int to) {
        if (array.length == 0)
            return -1;

        int left = from;
        int right = to;

        while (right - left > 1) {
            int mid = (right + left) >> 1;
            if (value < array[mid])
                right = mid;
            else
                left = mid;
        }

        if (array[left] > value)
            return left;
        else if (array[right] > value)
            return right;
        else
            return -1;
    }

    public static int searchGreater(double[] array, double value) {
        return searchGreater(array, value, 0, array.length - 1);
    }

    public static int searchGreater(double[] array, double value, int from, int to) {
        if (array.length == 0)
            return -1;

        int left = from;
        int right = to;

        while (right - left > 1) {
            int mid = (right + left) >> 1;
            if (array[mid] - value > epsilon)
                right = mid;
            else
                left = mid;
        }

        if (array[left] - value > epsilon)
            return left;
        else if (array[right] - value > epsilon)
            return right;
        else
            return -1;
    }

    public static int searchGreaterAndEqual(int[] array, int value) {
        return searchGreaterAndEqual(array, value, 0, array.length - 1);
    }

    public static int searchGreaterAndEqual(int[] array, int value, int from, int to) {
        if (array.length == 0)
            return -1;

        int left = from;
        int right = to;

        while (right - left > 1) {
            int mid = (right + left) >> 1;
            if (value <= array[mid])
                right = mid;
            else
                left = mid;
        }

        if (array[left] >= value)
            return left;
        else if (array[right] >= value)
            return right;
        else
            return -1;
    }

    public static int searchGreaterAndEqual(long[] array, long value) {
        return searchGreaterAndEqual(array, value, 0, array.length - 1);
    }

    public static int searchGreaterAndEqual(long[] array, long value, int from, int to) {
        if (array.length == 0)
            return -1;

        int left = from;
        int right = to;

        while (right - left > 1) {
            int mid = (right + left) >> 1;
            if (value <= array[mid])
                right = mid;
            else
                left = mid;
        }

        if (array[left] >= value)
            return left;
        else if (array[right] >= value)
            return right;
        else
            return -1;
    }

    public static int searchGreaterAndEqual(double[] array, double value) {
        return searchGreaterAndEqual(array, value, 0, array.length - 1);
    }

    public static int searchGreaterAndEqual(double[] array, double value, int from, int to) {
        if (array.length == 0)
            return -1;

        int left = from;
        int right = to;

        while (right - left > 1) {
            int mid = (right + left) >> 1;
            if (array[mid] - value > -epsilon)
                right = mid;
            else
                left = mid;
        }

        if (array[left] - value > -epsilon)
            return left;
        else if (array[right] - value > -epsilon)
            return right;
        else
            return -1;
    }
}