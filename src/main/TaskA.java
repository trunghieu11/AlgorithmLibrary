package main;

import myTool.IOTool.InputReader;
import myTool.IOTool.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int k = in.readInt();
        char[] s = in.readString().toCharArray();

        if (count == 1) {
            out.printLine("NO");
            return;
        }

        int[] A = new int[count];
        for (int i = 0; i < count; i++) {
            A[i] = s[i] - 'A';
        }

        for (int i = 0; i < k; i++) {
            int tmp = A[count - 1];
            A[count - 1] = i;
            if (i <= tmp) {
                A[count - 2]++;
            }
        }
    }
}
