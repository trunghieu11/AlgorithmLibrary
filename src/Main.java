import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Nguyen Trung Hieu - vuondenthanhcong11@gmail.com
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        TaskTam solver = new TaskTam();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class TaskTam {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            out.printLine("Case #" + testNumber + ":");
            double x = in.readDouble();

            if (x <= 1.414213) {
                double delta = 8 - 4 * x * x;
                double a1 = 0;
                double a2 = 0;

                if (Math.abs(delta) <= 0.000001) {
                    a1 = a2 = x / 2;
                } else {
                    a1 = (2 * x + Math.sqrt(delta)) / 4;
                    a2 = (2 * x - Math.sqrt(delta)) / 4;
                }

                double b1 = x - a1;
                double b2 = x - a2;

                double angel = Math.atan2(a1, b1);

                double updateX = 0.5 * (Math.sin(angel));
                double updateY = 0.5 * (Math.cos(angel));

                out.printFormat("%.7f %.7f %.7f\n", updateX, updateY, 0.0);
                out.printFormat("%.7f %.7f %.7f\n", -updateY, updateX, 0.0);
                out.printFormat("%.7f %.7f %.7f\n", 0.0, 0.0, 0.5);
            } else {
                double alpha = Math.acos(x / 2);

                double angel = alpha * 180 / Math.PI;

                double bx = 0;
                double by = 0.5 * Math.cos(alpha);
                double bz = 0.5 * Math.sin(alpha);

                double rx = 0.5 * (Math.sqrt(2) / 2) * Math.sin(alpha);
                double ry = 0.5 * (Math.sqrt(2) / 2) * Math.cos(alpha);
                double rz = Math.sqrt(x * x - (0.5 * Math.cos(alpha)) * (0.5 * Math.cos(alpha)));

                double gx = -ry;
                double gy = rx;
                double gz = -rz;

                out.printFormat("%.7f %.7f %.7f\n", rx, ry, rz);
                out.printFormat("%.7f %.7f %.7f\n", gx, gy, gz);
                out.printFormat("%.7f %.7f %.7f\n", bx, by, bz);
            }
        }

    }

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(objects[i]);
            }
        }

        public void printLine(Object... objects) {
            print(objects);
            writer.println();
        }

        public void printFormat(String format, Object... objects) {
            writer.printf(format, objects);
        }

        public void close() {
            writer.close();
        }

    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private InputReader.SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String readString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                if (Character.isValidCodePoint(c)) {
                    res.appendCodePoint(c);
                }
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return isWhitespace(c);
        }

        public static boolean isWhitespace(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public double readDouble() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.') {
                if (c == 'e' || c == 'E') {
                    return res * Math.pow(10, readInt());
                }
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E') {
                        return res * Math.pow(10, readInt());
                    }
                    if (c < '0' || c > '9') {
                        throw new InputMismatchException();
                    }
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        public String next() {
            return readString();
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }
}

