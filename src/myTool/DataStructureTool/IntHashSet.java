package myTool.DataStructureTool;

import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 7/29/13
 * Time: 10:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class IntHashSet extends IntSet {
    private static final Random RND = new Random();
    private static final int[] SHIFTS = new int[4];
    private static final byte PRESENT_MASK = 1;

    static {
        for (int i = 0; i < 4; i++)
            SHIFTS[i] = RND.nextInt(31) + 1;
    }

    private int size;
    private int[] values;
    private byte[] present;
    private int step;
    private int ratio;

    public IntHashSet() {
        this(3);
    }


    public IntHashSet(int capacity) {
        capacity = Math.max(capacity, 1);
        values = new int[capacity];
        present = new byte[capacity];
        ratio = capacity;
        initStep(capacity);
    }

    private void initStep(int capacity) {
        step = RND.nextInt(capacity - 2) + 1;
        while (IntegerUtils.gcd(step, capacity) != 1)
            step++;
    }

    public IntIterator iterator() {
        return new IntIterator() {
            private int position = size == 0 ? values.length : -1;

            public int value() throws NoSuchElementException {
                if (position == -1)
                    advance();
                if (position >= values.length)
                    throw new NoSuchElementException();
                return values[position];
            }

            public void advance() throws NoSuchElementException {
                if (position >= values.length)
                    throw new NoSuchElementException();
                position++;
                while (position < values.length && (present[position] & PRESENT_MASK) == 0)
                    position++;
            }

            public boolean isValid() {
                return position < values.length;
            }
        };
    }

    public int size() {
        return size;
    }

    public void add(int value) {
        ensureCapacity((size + 1) * ratio);
        int current = getHash(value);
        while ((present[current] & PRESENT_MASK) != 0) {
            if (values[current] == value)
                return;
            current += step;
            if (current >= values.length)
                current -= values.length;
        }
        present[current] = PRESENT_MASK;
        values[current] = value;
        size++;
    }

    private int getHash(int value) {
        int result = value;
        for (int i : SHIFTS)
            result ^= value >> i;
        result %= values.length;
        if (result < 0)
            result += values.length;
        return result;
    }

    private void ensureCapacity(int capacity) {
        if (values.length < capacity) {
            capacity = Math.max(capacity, 2 * values.length);
            rebuild(capacity);
        }
    }

    private void rebuild(int capacity) {
        initStep(capacity);
        int[] oldValues = values;
        byte[] oldPresent = present;
        values = new int[capacity];
        present = new byte[capacity];
        size = 0;
        for (int i = 0; i < oldValues.length; i++) {
            if ((oldPresent[i] & PRESENT_MASK) == PRESENT_MASK)
                add(oldValues[i]);
        }
    }

    public boolean contains(int value) {
        int current = getHash(value);
        while (present[current] != 0) {
            if (values[current] == value && (present[current] & PRESENT_MASK) != 0)
                return true;
            current += step;
            if (current >= values.length)
                current -= values.length;
        }
        return false;
    }
}
