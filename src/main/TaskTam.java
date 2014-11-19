package main;

import myTool.DataStructureTool.IntegerUtils;
import myTool.IOTool.InputReader;
import myTool.IOTool.OutputWriter;
import myTool.StandardTool.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskTam {
    public static int MAXN = 100001;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] divisorTable = IntegerUtils.generateDivisorTable(MAXN);
        int[] primes = IntegerUtils.generatePrimes(MAXN);
        int testCount = in.readInt();
        for (int test = 0; test < testCount; test++) {
            int count = in.readInt();
            int mod = in.readInt();
            long[] factor = new long[MAXN];
            for (int i = count; i > (count + 1) / 2; i--) {
                int cur = i;
                int remain = count - i + 1;
                int increase = cur;
                int decrease = remain;

                while (cur > 1) {
                    factor[divisorTable[cur]] += increase;
                    cur /= divisorTable[cur];
                }

                while (remain > 1) {
                    factor[divisorTable[remain]] -= decrease;
                    remain /= divisorTable[remain];
                }
            }

            long[] answer = new long[count + 1];
            Arrays.fill(answer, 1);

            int mid = (count + 1) / 2;
            for (int x : primes) {
                answer[mid] *= IntegerUtils.power(x, factor[x], mod);
                answer[mid] %= mod;
                if (answer[mid] < 0)
                    answer[mid] += mod;
            }

            answer[count - mid] = answer[mid];

            for (int i = mid + 1; i <= count; i++) {
                answer[i] = answer[i - 1];

                List<Pair<Long, Integer>> curFact = factorize(count - i + 1);

                for (Pair<Long, Integer> x : curFact) {
                    answer[i] *= IntegerUtils.power(x.first, x.second, mod);
                    answer[i] %= mod;
                    if (answer[i] < 0)
                        answer[i] += mod;
                }

                answer[count - i] = answer[i];
            }

            int queryCount = in.readInt();
            for (int i = 0; i < queryCount; i++) {
                int idx = in.readInt();
                out.printLine(answer[idx]);
            }
        }
    }

    private List<Pair<Long, Integer>> factorize(long number) {
        List<Pair<Long, Integer>> result = new ArrayList<Pair<Long, Integer>>();
        int exponent = (int)number;
        for (long i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                int power = 0;
                do {
                    power += exponent;
                    number /= i;
                } while (number % i == 0);
                result.add(Pair.makePair(i, power));
            }
        }
        if (number != 1)
            result.add(Pair.makePair(number, exponent));
        return result;
    }
}
