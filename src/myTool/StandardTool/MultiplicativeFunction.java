package myTool.StandardTool;

import myTool.DataStructureTool.IntegerUtils;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 8/24/13
 * Time: 9:11 AM
 * To change this template use File | Settings | File Templates.
 */
abstract public class MultiplicativeFunction {
    public static final MultiplicativeFunction MOBIUS = new MultiplicativeFunction() {
        @Override
        protected long value(long p, int exponent, long power) {
            return exponent == 1 ? -1 : 0;
        }
    };

    protected abstract long value(long p, int exponent, long power);

    public long[] calculateUpTo(int upTo) {
        int[] divisor = IntegerUtils.generateDivisorTable(upTo);
        long[] result = new long[upTo];
        result[1] = 1;
        for (int i = 2; i < upTo; i++) {
            int iDivided = i;
            int exponent = 0;
            do {
                iDivided /= divisor[i];
                exponent++;
            } while (iDivided % divisor[i] == 0);
            result[i] = result[iDivided] * value(divisor[i], exponent, i / iDivided);
        }
        return result;
    }
}
