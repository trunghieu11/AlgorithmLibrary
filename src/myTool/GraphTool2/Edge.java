package myTool.GraphTool2;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 8/22/13
 * Time: 9:24 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Edge<V> {
    public V getSource();
    public V getDestination();
    public long getWeight();
    public Edge<V> getTransposedEdge();
    public Edge<V> getReverseEdge();
    public void pushFlow(long flow);
}
