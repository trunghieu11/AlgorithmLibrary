package main;

import myTool.DataStructureTool.IntervalTree;
import myTool.DataStructureTool.SumIntervalTree;
import myTool.IOTool.IOUtils;
import myTool.IOTool.InputReader;
import myTool.IOTool.OutputWriter;
import myTool.StandardTool.MiscUtils;

public class ChefAndChuru {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        IntervalTree tree = new SumIntervalTree(count);
        int[] A = IOUtils.readIntArray(in, count);
        for (int i = 0; i < count; i++) {
            tree.update(i, i, A[i]);
        }

        int[] from = new int[count];
        int[] to = new int[count];
        IOUtils.readIntArrays(in, from, to);
        MiscUtils.decreaseByOne(from, to);

        // calculate value of each function
        long[] functionVal = new long[count];
        for (int i = 0; i < count; i++) {
            functionVal[i] = tree.query(from[i], to[i]);
        }

        // calculate bag array
        int step = (int)Math.sqrt((double)count);
        int segmentCount = step + (count % step == 0 ? 0 : 1);
        long[] segmentVal = new long[segmentCount];

        for (int i = 0; i < segmentCount; i++) {
            for (int j = i * step; j < Math.min((i + 1) * step, count); j++) {
                segmentVal[i] += functionVal[j];
            }
        }

        // Find the numbers of exist of each position in each step
        int[][] existCount = new int[segmentCount][count];
        for (int i = 0; i < segmentCount; i++) {
            int[] idx = new int[count + 1];
            for (int j = i * step; j < Math.min((i + 1) * step, count); j++) {
                idx[from[j]]++;
                idx[to[j] + 1]--;
            }
            for (int j = 1; j < count; j++) {
                idx[j] += idx[j - 1];
            }
            for (int j = 0; j < count; j++) {
                existCount[i][j] = idx[j];
            }
        }

        // calculate for each query
        int queryCount = in.readInt();
        for (int query = 0; query < queryCount; query++) {
            int type = in.readInt();
            int x = in.readInt();
            int y = in.readInt();

            if (type == 1) {
                x--;
                for (int i = 0; i < segmentCount; i++) {
                    segmentVal[i] += (y - A[x]) * existCount[i][x];
                }
                tree.update(x, x, y - A[x]);
                A[x] = y;
            }
            else {
                x--;
                y--;
                int i = x;
                long answer = 0;
                while (i <= y) {
                    if (i % step == 0 && (i + step - 1) <= y) {
                        answer += segmentVal[i / step];
                        i += step;
                    }
                    else {
                        answer += tree.query(from[i], to[i]);
                        i++;
                    }
                }

                out.printLine(answer);
            }
        }
    }
}
