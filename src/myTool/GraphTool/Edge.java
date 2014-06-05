package myTool.GraphTool;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 8/22/13
 * Time: 9:05 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Edge {
    public int getSource();
    public int getDestination();
    public long getWeight();
    public long getCapacity();
    public Edge getTransposedEdge();
    public Edge getReverseEdge();
    public void pushFlow(long flow);
}
