package myTool.DataStructureTool;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/3/12
 * Time: 1:08 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class LongIntervalTree extends IntervalTree {
    protected long[] value;
    protected long[] delta;

    protected LongIntervalTree(int size) {
        this(size, true);
    }

    public LongIntervalTree(int size, boolean shouldInit) {
        super(size, shouldInit);
    }

    protected void initData(int size, int nodeCount) {
        value = new long[nodeCount];
        delta = new long[nodeCount];
    }

    protected abstract long joinValue(long left, long right);
    protected abstract long joinDelta(long was, long delta);
    protected abstract long accumulate(long value, long delta, int length);
    protected abstract long neutralValue();
    protected abstract long neutralDelta();

    protected long initValue(int index) {
        return neutralValue();
    }

    protected void initAfter(int root, int left, int right, int middle) {
        value[root] = joinValue(value[2 * root + 1], value[2 * root + 2]);
        delta[root] = neutralDelta();
    }

    protected void initBefore(int root, int left, int right, int middle) {
    }

    protected void initLeaf(int root, int index) {
        value[root] = initValue(index);
        delta[root] = neutralDelta();
    }

    protected void updatePostProcess(int root, int left, int right, int from, int to, long delta, int middle) {
        value[root] = joinValue(value[2 * root + 1], value[2 * root + 2]);
    }

    protected void updatePreProcess(int root, int left, int right, int from, int to, long delta, int middle) {
        pushDown(root, left, middle, right);
    }

    protected void pushDown(int root, int left, int middle, int right) {
        value[2 * root + 1] = accumulate(value[2 * root + 1], delta[root], middle - left + 1);
        value[2 * root + 2] = accumulate(value[2 * root + 2], delta[root], right - middle);
        delta[2 * root + 1] = joinDelta(delta[2 * root + 1], delta[root]);
        delta[2 * root + 2] = joinDelta(delta[2 * root + 2], delta[root]);
        delta[root] = neutralDelta();
    }

    protected void updateFull(int root, int left, int right, int from, int to, long delta) {
        value[root] = accumulate(value[root], delta, right - left + 1);
        this.delta[root] = joinDelta(this.delta[root], delta);
    }

    protected long queryPostProcess(int root, int left, int right, int from, int to, int middle, long leftResult, long rightResult) {
        return joinValue(leftResult, rightResult);
    }

    protected void queryPreProcess(int root, int left, int right, int from, int to, int middle) {
        pushDown(root, left, middle, right);
    }

    protected long queryFull(int root, int left, int right, int from, int to) {
        return value[root];
    }

    protected long emptySegmentResult() {
        return neutralValue();
    }
}