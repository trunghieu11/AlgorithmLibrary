package myTool.TestTool;

import myTool.StandardTool.MiscUtils;

import java.util.Random;

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
}
