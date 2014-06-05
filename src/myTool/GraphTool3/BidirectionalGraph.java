package myTool.GraphTool3;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 8/22/13
 * Time: 10:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class BidirectionalGraph extends Graph {
    public int[] transposedEdge;

    public BidirectionalGraph(int vertexCount) {
        this(vertexCount, vertexCount);
    }

    public BidirectionalGraph(int vertexCount, int edgeCapacity) {
        super(vertexCount, 2 * edgeCapacity);
        transposedEdge = new int[2 * edgeCapacity];
    }

    public static BidirectionalGraph createGraph(int vertexCount, int[] from, int[] to) {
        BidirectionalGraph graph = new BidirectionalGraph(vertexCount, from.length);
        for (int i = 0; i < from.length; i++)
            graph.addSimpleEdge(from[i], to[i]);
        return graph;
    }

    public int addEdge(int fromID, int toID, long weight, long capacity, int reverseEdge) {
        int lastEdgeCount = edgeCount;
        super.addEdge(fromID, toID, weight, capacity, reverseEdge);
        super.addEdge(toID, fromID, weight, capacity, reverseEdge);
        this.transposedEdge[lastEdgeCount] = lastEdgeCount + 1;
        this.transposedEdge[lastEdgeCount + 1] = lastEdgeCount;
        return lastEdgeCount;
    }

    protected int entriesPerEdge() {
        return 2;
    }

    protected void ensureEdgeCapacity(int size) {
        if (size > edgeCapacity()) {
            super.ensureEdgeCapacity(size);
            transposedEdge = resize(transposedEdge, edgeCapacity());
        }
    }
}
