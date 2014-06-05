package myTool.DataStructureTool;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 5/13/13
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class EHashSet<E> extends AbstractSet<E> {
    private static final Object VALUE = new Object();
    private final Map<E, Object> map;

    public EHashSet() {
        this(4);
    }

    public EHashSet(int maxSize) {
        map = new EHashMap<E, Object>(maxSize);
    }

    public EHashSet(Collection<E> collection) {
        this(collection.size());
        addAll(collection);
    }

    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    public boolean add(E e) {
        if (e == null)
            return false;
        return map.put(e, VALUE) == null;
    }

    public boolean remove(Object o) {
        if (o == null)
            return false;
        return map.remove(o) != null;
    }

    public void clear() {
        map.clear();
    }

    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    public int size() {
        return map.size();
    }
}
