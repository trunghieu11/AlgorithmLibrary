package myTool.StandardTool;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/3/12
 * Time: 7:20 PM
 * To change this template use File | Settings | File Templates.
 */
interface Sequence<T> extends Iterable<T> {
    public Sequence<T> subSequence(int from);
    public Sequence<T> subSequence(int from, int to);
    public int size();
    public boolean isEmpty();
    public T get(int index);
}
