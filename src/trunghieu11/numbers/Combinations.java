package trunghieu11.numbers;

import static trunghieu11.numbers.IntegerUtils.generateFactorial;
import static trunghieu11.numbers.IntegerUtils.generateReverseFactorials;

/**
 * @author egor@egork.net
 */
public class Combinations {
    private final long mod;
    private final long[] factorial;
    private final long[] reverseFactorial;

    public Combinations(int length, long mod) {
        this.mod = mod;
        factorial = generateFactorial(length, mod);
        reverseFactorial = generateReverseFactorials(length, mod);
    }

    public long c(int n, int k) {
        if (k < 0 || k > n) {
            return 0;
        }
        return factorial[n] * reverseFactorial[k] % mod * reverseFactorial[n - k] % mod;
    }

    public long factorial(int n) {
        return factorial[n];
    }

    public long reverseFactorial(int n) {
        return reverseFactorial[n];
    }
}
