package main;

import trunghieu11.utils.io.InputReader;
import trunghieu11.utils.io.OutputWriter;

import java.util.Random;

public class TaskNumberGuessing {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        out.print("Case #" + testNumber + ": ");
        int a = in.readInt();
        int b = in.readInt();
        int count = in.readInt();
        Random random = new Random(11);

        while (true) {
            int guessValue = random.nextInt(b - a) + a + 1;
            out.printLine(guessValue);

            String result = in.readString();
            if (result.equals("CORRECT") || result.equals("WRONG_ANSWER")) {
                break;
            }

            if (result.equals("TOO_SMALL")) {
                a = guessValue;
            }

            if (result.equals("TOO_BIG")) {
                b = guessValue - 1;
            }
        }
    }
}
