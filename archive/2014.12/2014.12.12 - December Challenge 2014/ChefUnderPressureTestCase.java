package main;





import myTool.DataStructureTool.ArrayUtils;
import myTool.GraphTool3.BidirectionalGraph;
import myTool.GraphTool3.Graph;
import myTool.GraphTool3.LCA;
import myTool.IOTool.IOUtils;
import myTool.IOTool.OutputWriter;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.io.StringWriter;
import java.util.*;

public class ChefUnderPressureTestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 0;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            tests.add(new Test(sw.toString()));
        }
        return tests;
    }

    @TestCase
    public Collection<Test> accuracyTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 100;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);

            int MAXN = 1000;

            int count = random.nextInt(MAXN) + 1;
            int productCount = random.nextInt(MAXN) + 1;
            int capital = random.nextInt(count) + 1;
            int[] from = new int[count - 1];
            int[] to = new int[count - 1];

            boolean[] visited = new boolean[count];
            int val = random.nextInt(count);
            visited[val] = true;

            for (int i = 0; i < count - 1; i++) {
                val = random.nextInt(count);
                while (!visited[val])
                    val = random.nextInt(count);
                from[i] = val + 1;
                int cur = random.nextInt(count);
                while (visited[cur])
                    cur = random.nextInt(count);
                to[i] = cur + 1;
                visited[cur] = true;
            }

            int[] products = new int[count];
            for (int i = 0; i < count; i++) {
                products[i] = random.nextInt(productCount) + 1;
            }

            int queryCount = random.nextInt(MAXN) + 1;
            int[] cur = new int[queryCount];
            int[] want = new int[queryCount];
            for (int i = 0; i < queryCount; i++) {
                cur[i] = random.nextInt(count) + 1;
                want[i] = random.nextInt(productCount) + 1;
            }

            out.printLine(count, productCount);
            out.printLine(capital);
            for (int i = 0; i < count - 1; i++) {
                out.printLine(from[i], to[i]);
            }
            for (int i = 0; i < count; i++) {
                out.printLine(products[i]);
            }

            out.printLine(queryCount);
            for (int i = 0; i < queryCount; i++) {
                out.printLine(cur[i], want[i]);
            }

            findAnswer(count, productCount, capital - 1, from, to, products, queryCount, cur, want, outAnswer);

            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }

    private void findAnswer(int count, int productCount, int capital, int[] from, int[] to, int[] products, int queryCount, int[] queryCur, int[] queryWant, OutputWriter outAnswer) {
        Graph graph = new BidirectionalGraph(count, count - 1);

        for (int i = 0; i < count - 1; i++) {
            graph.addSimpleEdge(from[i] - 1, to[i] - 1);
        }

        LCA lca = new LCA(graph, capital);
        int[] distance = new int[count];
        dfs(-1, capital, 1, graph, distance);

        for (int query = 0; query < queryCount; query++) {
            int cur = queryCur[query] - 1;
            int want = queryWant[query];
            int answer = Integer.MAX_VALUE;
            int maxVal = 0;
            for (int i = 0; i < count; i++) {
                if (products[i] == want) {
                    int ancestor = lca.getLCA(cur, i);
                    if (distance[ancestor] > maxVal) {
                        answer = i;
                        maxVal = distance[ancestor];
                    }
                    else if (distance[ancestor] == maxVal) {
                        answer = Math.min(answer, i);
                    }
                }
            }
            outAnswer.printLine(answer == Integer.MAX_VALUE ? -1 : answer + 1);
        }
    }

    private void dfs(int from, int cur, int len, Graph graph, int[] distance) {
        for (int i = graph.firstOutbound(cur); i != -1; i = graph.nextOutbound(i)) {
            if (graph.destination(i) == from)
                continue;
            distance[graph.destination(i)] = len;
            dfs(cur, graph.destination(i), len + 1, graph, distance);
        }
    }
}
