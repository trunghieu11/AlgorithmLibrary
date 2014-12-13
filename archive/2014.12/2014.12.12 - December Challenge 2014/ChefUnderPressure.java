package main;





import myTool.DataStructureTool.ArrayUtils;
import myTool.GraphTool.GraphUtils;
import myTool.GraphTool3.BidirectionalGraph;
import myTool.GraphTool3.Graph;
import myTool.GraphTool3.LCA;
import myTool.IOTool.IOUtils;
import myTool.IOTool.InputReader;
import myTool.IOTool.OutputWriter;
import myTool.StandardTool.MiscUtils;

import java.util.Arrays;

public class ChefUnderPressure {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int productCount = in.readInt();
        int capital = in.readInt() - 1;

        Graph graph = new BidirectionalGraph(count, count - 1);
        for (int i = 0; i < count - 1; i++) {
            graph.addSimpleEdge(in.readInt() - 1, in.readInt() - 1);
        }

        int[] products = IOUtils.readIntArray(in, count);
        MiscUtils.decreaseByOne(products);

        int[][] low = new int[count][productCount];
        int[][] same = new int[count][productCount];

        ArrayUtils.fill(low, Integer.MAX_VALUE);
        ArrayUtils.fill(same, Integer.MAX_VALUE);

        for (int i = 0; i < count; i++) {
            low[capital][products[i]] = Math.min(low[capital][products[i]], i);
        }

        dfsLow(-1, capital, graph, low, products, productCount);
        dfsSame(-1, capital, graph, low, same, productCount);

        int queryCount = in.readInt();
        for (int query = 0; query < queryCount; query++) {
            int cur = in.readInt() - 1;
            int want = in.readInt() - 1;
            if (low[cur][want] < Integer.MAX_VALUE)
                out.printLine(low[cur][want] + 1);
            else if (same[cur][want] < Integer.MAX_VALUE)
                out.printLine(same[cur][want] + 1);
            else
                out.printLine(-1);
        }
    }

    private void dfsLow(int from, int cur, Graph graph, int[][] low, int[] products, int productCount) {
        for (int i = graph.firstOutbound(cur); i != -1; i = graph.nextOutbound(i)) {
            if (graph.destination(i) == from)
                continue;
            dfsLow(cur, graph.destination(i), graph, low, products, productCount);
            for (int j = 0; j < productCount; j++) {
                low[cur][j] = Math.min(low[cur][j], low[graph.destination(i)][j]);
            }
        }
        low[cur][products[cur]] = Math.min(low[cur][products[cur]], cur);
    }

    private void dfsSame(int from, int cur, Graph graph, int[][] low, int[][] same, int productCount) {
        for (int i = graph.firstOutbound(cur); i != -1; i = graph.nextOutbound(i)) {
            if (graph.destination(i) == from)
                continue;
            for (int j = 0; j < productCount; j++) {
                if (same[graph.destination(i)][j] == Integer.MAX_VALUE) {
                    if (low[cur][j] == Integer.MAX_VALUE)
                        same[graph.destination(i)][j] = same[cur][j];
                    else
                        same[graph.destination(i)][j] = low[cur][j];
                }
            }
            dfsSame(cur, graph.destination(i), graph, low, same, productCount);
        }
    }
}
