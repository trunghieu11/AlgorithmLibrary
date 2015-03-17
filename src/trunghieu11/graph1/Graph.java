package trunghieu11.graph1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 8/22/13
 * Time: 9:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class Graph {
    private final int size;
    private final List<Edge>[] edges;

    public Graph(int size) {
        this.size = size;
        //noinspection unchecked
        edges = new List[size];
        for (int i = 0; i < size; i++)
            edges[i] = new ArrayList<Edge>();
    }

    public int getSize() {
        return size;
    }

    public List<Edge> getIncident(int vertex) {
        return edges[vertex];
    }

    public void add(Edge edge) {
        edges[edge.getSource()].add(edge);
        edge = edge.getReverseEdge();
        if (edge != null)
            edges[edge.getSource()].add(edge);
    }

    //update 22-8-2013
}
