package myTool.StandardTool;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/3/12
 * Time: 7:38 PM
 * To change this template use File | Settings | File Templates.
 */
interface Operation<V> extends Factory<V> {
    public V operation(V first, V second);
}
