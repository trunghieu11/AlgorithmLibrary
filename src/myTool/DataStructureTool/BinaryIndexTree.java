package myTool.DataStructureTool;

/**
 * Created by OnePiece on 11/20/2014.
 */
public abstract class BinaryIndexTree {
    protected int size;
    protected long[] bit;

    public BinaryIndexTree(int size) {
        this.size = size;
        this.bit = new long[this.size];
    }

    public long get(int x) {
        long res = 0;
        for (; x > 0; x -= x & -x)
            res = joinValue(res, bit[x]);
        return res;
    }

    public void update(int x, int v) {
        for (; x < bit.length; x += x & -x)
            bit[x] = joinValue(bit[x], v);
    }

    protected abstract long joinValue(long was, long delta);
}
