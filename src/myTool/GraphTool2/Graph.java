package myTool.GraphTool2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 8/22/13
 * Time: 9:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class Graph<V> {

    public static final int REMOVED_BIT = 0;
    private Map<V, Edge<V>> firstEdge = new HashMap<V, Edge<V>>();
    private Map<Edge<V>, Edge<V>> nextEdge = new HashMap<Edge<V>, Edge<V>>();
    private Map<Edge<V>, Edge<V>> lastEdge = new HashMap<Edge<V>, Edge<V>>();

    protected int vertexCount;
    protected int edgeCount;

    protected Map<V, Integer> map = new HashMap<V, Integer>();

    public V[] vertices;
    public int[] firstOutbound;
    public int[] firstInbound;

    public Edge<V>[] edges;
    public int[] nextInbound;
    public int[] nextOutbound;
    public int[] from;
    public int[] to;
    public long[] weight;
    public long[] capacity;
    public int[] reverseEdge;
    public boolean[] removed;
    private int[] flags;

    public Edge<V> addWeightedEdge(V from, V to, long weight) {
        return addFlowWeightedEdge(from, to, weight, 0);
    }

    public V getVertex(int id) {
        return vertices[id];
    }

    public Edge<V> getEdge(int id) {
        return edges[id];
    }

    public Iterable<Edge<V>> getIncident(final V vertex) {
        return new Iterable<Edge<V>>() {
            public Iterator<Edge<V>> iterator() {
                return new EdgeIterator(vertex);
            }
        };
    }

    public void add(Edge<V> edge) {
        addImpl(edge);
        edge = edge.getReverseEdge();
        if (edge != null)
            addImpl(edge);
    }

    private void addImpl(Edge<V> edge) {
        V source = edge.getSource();
        if (firstEdge.containsKey(source)) {
            Edge<V> last = firstEdge.get(source);
            lastEdge.put(last, edge);
            nextEdge.put(edge, last);
        }
        firstEdge.put(source, edge);
    }

    public void remove(Edge<V> edge) {
        removeImpl(edge);
        edge = edge.getReverseEdge();
        if (edge != null)
            removeImpl(edge);
    }

    private void removeImpl(Edge<V> edge) {
        V source = edge.getSource();
        if (firstEdge.containsKey(source)) {
            Edge<V> next = nextEdge.get(edge);
            Edge<V> last = lastEdge.get(edge);
            if (last == null) {
                if (next == null)
                    firstEdge.remove(source);
                else {
                    firstEdge.put(source, next);
                    lastEdge.remove(next);
                }
            } else if (next == null)
                nextEdge.remove(last);
            else {
                nextEdge.put(last, next);
                lastEdge.put(next, last);
            }
            nextEdge.remove(edge);
            lastEdge.remove(edge);
        }
    }

    private class EdgeIterator implements Iterator<Edge<V>> {
        private Edge<V> current, last;

        public EdgeIterator(V vertex) {
            current = firstEdge.get(vertex);
        }

        public boolean hasNext() {
            return current != null;
        }

        public Edge<V> next() {
            if (!hasNext())
                throw new NoSuchElementException();
            last = current;
            current = nextEdge.get(current);
            return last;
        }

        public void remove() {
            if (last == null)
                throw new IllegalStateException();
            Graph.this.remove(last);
            last = null;
        }
    }

    public final int vertexCount() {
        return vertexCount;
    }

    public final int addFlowEdge(int from, int to, long capacity) {
        return addFlowWeightedEdge(from, to, 0, capacity);
    }

    public final int addFlowWeightedEdge(int from, int to, long weight, long capacity) {
        if (capacity == 0) {
            return addEdge(from, to, weight, 0, -1);
        } else {
            int lastEdgeCount = edgeCount;
            addEdge(to, from, -weight, 0, lastEdgeCount + entriesPerEdge());
            return addEdge(from, to, weight, capacity, lastEdgeCount);
        }
    }

    public final int firstOutbound(int vertex) {
        int id = firstOutbound[vertex];
        while (id != -1 && isRemoved(id))
            id = nextOutbound[id];
        return id;
    }

    public final boolean isRemoved(int id) {
        return flag(id, REMOVED_BIT);
    }

    public final boolean flag(int id, int bit) {
        return (flags[id] >> bit & 1) != 0;
    }

    public final long weight(int id) {
        if (weight == null)
            return 0;
        return weight[id];
    }

    public final int destination(int id) {
        return to[id];
    }

    public final long capacity(int id) {
        if (capacity == null)
            return 0;
        return capacity[id];
    }

    public final int nextOutbound(int id) {
        id = nextOutbound[id];
        while (id != -1 && isRemoved(id))
            id = nextOutbound[id];
        return id;
    }

    public final int source(int id) {
        return from[id];
    }

    public final void pushFlow(int id, long flow) {
        if (flow == 0)
            return;
        if (flow > 0) {
            if (capacity(id) < flow)
                throw new IllegalArgumentException("Not enough capacity");
        } else {
            if (flow(id) < -flow)
                throw new IllegalArgumentException("Not enough capacity");
        }
        capacity[id] -= flow;
        capacity[reverseEdge[id]] += flow;
    }

    public final long flow(int id) {
        if (reverseEdge == null)
            return 0;
        return capacity[reverseEdge[id]];
    }

    //Update

    public Graph() {
        this(10);
    }

    public Graph(int vertexCapacity) {
        this(vertexCapacity, vertexCapacity);
    }

    public Graph(int vertexCapacity, int edgeCapacity) {
        //noinspection unchecked
        vertices = (V[]) new Object[vertexCapacity];
        firstOutbound = new int[vertexCapacity];
        firstInbound = new int[vertexCapacity];

        //noinspection unchecked
        edges = new Edge[edgeCapacity];
        from = new int[edgeCapacity];
        to = new int[edgeCapacity];
        nextInbound = new int[edgeCapacity];
        nextOutbound = new int[edgeCapacity];
        weight = new long[edgeCapacity];
        capacity = new long[edgeCapacity];
        reverseEdge = new int[edgeCapacity];
        removed = new boolean[edgeCapacity];
    }

    public int addEdge(int fromID, int toID, long weight, long capacity, int reverseEdge) {
        ensureEdgeCapacity(edgeCount + 1);
        if (firstOutbound[fromID] != -1)
            nextOutbound[edgeCount] = firstOutbound[fromID];
        else
            nextOutbound[edgeCount] = -1;
        firstOutbound[fromID] = edgeCount;
        if (firstInbound[toID] != -1)
            nextInbound[edgeCount] = firstInbound[toID];
        else
            nextInbound[edgeCount] = -1;
        firstInbound[toID] = edgeCount;
        this.from[edgeCount] = fromID;
        this.to[edgeCount] = toID;
        this.weight[edgeCount] = weight;
        this.capacity[edgeCount] = capacity;
        this.reverseEdge[edgeCount] = reverseEdge;
        edges[edgeCount] = createEdge(edgeCount);
        return edgeCount++;
    }

    protected GraphEdge createEdge(int id) {
        return new GraphEdge(id);
    }

    public Edge<V> addFlowWeightedEdge(V from, V to, long weight, long capacity) {
        int fromID = resolveOrAdd(from);
        int toID = resolveOrAdd(to);
        if (capacity == 0) {
            int result = addEdge(fromID, toID, weight, 0, -1);
            return edges[result];
        } else {
            int lastEdgeCount = edgeCount;
            addEdge(toID, fromID, -weight, 0, lastEdgeCount + entriesPerEdge());
            int result = addEdge(fromID, toID, weight, capacity, lastEdgeCount);
            return edges[result];
        }
    }

    protected int entriesPerEdge() {
        return 1;
    }

    public Edge<V> addFlowEdge(V from, V to, long capacity) {
        return addFlowWeightedEdge(from, to, 0, capacity);
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int resolve(V vertex) {
        if (map.containsKey(vertex))
            return map.get(vertex);
        throw new IllegalArgumentException();
    }

    private int resolveOrAdd(V vertex) {
        if (map.containsKey(vertex))
            return map.get(vertex);
        ensureVertexCapacity(vertexCount + 1);
        map.put(vertex, vertexCount);
        vertices[vertexCount] = vertex;
        firstInbound[vertexCount] = firstOutbound[vertexCount] = -1;
        return vertexCount++;
    }

    protected void ensureEdgeCapacity(int size) {
        if (from.length < size) {
            int newSize = Math.max(size, 2 * from.length);
            edges = resize(edges, newSize);
            from = resize(from, newSize);
            to = resize(to, newSize);
            nextInbound = resize(nextInbound, newSize);
            nextOutbound = resize(nextOutbound, newSize);
            weight = resize(weight, newSize);
            capacity = resize(capacity, newSize);
            reverseEdge = resize(reverseEdge, newSize);
            removed = resize(removed, newSize);
        }
    }

    protected void ensureVertexCapacity(int size) {
        if (firstInbound.length < size) {
            int newSize = Math.max(size, 2 * firstInbound.length);
            vertices = resize(vertices, newSize);
            firstInbound = resize(firstInbound, newSize);
            firstOutbound = resize(firstOutbound, newSize);
        }
    }

    protected int[] resize(int[] array, int size) {
        int[] newArray = new int[size];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    protected boolean[] resize(boolean[] array, int size) {
        boolean[] newArray = new boolean[size];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    private long[] resize(long[] array, int size) {
        long[] newArray = new long[size];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    private Edge<V>[] resize(Edge<V>[] array, int size) {
        @SuppressWarnings("unchecked")
        Edge<V>[] newArray = new Edge[size];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    private V[] resize(V[] array, int size) {
        @SuppressWarnings("unchecked")
        V[] newArray = (V[]) new Object[size];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    public boolean isSparse() {
        return vertexCount == 0 || edgeCount * 20 / vertexCount <= vertexCount;
    }

    protected class GraphEdge implements Edge<V> {
        protected int id;

        protected GraphEdge(int id) {
            this.id = id;
        }

        @Override
        public V getSource() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public V getDestination() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public long getWeight() {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public Edge<V> getTransposedEdge() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public Edge<V> getReverseEdge() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public void pushFlow(long flow) {
            if (flow == 0)
                return;
            if (flow > 0) {
                if (capacity[id] < flow)
                    throw new IllegalArgumentException("Not enough capacity");
            } else {
                if (capacity[reverseEdge[id]] < -flow)
                    throw new IllegalArgumentException("Not enough capacity");
            }
            capacity[id] -= flow;
            capacity[reverseEdge[id]] += flow;
        }

    }

}
