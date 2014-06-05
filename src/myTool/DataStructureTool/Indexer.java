package myTool.DataStructureTool;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/3/12
 * Time: 1:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class Indexer<K> extends HashMap<K, Integer> {
    private int index = 0;

    public Integer get(Object key) {
        if (!containsKey(key))
            put((K) key, index++);
        return super.get(key);
    }
}
