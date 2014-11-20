package main;

import myTool.DataStructureTool.IntervalTree;
import myTool.DataStructureTool.SumIntervalTree;
import myTool.IOTool.OutputWriter;
import myTool.StandardTool.MiscUtils;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class ChefAndChuruTestCase {
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
        int testCount = 1;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);

            int count = random.nextInt(1000) + 1;
            int[] A = new int[count];
            for (int i = 0; i < count; i++) {
                A[i] = random.nextInt(10000);
            }
            int[] from = new int[count];
            int[] to = new int[count];
            for (int i = 0; i < count; i++) {
                to[i] = random.nextInt(count) + 1;
                from[i] = random.nextInt(to[i]) + 1;
            }
            int queryCount = random.nextInt(1000);
            int[] type= new int[queryCount];
            int[] x = new int[queryCount];
            int[] y = new int[queryCount];

            for (int query = 0; query < queryCount; query++) {
                type[query] = random.nextInt(2) + 1;
                x[query] = random.nextInt(count) + 1;
                y[query] = random.nextInt(count) + 1;
                if (type[query] == 2 && x[query] > y[query]) {
                    int temp = x[query];
                    x[query] = y[query];
                    y[query] = temp;
                }
            }

            long[] answer = solve(count, A, from, to, queryCount, type, x, y);

            out.printLine(count);
            out.printLine(A);
            for (int i = 0; i < count; i++) {
                out.printLine(from[i], to[i]);
            }
            out.printLine(queryCount);
            for (int i = 0; i < queryCount; i++) {
                out.printLine(type[i], x[i], y[i]);
            }

            for (long val : answer)
                outAnswer.printLine(val);

            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }

    private long[] solve(int count, int[] A, int[] from, int[] to, int queryCount, int[] type, int[] x, int[] y) {
        long[] answer = new long[queryCount];
        IntervalTree tree = new SumIntervalTree(count);
        MiscUtils.decreaseByOne(from, to);
        for (int i = 0; i < count; i++) {
            tree.update(i, i, A[i]);
        }
        for (int query = 0; query < queryCount; query++) {
            if (type[query] == 1) {
                tree.update(x[query] - 1, x[query] - 1, y[query]);
            }
            else {
                for (int i = x[query] - 1; i < y[query]; i++) {
                    answer[query] += tree.query(from[i], to[i]);
                }
            }
        }
        return answer;
    }
}
