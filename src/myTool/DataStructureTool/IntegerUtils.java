package myTool.DataStructureTool;

import myTool.StandardTool.Pair;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 9/30/12
 * Time: 10:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class IntegerUtils {

    public static long[] generateReverse(int upTo, long module) {
        long[] result = new long[upTo];
        if (upTo > 1)
            result[1] = 1;
        for (int i = 2; i < upTo; i++)
            result[i] = (module - module / i * result[((int) (module % i))] % module) % module;
        return result;
    }

    public static long[] generateReverseFactorials(int upTo, long module) {
        long[] result = generateReverse(upTo, module);
        if (upTo > 0)
            result[0] = 1;
        for (int i = 1; i < upTo; i++)
            result[i] = result[i] * result[i - 1] % module;
        return result;
    }

    public static long[] generateFibonacci(long upTo) {
        int count = 0;
        long last = 0;
        long current = 1;
        while (current <= upTo) {
            long next = last + current;
            last = current;
            current = next;
            count++;
        }
        return generateFibonacci(count, -1);
    }

    public static long[] generateFibonacci(int count, long module) {
        long[] result = new long[count];
        if (module == -1) {
            if (count != 0)
                result[0] = 1;
            if (count > 1)
                result[1] = 1;
            for (int i = 2; i < count; i++)
                result[i] = result[i - 1] + result[i - 2];
        } else {
            if (count != 0)
                result[0] = 1 % module;
            if (count > 1)
                result[1] = 1 % module;
            for (int i = 2; i < count; i++)
                result[i] = (result[i - 1] + result[i - 2]) % module;
        }
        return result;
    }

    public static long power(long base, long exponent, long mod) {
        if (exponent == 0)
            return 1 % mod;
        long result = power(base, exponent >> 1, mod);
        result = result * result % mod;
        if ((exponent & 1) != 0)
            result = result * base % mod;
        return result;
    }

    public static int longCompare(long a, long b) {
        if (a < b)
            return -1;
        if (a > b)
            return 1;
        return 0;
    }

    public static long[] generateFactorial(int count, long module) {
        long[] result = new long[count];
        if (module == -1) {
            if (count != 0)
                result[0] = 1;
            for (int i = 1; i < count; i++)
                result[i] = result[i - 1] * i;
        } else {
            if (count != 0)
                result[0] = 1 % module;
            for (int i = 1; i < count; i++)
                result[i] = (result[i - 1] * i) % module;
        }
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

    public static List<Pair<Long, Integer>> factorize(long number) {
        List<Pair<Long, Integer>> result = new ArrayList<Pair<Long, Integer>>();
        for (long i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                int power = 0;
                do {
                    power++;
                    number /= i;
                } while (number % i == 0);
                result.add(Pair.makePair(i, power));
            }
        }
        if (number != 1)
            result.add(Pair.makePair(number, 1));
        return result;
    }

    public static List<Long> getDivisors(long number) {
        List<Pair<Long, Integer>> primeDivisors = factorize(number);
        return getDivisorsImpl(primeDivisors, 0, 1, new ArrayList<Long>());
    }

    private static List<Long> getDivisorsImpl(List<Pair<Long, Integer>> primeDivisors, int index, long current, List<Long> result)
    {
        if (index == primeDivisors.size()) {
            result.add(current);
            return result;
        }
        long p = primeDivisors.get(index).first;
        int power = primeDivisors.get(index).second;
        for (int i = 0; i <= power; i++) {
            getDivisorsImpl(primeDivisors, index + 1, current, result);
            current *= p;
        }
        return result;
    }

    public static long[][] generateBinomialCoefficients(int n, long module) {
        long[][] result = new long[n + 1][n + 1];
        if (module == 1)
            return result;
        for (int i = 0; i <= n; i++) {
            result[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                result[i][j] = result[i - 1][j - 1] + result[i - 1][j];
                if (result[i][j] >= module)
                    result[i][j] -= module;
            }
        }
        return result;
    }

    public static boolean isPrime(long number) {
        if (number < 2)
            return false;
        for (long i = 2; i * i <= number; i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }

    public static long binomialCoefficient(int n, int m, long mod) {
        if (m < 0 || m > n)
            return 0;
        if (2 * m > n)
            m = n - m;
        long result = 1;
        for (int i = n - m + 1; i <= n; i++)
            result = result * i % mod;
        return result * BigInteger.valueOf(factorial(m, mod)).modInverse(BigInteger.valueOf(mod)).longValue() % mod;
    }

    private static long factorial(int n, long mod) {
        long result = 1;
        for (int i = 2; i <= n; i++)
            result = result * i % mod;
        return result % mod;
    }

    public static long[] generatePowers(long base, int count, long mod) {
        long[] result = new long[count];
        if (count != 0)
            result[0] = 1 % mod;
        for (int i = 1; i < count; i++)
            result[i] = result[i - 1] * base % mod;
        return result;
    }

    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public static long gcd(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    BigInteger gcd(BigInteger a, BigInteger b) {
        a = a.abs();
        b = b.abs();
        BigInteger zero = new BigInteger("0");
        while (b.compareTo(zero) != 0) {
            BigInteger temp = a.mod(b);
            a = b;
            b = temp;
        }

        return a;
    }

    public static long[][] generateBinomialCoefficients(int n) {
        long[][] result = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            result[i][0] = 1;
            for (int j = 1; j <= i; j++)
                result[i][j] = result[i - 1][j - 1] + result[i - 1][j];
        }
        return result;
    }

    public static long power(long base, long exponent) {
        if (exponent == 0)
            return 1;
        long result = power(base, exponent >> 1);
        result = result * result;
        if ((exponent & 1) != 0)
            result = result * base;
        return result;
    }

    public static long nextPrime(long from) {
        if (from <= 2)
            return 2;
        from += 1 - (from & 1);
        while (!isPrime(from))
            from += 2;
        return from;
    }

    public static long reverse(long number, long module) {
        return power(number, module - 2, module);
    }

    public static int[] sieveEratosthenes(int n) {
        if(n <= 32){
            int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31 };
            for(int i = 0;i < primes.length;i++){
                if(n < primes[i]){
                    return Arrays.copyOf(primes, i);
                }
            }
            return primes;
        }

        int u = n + 32;
        double lu = Math.log(u);
        int[] ret = new int[(int) (u / lu + u / lu / lu * 1.5)];
        ret[0] = 2;
        int pos = 1;

        int[] isp = new int[(n + 1) / 32 / 2 + 1];
        int sup = (n + 1) / 32 / 2 + 1;

        int[] tprimes = { 3, 5, 7, 11, 13, 17, 19, 23, 29, 31 };
        for(int tp : tprimes){
            ret[pos++] = tp;
            int[] ptn = new int[tp];
            for(int i = (tp - 3) / 2;i < tp << 5;i += tp)
                ptn[i >> 5] |= 1 << (i & 31);
            for(int i = 0;i < tp;i++){
                for(int j = i;j < sup;j += tp)
                    isp[j] |= ptn[i];
            }
        }

        // 3,5,7
        // 2x+3=n
        int[] magic = { 0, 1, 23, 2, 29, 24, 19, 3, 30, 27, 25, 11, 20, 8, 4,
                13, 31, 22, 28, 18, 26, 10, 7, 12, 21, 17, 9, 6, 16, 5, 15, 14 };
        int h = n / 2;
        for(int i = 0;i < sup;i++){
            for(int j = ~isp[i];j != 0;j &= j - 1){
                int pp = i << 5 | magic[(j & -j) * 0x076be629 >>> 27];
                int p = 2 * pp + 3;
                if(p > n)
                    break;
                ret[pos++] = p;
                for(int q = pp;q <= h;q += p)
                    isp[q >> 5] |= 1 << (q & 31);
            }
        }

        return Arrays.copyOf(ret, pos);
    }

    public static boolean isSquare(int x) {
        int square = (int)Math.sqrt(x);
        return square * square == x;
    }

    public static boolean isSquare(long x) {
        long square = (long)Math.sqrt(x);
        return square * square == x;
    }
}
