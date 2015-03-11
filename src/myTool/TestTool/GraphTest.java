package myTool.TestTool;

import myTool.StandardTool.MiscUtils;
import myTool.StandardTool.Pair;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by nthieu1 on 3/4/2015.
 */
public class GraphTest {
    private static Random random = new Random(239);

    public static int[][] generateTree(int vertexCount) {
        int[] from = new int[vertexCount - 1];
        int[] to = new int[vertexCount - 1];
        boolean[] visited = new boolean[vertexCount];
        visited[0] = true;
        for (int i = 0; i < vertexCount - 1; i++) {
            int curFrom = random.nextInt(vertexCount);
            int curTo = random.nextInt(vertexCount);
            while (!visited[curFrom]) {
                curFrom = random.nextInt(vertexCount);
            }
            from[i] = curFrom;
            while (visited[curTo]) {
                curTo = random.nextInt(vertexCount);
            }
            to[i] = curTo;
            visited[curTo] = true;
        }
        MiscUtils.increaseByOne(from, to);
        return new int[][]{from, to};
    }

    public static int[][] generateSimpleGraph(int vertexCount, int edgeCount) {
        int[][] tree = generateTree(vertexCount);
        Set<Pair<Integer, Integer>> graph = new HashSet<Pair<Integer, Integer>>();
        for (int i = 0; i < vertexCount - 1; i++) {
            graph.add(new Pair<Integer, Integer>(tree[0][i], tree[1][i]));
        }
        while (graph.size() < edgeCount) {
            int from = random.nextInt(vertexCount) + 1;
            int to = random.nextInt(vertexCount) + 1;
            while (from == to || graph.contains(new Pair<Integer, Integer>(from, to)) || graph.contains(new Pair<Integer, Integer>(to, from))) {
                from = random.nextInt(vertexCount) + 1;
                to = random.nextInt(vertexCount) + 1;
            }
            graph.add(new Pair<Integer, Integer>(from, to));
        }
        int[] from = new int[edgeCount];
        int[] to = new int[edgeCount];
        int idx = 0;
        for (Pair<Integer, Integer> x : graph) {
            from[idx] = x.first;
            to[idx] = x.second;
            idx++;
        }
        return new int[][]{from, to};
    }
}
