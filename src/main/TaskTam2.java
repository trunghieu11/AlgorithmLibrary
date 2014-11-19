package main;

import myTool.DataStructureTool.IntegerUtils;
import myTool.IOTool.InputReader;
import myTool.IOTool.OutputWriter;

import java.util.Arrays;

public class TaskTam2 {
    static int MAXN = 501;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] divisorTable = IntegerUtils.generateDivisorTable(MAXN);
        int[] primes = IntegerUtils.generatePrimes(MAXN);
        int[][] above = new int[MAXN][MAXN];
        for (int i = 2; i < MAXN; i++) {
            above[i] = Arrays.copyOf(above[i - 1], MAXN);
            int cur = i;
            while (cur > 1) {
                above[i][divisorTable[cur]] += i;
                cur /= divisorTable[cur];
            }
        }

        int testCount = in.readInt();
        for (int test = 0; test < testCount; test++) {
            int count = in.readInt();
            int mod = in.readInt();

            for (int i = 2; i < count; i++) {
                int value = i;

            }

            int queryCount = in.readInt();
            for (int query = 0; query < queryCount; query++) {
                long answer = 1;
                int value = in.readInt();int remain = count - value;
                for (int p : primes) {
                    answer *= IntegerUtils.power(p, above[count][p] - above[value][p] - above[remain][p], mod);
                    answer %= mod;
                }
                out.printLine(answer);
            }

            out.printLine();
        }
    }
}
