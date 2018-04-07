package main;

import trunghieu11.utils.io.InputReader;
import trunghieu11.utils.io.OutputWriter;

import java.util.Scanner;

public class TaskGopher {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int testCase = in.readInt();
        for (int test = 0; test < testCase; test++) {
            int[][] table = new int[4][800];
            int area = in.readInt();
            int i = 2;
            int j = 2;

            while (true) {
                out.printLine(i, j);
                int x = in.readInt();
                int y = in.readInt();

                if (x == 0 && y == 0) {
                    break;
                }

                if (x == -1 && y == -1) {
                    break;
                }

                table[x][y] = 1;

                while (true) {
                    boolean goRight = true;
                    for (int k = 1; k <= 3; k++) {
                        if (table[k][j - 1] != 1) {
                            goRight = false;
                        }
                    }

                    if (goRight && (j + 1) * 3 < area) {
                        j++;
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
