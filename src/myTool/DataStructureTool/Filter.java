package myTool.DataStructureTool;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/3/12
 * Time: 7:05 PM
 * To change this template use File | Settings | File Templates.
 */
interface Filter<T> {
    public boolean accept(T value);
}