package myTool.GraphTool;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 8/22/13
 * Time: 9:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class BidirectionalGraph extends Graph {
    public BidirectionalGraph(int size) {
        super(size);
    }

    public void add(Edge edge) {
        super.add(edge);
        super.add(edge.getTransposedEdge());
    }
}
