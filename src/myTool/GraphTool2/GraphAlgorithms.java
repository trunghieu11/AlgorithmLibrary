package myTool.GraphTool2;

import myTool.StandardTool.Pair;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 8/22/13
 * Time: 9:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class GraphAlgorithms {
    public static<V> Pair<Map<V, Long>, Map<V, Edge<V>>> dijkstraAlgorithm(Graph<V> graph, V source) {
        Map<V, Long> distance = new HashMap<V, Long>();
        Queue<Pair<Long, V>> queue = new PriorityQueue<Pair<Long, V>>();
        Map<V, Edge<V>> last = new HashMap<V, Edge<V>>();
        distance.put(source, 0L);
        queue.add(Pair.makePair(0L, source));
        Set<V> processed = new HashSet<V>();
        while (!queue.isEmpty()) {
            V current = queue.poll().second;
            if (processed.contains(current))
                continue;
            processed.add(current);
            for (Edge<V> edge : graph.getIncident(current)) {
                V next = edge.getDestination();
                long total = edge.getWeight() + distance.get(current);
                if (!distance.containsKey(next) || distance.get(next) > total) {
                    distance.put(next, total);
                    last.put(next, edge);
                    queue.add(Pair.makePair(total, next));
                }
            }
        }
        return Pair.makePair(distance, last);
    }
}
