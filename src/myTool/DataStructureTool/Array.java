package myTool.DataStructureTool;

import java.util.AbstractList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/1/12
 * Time: 5:46 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Array<T> extends AbstractList<T> {
    public static<T> List<T> wrap(T...array) {
        return new ReferenceArray<T>(array);
    }

    public static List<Integer> wrap(int...array) {
        return new IntArray(array);
    }

    public static List<Long> wrap(long...array) {
        return new LongArray(array);
    }

    protected static class IntArray extends Array<Integer> {
        protected final int[] array;

        protected IntArray(int[] array) {
            this.array = array;
        }

        public int size() {
            return array.length;
        }

        public Integer get(int index) {
            return array[index];
        }

        public Integer set(int index, Integer value) {
            int result = array[index];
            array[index] = value;
            return result;
        }
    }

    protected static class ReferenceArray<T> extends Array<T> {
        protected final T[] array;

        protected ReferenceArray(T[] array) {
            this.array = array;
        }

        public int size() {
            return array.length;
        }

        public T get(int index) {
            return array[index];
        }

        public T set(int index, T value) {
            T result = array[index];
            array[index] = value;
            return result;
        }
    }

    protected static class LongArray extends Array<Long> {
        protected final long[] array;

        protected LongArray(long[] array) {
            this.array = array;
        }

        public int size() {
            return array.length;
        }

        public Long get(int index) {
            return array[index];
        }

        public Long set(int index, Long value) {
            long result = array[index];
            array[index] = value;
            return result;
        }
    }

    public static List<Double> wrap(double...array) {
        return new DoubleArray(array);
    }

    public static List<Boolean> wrap(boolean...array) {
        return new BooleanArray(array);
    }

    protected static class BooleanArray extends Array<Boolean> {
        protected final boolean[] array;

        protected BooleanArray(boolean[] array) {
            this.array = array;
        }

        public int size() {
            return array.length;
        }

        public Boolean get(int index) {
            return array[index];
        }

        public Boolean set(int index, Boolean value) {
            boolean result = array[index];
            array[index] = value;
            return result;
        }
    }

    protected static class DoubleArray extends Array<Double> {
        protected final double[] array;

        protected DoubleArray(double[] array) {
            this.array = array;
        }

        public int size() {
            return array.length;
        }

        public Double get(int index) {
            return array[index];
        }

        public Double set(int index, Double value) {
            double result = array[index];
            array[index] = value;
            return result;
        }
    }
}
