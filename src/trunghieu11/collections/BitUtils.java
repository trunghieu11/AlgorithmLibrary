package trunghieu11.collections;

/**
 * Created by OnePiece on 12/13/2014.
 */
public class BitUtils {
    public static int countBit(long value) {
        return (value == 0) ? 0 : (1 + countBit(value & (value - 1)));
    }

    public static int lowBit(int value) {
        return (value ^ (value - 1)) & value;
    }

    public static boolean isContain(long mask, int bit) {
        return (mask & ((long)1 << bit)) != 0;
    }
}
