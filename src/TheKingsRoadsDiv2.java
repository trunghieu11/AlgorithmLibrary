import java.util.Arrays;
import java.util.Collection;
import java.util.Queue;
import java.util.ArrayDeque;

public class TheKingsRoadsDiv2 {
    public String getAnswer(int h, int[] from, int[] to) {
        MiscUtils.decreaseByOne(from, to);
        int vertexCount = from.length;
        Graph graph = new Graph(vertexCount);
        for (int i = 0; i < vertexCount; i++) {
            graph.addSimpleEdge(from[i], to[i]);
            graph.addSimpleEdge(to[i], from[i]);
        }

        for (int i = 0; i < vertexCount; i++) {
            graph.removeEdge(2 * i);
            graph.removeEdge(2 * i + 1);
            if (isBalancedTree(graph, h)) {
                return "Correct";
            }
            graph.restoreEdge(2 * i);
            graph.restoreEdge(2 * i + 1);
        }
        return "Incorrect";
    }

    private boolean isBalancedTree(Graph graph, int h) {
        int root = -1;

        for (int i = 0; i < graph.vertexCount(); i++) {
            if (graph.getDeg(i) == 4) {
                if (root == -1) {
                    root = i;
                } else {
                    return false;
                }
            }
        }

        if (root == -1) {
            return false;
        }

        Queue<Node> queue = new ArrayDeque<Node>();
        queue.add(new Node(root, 0, -1));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.height >= h) {
                return false;
            }
            int childCount = 0;
            for (int id = graph.firstOutbound(cur.vertex); id != -1; id = graph.nextOutbound(id)) {
                if (graph.destination(id) == cur.from || graph.isRemoved(id) || graph.destination(id) == graph.source(id)) {
                    continue;
                }
                childCount++;
                queue.add(new Node(graph.destination(id), cur.height + 1, cur.vertex));
            }

            if (childCount != 2 && cur.height < h - 1) {
                return false;
            }
            if (childCount != 0 && cur.height >= h - 1) {
                return false;
            }
        }

        return true;
    }

    private class Node {
        public int vertex;
        public int height;
        public int from;

        public Node(int vertex, int height, int from) {
            this.vertex = vertex;
            this.height = height;
            this.from = from;
        }

    }

}

class Graph {
    public static final int REMOVED_BIT = 0;
    protected int vertexCount;
    protected int edgeCount;
    private int[] firstOutbound;
    private int[] firstInbound;
    private Edge[] edges;
    private int[] nextInbound;
    private int[] nextOutbound;
    private int[] from;
    private int[] to;
    private long[] weight;
    public long[] capacity;
    private int[] reverseEdge;
    private int[] flags;
    private int[] deg;

    public Graph(int vertexCount) {
        this(vertexCount, vertexCount);
    }

    public Graph(int vertexCount, int edgeCapacity) {
        this.vertexCount = vertexCount;
        firstOutbound = new int[vertexCount];
        this.deg = new int[vertexCount];
        Arrays.fill(firstOutbound, -1);

        from = new int[edgeCapacity];
        to = new int[edgeCapacity];
        nextOutbound = new int[edgeCapacity];
        flags = new int[edgeCapacity];
    }

    public int addEdge(int fromID, int toID, long weight, long capacity, int reverseEdge) {
        deg[fromID]++;
        deg[toID]++;

        ensureEdgeCapacity(edgeCount + 1);
        if (firstOutbound[fromID] != -1)
            nextOutbound[edgeCount] = firstOutbound[fromID];
        else
            nextOutbound[edgeCount] = -1;
        firstOutbound[fromID] = edgeCount;
        if (firstInbound != null) {
            if (firstInbound[toID] != -1)
                nextInbound[edgeCount] = firstInbound[toID];
            else
                nextInbound[edgeCount] = -1;
            firstInbound[toID] = edgeCount;
        }
        this.from[edgeCount] = fromID;
        this.to[edgeCount] = toID;
        if (capacity != 0) {
            if (this.capacity == null)
                this.capacity = new long[from.length];
            this.capacity[edgeCount] = capacity;
        }
        if (weight != 0) {
            if (this.weight == null)
                this.weight = new long[from.length];
            this.weight[edgeCount] = weight;
        }
        if (reverseEdge != -1) {
            if (this.reverseEdge == null) {
                this.reverseEdge = new int[from.length];
                Arrays.fill(this.reverseEdge, 0, edgeCount, -1);
            }
            this.reverseEdge[edgeCount] = reverseEdge;
        }
        if (edges != null)
            edges[edgeCount] = createEdge(edgeCount);
        return edgeCount++;
    }

    protected final GraphEdge createEdge(int id) {
        return new GraphEdge(id);
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

    protected int entriesPerEdge() {
        return 1;
    }

    public final int addWeightedEdge(int from, int to, long weight) {
        return addFlowWeightedEdge(from, to, weight, 0);
    }

    public final int addSimpleEdge(int from, int to) {
        return addWeightedEdge(from, to, 0);
    }

    public final int vertexCount() {
        return vertexCount;
    }

    public final int getDeg(int vertex) {
        return this.deg[vertex];
    }

    public final int firstOutbound(int vertex) {
        int id = firstOutbound[vertex];
        while (id != -1 && isRemoved(id))
            id = nextOutbound[id];
        return id;
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

    public final int destination(int id) {
        return to[id];
    }

    public final boolean flag(int id, int bit) {
        return (flags[id] >> bit & 1) != 0;
    }

    public final void setFlag(int id, int bit) {
        flags[id] |= 1 << bit;
    }

    public final void removeFlag(int id, int bit) {
        flags[id] &= -1 - (1 << bit);
    }

    public final void removeEdge(int id) {
        deg[from[id]]--;
        deg[to[id]]--;
        setFlag(id, REMOVED_BIT);
    }

    public final void restoreEdge(int id) {
        deg[from[id]]++;
        deg[to[id]]++;
        removeFlag(id, REMOVED_BIT);
    }

    public final boolean isRemoved(int id) {
        return flag(id, REMOVED_BIT);
    }

    protected void ensureEdgeCapacity(int size) {
        if (from.length < size) {
            int newSize = Math.max(size, 2 * from.length);
            if (edges != null)
                edges = resize(edges, newSize);
            from = resize(from, newSize);
            to = resize(to, newSize);
            nextOutbound = resize(nextOutbound, newSize);
            if (nextInbound != null)
                nextInbound = resize(nextInbound, newSize);
            if (weight != null)
                weight = resize(weight, newSize);
            if (capacity != null)
                capacity = resize(capacity, newSize);
            if (reverseEdge != null)
                reverseEdge = resize(reverseEdge, newSize);
            flags = resize(flags, newSize);
        }
    }

    protected final int[] resize(int[] array, int size) {
        int[] newArray = new int[size];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    private long[] resize(long[] array, int size) {
        long[] newArray = new long[size];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    private Edge[] resize(Edge[] array, int size) {
        Edge[] newArray = new Edge[size];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    protected class GraphEdge implements Edge {
        protected int id;

        protected GraphEdge(int id) {
            this.id = id;
        }

    }

}

class MiscUtils {
    public static void decreaseByOne(int[]... arrays) {
        for (int[] array : arrays) {
            for (int i = 0; i < array.length; i++)
                array[i]--;
        }
    }

}

interface Edge {
}
