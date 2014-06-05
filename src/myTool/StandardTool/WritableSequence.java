package myTool.StandardTool;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/3/12
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
public interface WritableSequence<T> extends Sequence<T> {
    public void set(int index, T value);
    public WritableSequence<T> subSequence(int from);
    public WritableSequence<T> subSequence(int from, int to);
}
