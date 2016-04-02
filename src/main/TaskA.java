package main;

import com.sun.deploy.util.ArrayUtil;
import trunghieu11.io.IOUtils;
import trunghieu11.misc.ArrayUtils;
import trunghieu11.utils.io.InputReader;
import trunghieu11.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        char[][] table = IOUtils.readTable(in, count, count);

        int answer = 0;
        for (int i = 0; i < count; i++) {
            int total = ArrayUtils.count(table[i], 'C');
            answer += total * (total - 1) / 2;
            total = ArrayUtils.colu
        }
    }
}
