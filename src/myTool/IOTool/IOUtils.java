package myTool.IOTool;

import myTool.StandardTool.Pair;

import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 9/29/12
 * Time: 11:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class IOUtils {
    public static void printArray(int[] array, OutputWriter out) {
        if (array.length == 0) {
            out.printLine();
            return;
        }
        out.print(array[0]);
        for (int i = 1; i < array.length; i++)
            out.print(" " + array[i]);
        out.printLine();
    }

    public static<T> void printCollection(Iterable<T> collection, OutputWriter out) {
        printCollection(collection, out, " ");
    }

    public static<T> void printCollection(Iterable<T> collection, OutputWriter out, String delimiter) {
        boolean isFirst = true;
        for (T element : collection) {
            if (isFirst)
                isFirst = false;
            else
                out.print(delimiter);
            out.print(element);
        }
        out.printLine();
    }

    public static<T> void printCollection(Iterable<T> collection, PrintWriter out, String delimiter) {
        boolean isFirst = true;
        for (T element : collection) {
            if (isFirst)
                isFirst = false;
            else
                out.print(delimiter);
            out.print(element);
        }
        out.println();
    }

    public static Pair<Integer, Integer> readIntPair(InputReader in) {
        int first = in.readInt();
        int second = in.readInt();
        return Pair.makePair(first, second);
    }

    public static Pair<Long, Long> readLongPair(InputReader in) {
        long first = in.readLong();
        long second = in.readLong();
        return Pair.makePair(first, second);
    }

    public static Pair<Integer, Integer>[] readIntPairArray(InputReader in, int size) {
        @SuppressWarnings({"unchecked"})
        Pair<Integer, Integer>[] result = new Pair[size];
        for (int i = 0; i < size; i++)
            result[i] = readIntPair(in);
        return result;
    }

    public static Pair<Long, Long>[] readLongPairArray(InputReader in, int size) {
        @SuppressWarnings({"unchecked"})
        Pair<Long, Long>[] result = new Pair[size];
        for (int i = 0; i < size; i++)
            result[i] = readLongPair(in);
        return result;
    }

    public static void readDoubleArrays(InputReader in, double[]...arrays) {
        for (int i = 0; i < arrays[0].length; i++) {
            for (int j = 0; j < arrays.length; j++)
                arrays[j][i] = in.readDouble();
        }
    }

    public static int[][] readIntTable(InputReader in, int rowCount, int columnCount) {
        int[][] table = new int[rowCount][];
        for (int i = 0; i < rowCount; i++)
            table[i] = readIntArray(in, columnCount);
        return table;
    }

    public static long[][] readLongTable(InputReader in, int rowCount, int columnCount) {
        long[][] table = new long[rowCount][];
        for (int i = 0; i < rowCount; i++)
            table[i] = readLongArray(in, columnCount);
        return table;
    }

    public static String[][] readStringTable(InputReader in, int rowCount, int columnCount) {
        String[][] table = new String[rowCount][];
        for (int i = 0; i < rowCount; i++)
            table[i] = readStringArray(in, columnCount);
        return table;
    }

    public static String readText(InputReader in) {
        StringBuilder result = new StringBuilder();
        while (true) {
            int character = in.read();
            if (character == '\r')
                continue;
            if (character == -1)
                break;
            result.append((char) character);
        }
        return result.toString();
    }

    public static int[] readIntArray(InputReader in, int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = in.readInt();
        return array;
    }

    public static void readIntArrays(InputReader in, int[]... arrays) {
        for (int i = 0; i < arrays[0].length; i++) {
            for (int j = 0; j < arrays.length; j++)
                arrays[j][i] = in.readInt();
        }
    }

    public static char[] readCharArray(InputReader in, int size) {
        char[] array = new char[size];
        for (int i = 0; i < size; i++)
            array[i] = in.readCharacter();
        return array;
    }

    public static char[][] readTable(InputReader in, int rowCount, int columnCount) {
        char[][] table = new char[rowCount][];
        for (int i = 0; i < rowCount; i++)
            table[i] = readCharArray(in, columnCount);
        return table;
    }

    public static void readLongArrays(InputReader in, long[]... arrays) {
        for (int i = 0; i < arrays[0].length; i++) {
            for (int j = 0; j < arrays.length; j++)
                arrays[j][i] = in.readLong();
        }
    }

    public static String[] readStringArray(InputReader in, int size) {
        String[] array = new String[size];
        for (int i = 0; i < size; i++)
            array[i] = in.readString();
        return array;
    }

    public static void readStringArrays(InputReader in, String[]... arrays) {
        for (int i = 0; i < arrays[0].length; i++) {
            for (int j = 0; j < arrays.length; j++)
                arrays[j][i] = in.readString();
        }
    }

    public static String[] readLineArray(InputReader in, int size) {
        String[] array = new String[size];
        for (int i = 0; i < size; i++)
            array[i] = in.readLine();
        return array;
    }

    public static long[] readLongArray(InputReader in, int size) {
        long[] array = new long[size];
        for (int i = 0; i < size; i++)
            array[i] = in.readLong();
        return array;
    }

    public static double[] readDoubleArray(InputReader in, int size) {
        double[] array = new double[size];
        for (int i = 0; i < size; i++)
            array[i] = in.readDouble();
        return array;
    }
}
