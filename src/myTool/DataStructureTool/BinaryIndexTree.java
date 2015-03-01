package myTool.DataStructureTool;

import java.util.Arrays;

/**
 * Created by OnePiece on 11/20/2014.
 */
public abstract class BinaryIndexTree {
    protected int size;
    protected long[] bit;

    // remember add 1 to size
    public BinaryIndexTree(int size) {
        this.size = size;
        this.bit = new long[this.size];
    }

    // remember add 1 to x
    public long get(int x) {
        long res = 0;
        for (; x > 0; x -= x & -x)
            res = joinValue(res, bit[x]);
        return res;
    }

    // remember add 1 to x
    public void update(int x, long v) {
        for (; x < bit.length; x += x & -x)
            bit[x] = joinValue(bit[x], v);
    }

    protected abstract long joinValue(long was, long delta);

    public void clear() {
        Arrays.fill(bit, 0);
    }
}
