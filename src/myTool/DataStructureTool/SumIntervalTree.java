package myTool.DataStructureTool;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 4/12/13
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class SumIntervalTree extends LongIntervalTree {
    public SumIntervalTree(int size) {
        super(size);
    }

    protected long joinValue(long left, long right) {
        return left + right;
    }

    protected long joinDelta(long was, long delta) {
        return was + delta;
    }

    protected long accumulate(long value, long delta, int length) {
        return value + delta * length;
    }

    protected long neutralValue() {
        return 0;
    }

    protected long neutralDelta() {
        return 0;
    }
}
