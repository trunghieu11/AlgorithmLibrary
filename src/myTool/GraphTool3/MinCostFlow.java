package myTool.GraphTool3;

import myTool.DataStructureTool.IntArrayList;
import myTool.DataStructureTool.IntComparator;
import myTool.DataStructureTool.IntList;
import myTool.DataStructureTool.IntegerUtils;
import myTool.StandardTool.Pair;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 8/23/13
 * Time: 11:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class MinCostFlow {
    private final Graph graph;
    private final int source;
    private final int destination;
    private final long[] phi;
    private final long[] dijkstraResult;
    private final int[] lastEdge;
    private final Heap heap;
    private final int vertexCount;
    private final int[] visited;
    private int visitIndex;

    public MinCostFlow(Graph graph, int source, int destination, boolean hasNegativeEdges) {
        this.graph = graph;
        this.source = source;
        this.destination = destination;
        vertexCount = graph.vertexCount();
        phi = new long[vertexCount];
        if (hasNegativeEdges)
            fordBellman();
        dijkstraResult = new long[vertexCount];
        lastEdge = new int[vertexCount];
        if (graph.isSparse()) {
            heap = new Heap(vertexCount, new IntComparator() {
                public int compare(int first, int second) {
                    return IntegerUtils.longCompare(dijkstraResult[first], dijkstraResult[second]);
                }
            }, vertexCount);
            visited = null;
        } else {
            heap = null;
            visited = new int[vertexCount];
        }
    }

    private void fordBellman() {
        Arrays.fill(phi, Long.MAX_VALUE);
        phi[source] = 0;
        boolean[] inQueue = new boolean[vertexCount];
        int[] queue = new int[vertexCount + 1];
        queue[0] = source;
        inQueue[source] = true;
        int stepCount = 0;
        int head = 0;
        int end = 1;
        int maxSteps = 2 * vertexCount * vertexCount;
        while (head != end) {
            int vertex = queue[head++];
            if (head == queue.length)
                head = 0;
            inQueue[vertex] = false;
            int edgeID = graph.firstOutbound(vertex);
            while (edgeID != -1) {
                long total = phi[vertex] + graph.weight(edgeID);
                int destination = graph.destination(edgeID);
                if (graph.capacity(edgeID) != 0 && phi[destination] > total) {
                    phi[destination] = total;
                    if (!inQueue[destination]) {
                        queue[end++] = destination;
                        inQueue[destination] = true;
                        if (end == queue.length)
                            end = 0;
                    }
                }
                edgeID = graph.nextOutbound(edgeID);
            }
            if (++stepCount > maxSteps)
                throw new IllegalArgumentException("Graph contains negative cycle");
        }
    }

    class Heap {
        IntList list;
        int shift;
        int[] index;

        public Heap(int vertexCount, IntComparator intComparator, int vertexCount1) {
            list = new IntArrayList(vertexCount);
            index = new int[vertexCount1];
            Arrays.fill(index, -1);
        }

        public void add(int source) {
            index[source] = 0;
            list.add(source);
        }

        public boolean isEmpty() {
            return shift == list.size();
        }

        public int poll() {
            int result = list.get(shift++);
            index[result] = -1;
            return result;
        }

        public int getIndex(int next) {
            return index[next];
        }

        public void shiftUp(int index) {
        }

        public void clear() {
            list = new IntArrayList();
            shift = 0;
            Arrays.fill(index, -1);
        }
    }

    public Pair<Long, Long> minCostMaxFlow() {
        return minCostMaxFlow(Long.MAX_VALUE);
    }

    public Pair<Long, Long> minCostMaxFlow(long maxFlow) {
        long cost = 0;
        long flow = 0;
        while (maxFlow != 0) {
            if (graph.isSparse())
                dijkstraAlgorithm();
            else
                dijkstraAlgorithmFull();
            if (lastEdge[destination] == -1)
                return Pair.makePair(cost, flow);
            for (int i = 0; i < dijkstraResult.length; i++) {
                if (dijkstraResult[i] != Long.MAX_VALUE)
                    phi[i] += dijkstraResult[i];
            }
            int vertex = destination;
            long currentFlow = maxFlow;
            long currentCost = 0;
            while (vertex != source) {
                int edgeID = lastEdge[vertex];
                currentFlow = Math.min(currentFlow, graph.capacity(edgeID));
                currentCost += graph.weight(edgeID);
                vertex = graph.source(edgeID);
            }
            maxFlow -= currentFlow;
            cost += currentCost * currentFlow;
            flow += currentFlow;
            vertex = destination;
            while (vertex != source) {
                int edgeID = lastEdge[vertex];
                graph.pushFlow(edgeID, currentFlow);
                vertex = graph.source(edgeID);
            }
        }
        return Pair.makePair(cost, flow);
    }

    private void dijkstraAlgorithm() {
        Arrays.fill(dijkstraResult, Long.MAX_VALUE);
        Arrays.fill(lastEdge, -1);
        dijkstraResult[source] = 0;
        heap.clear();
        heap.add(source);
        while (!heap.isEmpty()) {
            int current = heap.poll();
            int edgeID = graph.firstOutbound(current);
            while (edgeID != -1) {
                if (graph.capacity(edgeID) != 0) {
                    int next = graph.destination(edgeID);
                    long total = graph.weight(edgeID) - phi[next] + phi[current] + dijkstraResult[current];
                    if (dijkstraResult[next] > total) {
                        dijkstraResult[next] = total;
                        if (heap.getIndex(next) == -1)
                            heap.add(next);
                        else
                            heap.shiftUp(heap.getIndex(next));
                        lastEdge[next] = edgeID;
                    }
                }
                edgeID = graph.nextOutbound(edgeID);
            }
        }
    }

    private void dijkstraAlgorithmFull() {
        visitIndex++;
        Arrays.fill(dijkstraResult, Long.MAX_VALUE);
        lastEdge[destination] = -1;
        dijkstraResult[source] = 0;
        for (int i = 0; i < vertexCount; i++) {
            int index = -1;
            long length = Long.MAX_VALUE;
            for (int j = 0; j < vertexCount; j++) {
                if (visited[j] != visitIndex && dijkstraResult[j] < length) {
                    length = dijkstraResult[j];
                    index = j;
                }
            }
            if (index == -1) {
                return;
            }
            visited[index] = visitIndex;
            int edgeID = graph.firstOutbound(index);
            while (edgeID != -1) {
                if (graph.capacity(edgeID) != 0) {
                    int next = graph.destination(edgeID);
                    if (visited[next] != visitIndex) {
                        long total = graph.weight(edgeID) - phi[next] + phi[index] + length;
                        if (dijkstraResult[next] > total) {
                            dijkstraResult[next] = total;
                            lastEdge[next] = edgeID;
                        }
                    }
                }
                edgeID = graph.nextOutbound(edgeID);
            }
        }
    }
}
