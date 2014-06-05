package myTool.DataStructureTool;

import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 2/3/13
 * Time: 11:46 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class IntCollection {
    public abstract IntIterator iterator();
    public abstract int size();
    public abstract void add(int value);

    public int min() {
        if (size() == 0)
            throw new NoSuchElementException();
        int result = Integer.MAX_VALUE;
        for (IntIterator iterator = iterator(); iterator.isValid(); iterator.advance())
            result = Math.min(result, iterator.value());
        return result;
    }

    public int max() {
        if (size() == 0)
            throw new NoSuchElementException();
        int result = Integer.MIN_VALUE;
        for (IntIterator iterator = iterator(); iterator.isValid(); iterator.advance())
            result = Math.max(result, iterator.value());
        return result;
    }

    public int[] toArray() {
        int size = size();
        int[] array = new int[size];
        int i = 0;
        for (IntIterator iterator = iterator(); iterator.isValid(); iterator.advance())
            array[i++] = iterator.value();
        return array;
    }

    public void addAll(IntCollection values) {
        for (IntIterator it = values.iterator(); it.isValid(); it.advance()) {
            add(it.value());
        }
    }

    public boolean contains(int value) {
        for (IntIterator iterator = iterator(); iterator.isValid(); iterator.advance()) {
            if (iterator.value() == value)
                return true;
        }
        return false;
    }
}
