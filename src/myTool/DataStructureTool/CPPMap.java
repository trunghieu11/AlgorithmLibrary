package myTool.DataStructureTool;

import myTool.StandardTool.Factory;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/3/12
 * Time: 12:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class CPPMap<K, V> extends HashMap<K, V> {
    private final Factory<V> defaultValueFactory;

    public CPPMap(Factory<V> defaultValueFactory) {
        this.defaultValueFactory = defaultValueFactory;
    }

    public V get(Object key) {
        if (containsKey(key))
            return super.get(key);
        V value = defaultValueFactory.create();
        try {
            //noinspection unchecked
            super.put((K) key, value);
            return value;
        } catch (ClassCastException e) {
            return value;
        }
    }
}
