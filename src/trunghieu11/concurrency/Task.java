package trunghieu11.concurrency;

import trunghieu11.utils.io.InputReader;
import trunghieu11.utils.io.OutputWriter;

/**
 * @author egor@egork.net
 */
public interface Task {
    public void read(InputReader in);

    public void solve();

    public void write(OutputWriter out, int testNumber);
}
