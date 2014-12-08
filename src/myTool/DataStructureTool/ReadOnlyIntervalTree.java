package myTool.DataStructureTool;

/**
 * Created by OnePiece on 12/7/2014.
 */
public abstract class ReadOnlyIntervalTree extends IntervalTree {
    protected long[] value;
    protected long[] array;

    protected ReadOnlyIntervalTree(long[] array) {
        super(array.length, false);
        this.array = array;
        init();
    }

    protected void initData(int size, int nodeCount) {
        value = new long[nodeCount];
    }

    protected void initAfter(int root, int left, int right, int middle) {
        value[root] = joinValue(value[2 * root + 1], value[2 * root + 2]);
    }

    protected void initBefore(int root, int left, int right, int middle) {
    }

    protected void initLeaf(int root, int index) {
        value[root] = array[index];
    }

    protected long queryPostProcess(int root, int left, int right, int from, int to, int middle, long leftResult, long rightResult) {
        return joinValue(leftResult, rightResult);
    }

    protected void queryPreProcess(int root, int left, int right, int from, int to, int middle) {
    }

    protected long queryFull(int root, int left, int right, int from, int to) {
        return value[root];
    }

    protected long emptySegmentResult() {
        return neutralValue();
    }

    protected abstract long neutralValue();
    protected abstract long joinValue(long left, long right);
}
