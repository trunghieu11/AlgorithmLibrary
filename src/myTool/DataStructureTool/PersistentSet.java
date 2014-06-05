package myTool.DataStructureTool;

import java.util.NavigableSet;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/3/12
 * Time: 7:27 PM
 * To change this template use File | Settings | File Templates.
 */
interface PersistentSet<K> extends NavigableSet<K> {
    public void markState(Object marker);
    public PersistentSet<K> getState(Object marker);
}
