package myTool.DataStructureTool;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 7/30/13
 * Time: 5:16 PM
 * To change this template use File | Settings | File Templates.
 */
abstract public class ArrayWrapper<T> implements Iterable<T> {
    protected final int from;
    protected final int to;
    protected final int size;
    protected final Object underlying;

    public static ArrayWrapper<Integer> wrap(int[] array) {
        return new IntArrayWrapper(array);
    }

    protected ArrayWrapper(Object underlying, int from, int to) {
        this.underlying = underlying;
        this.from = from;
        this.to = to;
        size = to - from;
    }


    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    public abstract T get(int index);

    private class ArrayIterator implements Iterator<T> {
        private int index = 0;

        protected ArrayIterator() {
        }

        public boolean hasNext() {
            return index < size;
        }

        public T next() {
            return get(index++);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    protected static class IntArrayWrapper extends ArrayWrapper<Integer> {
        protected final int[] array;

        protected IntArrayWrapper(int[] array) {
            this(array, 0, array.length);
        }

        protected IntArrayWrapper(int[] array, int from, int to) {
            super(array, from, to);
            this.array = array;
        }

        public Integer get(int index) {
            return array[index + from];
        }

    }
}
