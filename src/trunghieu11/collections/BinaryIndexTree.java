package trunghieu11.collections;

import java.util.Arrays;

/**
 * Created by OnePiece on 11/20/2014.
 */
public abstract class BinaryIndexTree {
    protected int size;
    protected long[] bit;
    protected long initValue;

    // index 0 ... n - 1
    public BinaryIndexTree(int size) {
        this(size, 0);
    }

    public BinaryIndexTree(int size, long initValue) {
        this.size = size;
        this.bit = new long[this.size];
        this.initValue = initValue;
        Arrays.fill(this.bit, this.initValue);
    }

    // index 0 ... n - 1
    public long get(int x) {
        long res = this.initValue;
        for (; x > 0; x -= x & -x)
            res = joinValue(res, bit[x]);
        return res;
    }

    // index 0 ... n - 1
    public void update(int x, long v) {
        for (; x < bit.length; x += x & -x)
            bit[x] = joinValue(bit[x], v);
    }

    protected abstract long joinValue(long was, long delta);

    public void clear() {
        Arrays.fill(bit, 0);
    }
}
