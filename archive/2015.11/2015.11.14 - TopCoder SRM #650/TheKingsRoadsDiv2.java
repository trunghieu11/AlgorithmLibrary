package main;

import trunghieu11.collections.Pair;
import trunghieu11.graph.Graph;
import trunghieu11.misc.MiscUtils;
import trunghieu11.numbers.IntegerUtils;

import java.util.HashSet;
import java.util.Set;

public class TheKingsRoadsDiv2 {
    public String getAnswer(int h, int[] a, int[] b) {
        int vertexCount = (int)IntegerUtils.power(2, h) - 1;
        MiscUtils.decreaseByOne(a, b);
        Graph graph = new Graph(vertexCount);

        int badEdge = 0;
        Set<Pair<Integer, Integer>> cache = new HashSet<Pair<Integer, Integer>>();

        for (int i = 0; i < a.length; i++) {
            Pair<Integer, Integer> edge = new Pair<Integer, Integer>(a[i], b[i]);
            Pair<Integer, Integer> backEdge = new Pair<Integer, Integer>(b[i], a[i]);
            if (a[i] == b[i] || cache.contains(edge)) {
                badEdge++;
            } else {
                graph.addSimpleEdge(a[i], b[i]);
                graph.addSimpleEdge(b[i], a[i]);
                cache.add(edge);
                cache.add(backEdge);
            }
        }

        boolean[] visited = new boolean[vertexCount];
        int root = -1;

        if (badEdge > 1) {
            return "Incorrect";
        }

        if (badEdge == 1) {
            for (int i = 0; i < vertexCount; i++) {
                if (graph.getDeg(i) == 4) {
                    if (root != -1) {
                        return "Incorrect";
                    } else {
                        root = i;
                    }
                }
            }
            visited = new boolean[vertexCount];
            return root != -1 && isTree(root, -1, 0, h, graph, visited) ? "Correct" : "Incorrect";
        }

        for (int i = 0; i < graph.edgeCount(); i += 2) {
            root = -1;
            graph.removeEdge(i);
            graph.removeEdge(i + 1);
            for (int j = 0; j < vertexCount; j++) {
                if (graph.getDeg(j) == 4) {
                    root = j;
                }
            }
            visited = new boolean[vertexCount];
            if (root != -1 && isTree(root, -1, 0, h, graph, visited)) {
                return "Correct";
            }
            graph.restoreEdge(i);
            graph.restoreEdge(i + 1);
        }

        return "Incorrect";
    }

    private boolean isTree(int cur, int from, int curHeight, int maxHeight, Graph graph, boolean[] visited) {
        int childCount = 0;
        visited[cur] = true;
        boolean answer = true;

        for (int i = graph.firstOutbound(cur); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next == from || graph.isRemoved(i) || visited[next]) {
                continue;
            }
            childCount++;
            answer &= isTree(next, cur, curHeight + 1, maxHeight, graph, visited);
            if (!answer) {
                return false;
            }
        }

        if (curHeight == maxHeight - 1) {
            return answer && (childCount == 0);
        }
        return answer && (childCount == 2) && (maxHeight > curHeight);
    }
}
