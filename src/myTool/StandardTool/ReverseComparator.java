package myTool.StandardTool;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/2/12
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReverseComparator<T extends Comparable<T>> implements Comparator<T> {
    public int compare(T o1, T o2) {
        return o2.compareTo(o1);
    }
}
