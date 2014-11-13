import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.Comparator;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.List;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.math.BigInteger;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author Nguyen Trung Hieu - vuondenthanhcong11@gmail.com
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		OutputWriter out = new OutputWriter(outputStream);
		TaskTam solver = new TaskTam();
		solver.solve(1, in, out);
		out.close();
	}
}

class TaskTam {
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

class InputReader {
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int readInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public boolean isSpaceChar(int c) {
        if (filter != null)
            return filter.isSpaceChar(c);
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
    }

}

class OutputWriter {
    private final PrintWriter writer;

    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }

    public OutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void print(Object...objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(objects[i]);
        }
    }

    public void printLine(Object...objects) {
        print(objects);
        writer.println();
    }

    public void close() {
        writer.close();
    }

}

class IntegerUtils {

    public static long power(long base, long exponent, long mod) {
        if (exponent == 0)
            return 1 % mod;
        long result = power(base, exponent >> 1, mod);
        result = result * result % mod;
        if ((exponent & 1) != 0)
            result = result * base % mod;
        return result;
    }

    public static int[] generatePrimes(int upTo) {
        boolean[] isPrime = generatePrimalityTable(upTo);
        List<Integer> primes = new ArrayList<Integer>();
        for (int i = 0; i < upTo; i++) {
            if (isPrime[i])
                primes.add(i);
        }
        return CollectionUtils.toArray(primes);
    }

    public static boolean[] generatePrimalityTable(int upTo) {
        boolean[] isPrime = new boolean[upTo];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i < upTo; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < upTo; j += i)
                    isPrime[j] = false;
            }
        }
        return isPrime;
    }

    public static int[] generateDivisorTable(int upTo) {
        int[] divisor = new int[upTo];
        for (int i = 1; i < upTo; i++)
            divisor[i] = i;
        for (int i = 2; i * i < upTo; i++) {
            if (divisor[i] == i) {
                for (int j = i * i; j < upTo; j += i)
                    divisor[j] = i;
            }
        }
        return divisor;
    }

}

class Pair<U, V> implements Comparable<Pair<U, V>> {

    public final U first;
    public final V second;

    public static<U, V> Pair<U, V> makePair(U first, V second) {
        return new Pair<U, V>(first, second);
    }

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        return !(first != null ? !first.equals(pair.first) : pair.first != null) && !(second != null ? !second.equals(pair.second) : pair.second != null);

    }

    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }

    public String toString() {
        return "(" + first + "," + second + ")";
    }

    public int compareTo(Pair<U, V> o) {
        int value = ((Comparable<U>)first).compareTo(o.first);
        if (value != 0)
            return value;
        return ((Comparable<V>)second).compareTo(o.second);
    }
}

class CollectionUtils {

    public static int[] toArray(Collection<Integer> collection) {
        int[] array = new int[collection.size()];
        int index = 0;
        for (int element : collection)
            array[index++] = element;
        return array;
    }

}

