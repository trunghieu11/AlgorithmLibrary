package myTool.DataStructureTool;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/3/12
 * Time: 12:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Counter<K> extends HashMap<K, Long> {
    public Counter() {
        super();
    }

    public void add(K key) {
        put(key, get(key) + 1);
    }

    public void add(K key, long delta) {
        put(key, get(key) + delta);
    }

    public Long get(Object key) {
        if (containsKey(key))
            return super.get(key);
        return 0L;
    }
}
