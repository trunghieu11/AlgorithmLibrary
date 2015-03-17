package trunghieu11.graph2;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 8/22/13
 * Time: 9:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class BidirectionalGraph<V> extends Graph<V> {
    public void add(Edge<V> edge) {
        super.add(edge);
        super.add(edge.getTransposedEdge());
    }

    public void remove(Edge<V> edge) {
        super.remove(edge);
        super.remove(edge.getTransposedEdge());
    }
}
