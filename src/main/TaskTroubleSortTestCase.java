package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.chelper.tester.TestCase;

import trunghieu11.utils.io.OutputWriter;

import java.util.*;
import java.io.StringWriter;

public class TaskTroubleSortTestCase {
    private static final int MAXN = 100;

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

            int count = random.nextInt(MAXN) + 3;
            int[] A = new int[count];
            for (int i = 0; i < count; i++) {
                A[i] = random.nextInt(MAXN);
            }

            out.printLine(1);
            out.printLine(count);
            out.printLine(A);

            boolean done = false;
            while (done == false) {
                done = true;
                for (int i = 0; i < count - 2; i++) {
                    if (A[i] > A[i + 2]) {
                        done = false;
                        int tmp = A[i];
                        A[i] = A[i + 2];
                        A[i + 2] = tmp;
                    }
                }
            }

            boolean isOK = true;
            outAnswer.print("Case #1: ");
            for (int i = 0; i < count - 1; i++) {
                if (A[i] > A[i + 1]) {
                    outAnswer.printLine(i);
                    isOK = false;
                    break;
                }
            }

            if (isOK) {
                outAnswer.printLine("OK");
            }

            out.printLine(A);
            Arrays.sort(A);
            out.printLine(A);

            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
