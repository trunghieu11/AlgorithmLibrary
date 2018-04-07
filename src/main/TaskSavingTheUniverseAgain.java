package main;

import trunghieu11.utils.io.InputReader;
import trunghieu11.utils.io.OutputWriter;

public class TaskSavingTheUniverseAgain {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        out.print("Case #" + testNumber + ": ");
        long shield = in.readLong();
        char[] command = in.readString().toCharArray();
        int count = command.length;
        int answer = 0;

        if (calcDamage(command) <= shield) {
            out.printLine(0);
            return;
        }

        for (int i = 0; i < count * count; i++) {
            for (int j = count - 1; j >= 1; j--) {
                if (command[j] == 'S' && command[j - 1] == 'C') {
                    char tmp = command[j];
                    command[j] = command[j - 1];
                    command[j - 1] = tmp;
                    answer++;
                    break;
                }
            }

            if (calcDamage(command) <= shield) {
                break;
            }
        }

        if (calcDamage(command) <= shield) {
            out.printLine(answer);
        } else {
            out.printLine("IMPOSSIBLE");
        }
    }

    private long calcDamage(char[] command) {
        long answer = 0;
        long curDamage = 1;
        for (char x : command) {
            if (x == 'C') {
                curDamage *= 2;
            } else {
                answer += curDamage;
            }
        }
        return answer;
    }
}
