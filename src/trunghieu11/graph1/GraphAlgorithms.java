package trunghieu11.graph1;

import trunghieu11.collections.Pair;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 8/22/13
 * Time: 9:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class GraphAlgorithms {
    public static Pair<Long, Long> minCostMaxFlow(Graph graph, int source, int destination) {
        return minCostMaxFlow(graph, source, destination, Long.MAX_VALUE);
    }

    public static Pair<Long, Long> minCostMaxFlow(Graph graph, int source, int destination, long maxFlow) {
        long cost = 0;
        long flow = 0;
        long[] phi = new long[graph.getSize()];
        long[] initialDistances = fordBellman(graph, source, true).first;
        for (int i = 0; i < graph.getSize(); i++) {
            if (initialDistances[i] != Long.MAX_VALUE)
                phi[i] -= initialDistances[i];
        }
        while (maxFlow != 0) {
            Pair<long[], Edge[]> result = dijkstraAlgorithm(graph, source, phi);
            if (result.first[destination] == Long.MAX_VALUE)
                return new Pair<Long, Long>(cost, flow);
//                return Pair.makePair(cost, flow);
            for (int i = 0; i < graph.getSize(); i++) {
                if (result.first[i] != Long.MAX_VALUE)
                    phi[i] -= result.first[i];
            }
            int vertex = destination;
            long currentFlow = maxFlow;
            long currentCost = 0;
            while (vertex != source) {
                currentFlow = Math.min(currentFlow, result.second[vertex].getCapacity());
                currentCost += result.second[vertex].getWeight();
                vertex = result.second[vertex].getSource();
            }
            maxFlow -= currentFlow;
            cost += currentCost * currentFlow;
            flow += currentFlow;
            vertex = destination;
            while (vertex != source) {
                result.second[vertex].pushFlow(currentFlow);
                vertex = result.second[vertex].getSource();
            }
        }
        return new Pair<Long, Long>(cost, flow);
//        return Pair.makePair(cost, flow);
    }

    public static Pair<long[], Edge[]> dijkstraAlgorithm(Graph graph, int source, long[] phi) {
        int size = graph.getSize();
        final long[] distance = new long[size];
        Queue<Pair<Long, Integer>> queue = new PriorityQueue<Pair<Long, Integer>>(size);
        Edge[] last = new Edge[size];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[source] = 0;
        queue.add(new Pair<Long, Integer>(0L, source));
//        queue.add(Pair.makePair(0L, source));
        boolean[] processed = new boolean[size];
        while (!queue.isEmpty()) {
            int current = queue.poll().second;
            if (processed[current])
                continue;
            processed[current] = true;
            for (Edge edge : graph.getIncident(current)) {
                if (edge.getCapacity() == 0)
                    continue;
                int next = edge.getDestination();
                long weight = edge.getWeight() + phi[next] - phi[current];
                if (distance[next] > distance[current] + weight) {
                    distance[next] = distance[current] + weight;
                    last[next] = edge;
                    queue.add(new Pair<Long, Integer>(distance[next], next));
//                    queue.add(Pair.makePair(distance[next], next));
                }
            }
        }
        return new Pair<long[], Edge[]>(distance, last);
//        return Pair.makePair(distance, last);
    }

    public static Pair<long[], Edge[]> fordBellman(Graph graph, int source, boolean ignoreEmptyEdges) {
        long[] distances = new long[graph.getSize()];
        Arrays.fill(distances, Long.MAX_VALUE);
        distances[source] = 0;
        Edge[] last = new Edge[graph.getSize()];
        Set<Integer> viable = Collections.singleton(source);
        int stepCount = 0;
        while (!viable.isEmpty()) {
            Set<Integer> nextViable = new HashSet<Integer>();
            for (int i : viable) {
                for (Edge edge : graph.getIncident(i)) {
                    long total = distances[i] + edge.getWeight();
                    int destination = edge.getDestination();
                    if (total < distances[destination] && (!ignoreEmptyEdges || edge.getCapacity() != 0)) {
                        distances[destination] = total;
                        last[destination] = edge;
                        nextViable.add(destination);
                    }
                }
            }
            viable = nextViable;
            stepCount++;
            if (stepCount > graph.getSize() + 1)
                return null;
        }
        return new Pair<long[], Edge[]>(distances, last);
//        return Pair.makePair(distances, last);
    }

    public static class DistanceResult {
        public final long[] distances;
        public final Edge[] last;

        public DistanceResult(long[] distances, Edge[] last) {
            this.distances = distances;
            this.last = last;
        }
    }

    public static DistanceResult leviteAlgorithm(Graph graph, int source) {
        return leviteAlgorithm(graph, source, false);
    }

    public static DistanceResult leviteAlgorithm(Graph graph, int source, boolean ignoreEmpty) {
        int size = graph.getSize();
        Deque<Integer> queue = new ArrayDeque<Integer>(size);
        boolean[] processed = new boolean[size];
        boolean[] notReached = new boolean[size];
        Arrays.fill(notReached, true);
        long[] distance = new long[size];
        Edge[] last = new Edge[size];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[source] = 0;
        queue.add(source);
        notReached[source] = false;
        int iterationCount = 0;
        while (!queue.isEmpty()) {
            iterationCount++;
            if (iterationCount / size / size / size != 0)
                return null;
            int current = queue.poll();
            processed[current] = true;
            for (Edge edge : graph.getIncident(current)) {
                if (ignoreEmpty && edge.getCapacity() == 0)
                    continue;
                int next = edge.getDestination();
                long weight = edge.getWeight();
                if (distance[next] > distance[current] + weight) {
                    distance[next] = distance[current] + weight;
                    last[next] = edge;
                    if (notReached[next]) {
                        notReached[next] = false;
                        queue.add(next);
                    } else if (processed[next]) {
                        processed[next] = false;
                        queue.addFirst(next);
                    }
                }
            }
        }
        return new DistanceResult(distance, last);
    }
}
