package myTool.StandardTool;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/3/12
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
abstract class ReadOnlyIterator<T> implements Iterator<T> {
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
