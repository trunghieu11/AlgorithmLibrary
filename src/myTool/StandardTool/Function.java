package myTool.StandardTool;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/3/12
 * Time: 7:37 PM
 * To change this template use File | Settings | File Templates.
 */
interface Function<A, V> {
    public abstract V value(A argument);
}
