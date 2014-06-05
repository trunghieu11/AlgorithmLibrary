package myTool.StandardTool;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/3/12
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
abstract class AbstractWritableSequence<T> extends AbstractSequence<T> implements WritableSequence<T> {
    @Override
    public WritableSequence<T> subSequence(int from) {
        return subSequence(from, size());
    }

    @Override
    public WritableSequence<T> subSequence(int from, int to) {
        int size = size();
        if (from < 0)
            from += size;
        if (to < 0)
            to += size;
        checkIndices(from, to, size);
        return new WritableSubSequence(from, to);
    }

    protected class WritableSubSequence extends SubSequence implements WritableSequence<T>{
        public WritableSubSequence(int from, int to) {
            super(from, to);
        }

        public void set(int index, T value) {
            if (index < 0 || index >= size())
                throw new IndexOutOfBoundsException();
            AbstractWritableSequence.this.set(from + index, value);
        }

        public WritableSequence<T> subSequence(int from) {
            return subSequence(from, to);
        }

        public WritableSequence<T> subSequence(int from, int to) {
            int size = size();
            if (from < 0)
                from += size;
            if (to < 0)
                to += size;
            checkIndices(from, to, size);
            return new WritableSubSequence(from + this.from, to + this.from);
        }
    }
}
