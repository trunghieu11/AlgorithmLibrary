package myTool.DataStructureTool;

/**
 * Created by OnePiece on 11/20/2014.
 */
public class BinaryIndexTree {
    protected int size;
    protected long[] bit;

    public BinaryIndexTree(int size) {
        this.size = size + 1;
        this.bit = new long[this.size];
    }

    public long get(int x) {
        x++;
        long res = 0;
        for (; x > 0; x -= x & -x)
            res += bit[x];
        return res;
    }

    public void update(int x, int v) {
        x++;
        for (; x < bit.length; x += x & -x)
            bit[x] += v;
    }
}
