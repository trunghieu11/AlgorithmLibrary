package myTool.DataStructureTool;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 9/30/12
 * Time: 9:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IndependentSetSystem {
    public boolean join(int first, int second);

    public int getSetCount();

    int get(int index);

    void setListener(Listener listener);

    public static interface Listener {
        public void joined(int joinedRoot, int root);
    }
}
