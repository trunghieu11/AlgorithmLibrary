package myTool.StandardTool;

import myTool.DataStructureTool.IntegerUtils;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 7/31/13
 * Time: 11:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class Rational implements Comparable<Rational> {
    public static final Rational MAX_VALUE = new Rational(Integer.MAX_VALUE, 1);

    public final long numerator;
    public final long denominator;

    public Rational(long numerator, long denominator) {
        if (denominator == 0)
            throw new IllegalArgumentException();
        long gcd = IntegerUtils.gcd(Math.abs(numerator), Math.abs(denominator));
        if (denominator > 0) {
            this.numerator = numerator / gcd;
            this.denominator = denominator / gcd;
        } else {
            this.numerator = -numerator / gcd;
            this.denominator = -denominator / gcd;
        }
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

    public int compareTo(Rational other) {
        return IntegerUtils.longCompare(numerator * other.denominator, denominator * other.numerator);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rational rational = (Rational) o;

        if (denominator != rational.denominator) return false;
        if (numerator != rational.numerator) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (numerator ^ (numerator >>> 32));
        result = 31 * result + (int) (denominator ^ (denominator >>> 32));
        return result;
    }

    public Rational add(Rational other) {
        return new Rational(numerator * other.denominator + denominator * other.numerator,
                denominator * other.denominator);
    }

    public Rational subtract(Rational other) {
        return new Rational(numerator * other.denominator - denominator * other.numerator,
                denominator * other.denominator);
    }

    public Rational divide(Rational other) {
        return new Rational(numerator * other.denominator, other.numerator * denominator);
    }

    public Rational multiply(Rational other) {
        return new Rational(numerator * other.numerator, other.denominator * denominator);
    }
}
