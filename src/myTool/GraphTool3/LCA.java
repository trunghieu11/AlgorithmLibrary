package myTool.GraphTool3;

import myTool.DataStructureTool.IntervalTree;
import myTool.DataStructureTool.ReadOnlyIntervalTree;

import java.util.Arrays;

/**
 * Created by OnePiece on 12/7/2014.
 */
public class LCA {
    private final long[] order;
    private final int[] position;
    private final Graph graph;
    private final IntervalTree lcaTree;
    private final int[] level;

    public LCA(Graph graph) {
        this(graph, 0);
    }

    public LCA(Graph graph, int root) {
        this.graph = graph;
        order = new long[2 * graph.vertexCount() - 1];
        position = new int[graph.vertexCount()];
        level = new int[graph.vertexCount()];
        int[] index = new int[graph.vertexCount()];
        for (int i = 0; i < index.length; i++)
            index[i] = graph.firstOutbound(i);
        int[] last = new int[graph.vertexCount()];
        int[] stack = new int[graph.vertexCount()];
        stack[0] = root;
        int size = 1;
        int j = 0;
        last[root] = -1;
        Arrays.fill(position, -1);
        while (size > 0) {
            int vertex = stack[--size];
            if (position[vertex] == -1)
                position[vertex] = j;
            order[j++] = vertex;
            if (last[vertex] != -1)
                level[vertex] = level[last[vertex]] + 1;
            while (index[vertex] != -1 && last[vertex] == graph.destination(index[vertex]))
                index[vertex] = graph.nextOutbound(index[vertex]);
            if (index[vertex] != -1) {
                stack[size++] = vertex;
                stack[size++] = graph.destination(index[vertex]);
                last[graph.destination(index[vertex])] = vertex;
                index[vertex] = graph.nextOutbound(index[vertex]);
            }
        }
        lcaTree = new ReadOnlyIntervalTree(order) {
            @Override
            protected void updatePostProcess(int root, int left, int right, int from, int to, long delta, int middle) {

            }

            @Override
            protected void updatePreProcess(int root, int left, int right, int from, int to, long delta, int middle) {

            }

            @Override
            protected void updateFull(int root, int left, int right, int from, int to, long delta) {

            }

            @Override
            protected long joinValue(long left, long right) {
                if (left == -1)
                    return right;
                if (right == -1)
                    return left;
                if (level[((int) left)] < level[((int) right)])
                    return left;
                return right;
            }

            @Override
            protected long neutralValue() {
                return -1;
            }
        };
        lcaTree.init();
    }

    public int getLCA(int first, int second) {
        return (int) lcaTree.query(Math.min(position[first], position[second]), Math.max(position[first], position[second]));
    }
}
