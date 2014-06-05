package myTool.DataStructureTool;

import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 2/3/13
 * Time: 11:48 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IntIterator {
    public int value() throws NoSuchElementException;
    /*
     * @throws NoSuchElementException only if iterator already invalid
     */
    public void advance() throws NoSuchElementException;
    public boolean isValid();
}