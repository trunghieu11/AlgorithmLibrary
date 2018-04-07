package main;

import trunghieu11.io.IOUtils;
import trunghieu11.utils.io.InputReader;
import trunghieu11.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskTroubleSort {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        out.print("Case #" + testNumber + ": ");
        int count = in.readInt();
        int[] A = IOUtils.readIntArray(in, count);

        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            if (i % 2 == 0) {
                even.add(A[i]);
            } else {
                odd.add(A[i]);
            }
        }

        Collections.sort(odd);
        Collections.sort(even);

        for (int i = 0; i < count; i++) {
            if (i % 2 == 0) {
                A[i] = even.get(i / 2);
            } else {
                A[i] = odd.get(i / 2);
            }
        }

//        out.printLine(A);

        for (int i = 1; i < count; i++) {
            if (A[i - 1] > A[i]) {
                out.printLine(i - 1);
                return;
            }
        }

        out.printLine("OK");
    }
}
