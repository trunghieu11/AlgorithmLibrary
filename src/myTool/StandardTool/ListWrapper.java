package myTool.StandardTool;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/3/12
 * Time: 7:25 PM
 * To change this template use File | Settings | File Templates.
 */
class ListWrapper<T> extends AbstractWritableSequence<T> {
    private final List<T> list;

    public static<T> WritableSequence<T> wrap(List<T> list) {
        return new ListWrapper<T>(list);
    }

    private ListWrapper(List<T> list) {
        this.list = list;
    }

    public void set(int index, T value) {
        list.set(index, value);
    }

    public int size() {
        return list.size();
    }

    public T get(int index) {
        return list.get(index);
    }
}
