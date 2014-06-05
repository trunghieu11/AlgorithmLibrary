package myTool.StandardTool;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/3/12
 * Time: 7:39 PM
 * To change this template use File | Settings | File Templates.
 */
interface PowerOperation<V> extends Operation<V> {
    public V power(V base, int exponent);
}
