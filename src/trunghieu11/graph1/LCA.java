package trunghieu11.graph1;

/**
 * Created by nthieu1 on 3/4/2015.
 */
public class LCA {
    private int len;
    private int timer = 0;

    private final int[][] graph;
    private final int[] level;
    private final int[][] ancestor;
    private final int[] timeIn;
    private final int[] timeOut;

    public LCA(int[][] graph) {
        this(graph, 0);
    }

    public LCA(int[][] graph, int root) {
        int graphSize = graph.length;
        len = Integer.numberOfTrailingZeros(Integer.highestOneBit(graphSize - 1)) + 1;

        this.graph = graph;
        level = new int[graphSize];
        ancestor = new int[graphSize][len];
        timeIn = new int[graphSize];
        timeOut = new int[graphSize];

        init(root, root, 0);
    }

    private void init(int cur, int from, int deep) {
        level[cur] = deep;
        timeIn[cur] = timer++;

        ancestor[cur][0] = from;
        for (int i = 1; i < len; i++) {
            ancestor[cur][i] = ancestor[ancestor[cur][i - 1]][i - 1];
        }

        for (int next : graph[cur]) {
            if (next == from) {
                continue;
            }
            init(next, cur, deep + 1);
        }

        timeOut[cur] = timer++;
    }

    private boolean isAncestor(int first, int second) {
        return timeIn[first] <= timeIn[second] && timeOut[second] <= timeOut[first];
    }

    private int goUp(int first, int second) {
        for (int i = len - 1; i >= 0; i--) {
            if (!isAncestor(ancestor[first][i], second)) {
                first = ancestor[first][i];
            }
        }
        return first;
    }

    public int getLCA(int first, int second) {
        int answer = -1;
        if (isAncestor(first, second)) {
            answer = first;
        } else if (isAncestor(second, first)) {
            answer = second;
        } else {
            answer = ancestor[goUp(first, second)][0];
        }
        return answer;
    }

    public int getPathLength(int first, int second) {
        return level[first] + level[second] - 2 * level[getLCA(first, second)];
    }

    public int getAncestor(int vertex, int dist) {
        if (dist == 0) {
            return vertex;
        }
        int curVertex = vertex;
        for (int i = len - 1; i >= 0; i--) {
            if (level[curVertex] - level[ancestor[vertex][i]] < dist) {
                vertex = ancestor[vertex][i];
            }
        }

        return ancestor[vertex][0];
    }

    public int getAncestorAt(int vertex, int idx) {
        return ancestor[vertex][idx];
    }

    public int getLevel(int vertex) {
        return level[vertex];
    }
}
