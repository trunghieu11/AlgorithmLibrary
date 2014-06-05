package myTool.DataStructureTool;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/3/12
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class IntervalTree {
    protected int size;
    public int[] value;

    private int[] left;
    private int[] right;

    public IntervalTree(int[] array) {
        this.size = array.length;
        int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
        value = new int[nodeCount];
        init(array);
    }

    public void init(int[] array) {
        init(0, 0, size - 1, array);
    }

    private void init(int root, int left, int right, int[] array) {
        if (left == right)
            value[root] = array[left];
        else {
            int middle = (left + right) >> 1;
            init(2 * root + 1, left, middle, array);
            init(2 * root + 2, middle + 1, right, array);
            value[root] = Math.max(value[2 * root + 1], value[2 * root + 2]);
        }
    }

    public int query(int from, int to) {
        return query(0, 0, size - 1, from, to);
    }

    private int query(int root, int left, int right, int from, int to) {
        if (left > to || right < from)
            return 0;
        if (left >= from && right <= to)
            return value[root];
        int middle = (left + right) >> 1;
        int leftResult = query(2 * root + 1, left, middle, from, to);
        int rightResult = query(2 * root + 2, middle + 1, right, from, to);
        return Math.max(leftResult, rightResult);
    }



    public IntervalTree(int size) {
        left = new int[4 * size];
        right = new int[4 * size];
        value = new int[4 * size];
        init(0, size, 0);
    }

    private void init(int left, int right, int root) {
        this.left[root] = left;
        this.right[root] = right;
        if (right - left > 1) {
            init(left, (left + right) / 2, 2 * root + 1);
            init((left + right) / 2, right, 2 * root + 2);
        }
    }
    public int lowerBound(int value) {
        return lowerBound(value, 0);
    }

    private int lowerBound(int value, int root) {
        if (right[root] - left[root] == 1)
            return left[root];
        if (this.value[2 * root + 1] >= value)
            return lowerBound(value, 2 * root + 1);
        return lowerBound(value - this.value[2 * root + 1], 2 * root + 2);
    }

    public void putValue(int position, int value) {
        putValue(position, value, 0);
    }

    private void putValue(int position, int value, int root) {
        if (left[root] > position || right[root] <= position)
            return;
        this.value[root] += value;
        if (right[root] - left[root] > 1) {
            putValue(position, value, 2 * root + 1);
            putValue(position, value, 2 * root + 2);
        }
    }

    //Other Interval Tree

    protected int[][] value2;
    protected int[] delta;
    int[] result = new int[26];

    public IntervalTree(char[] s) {
        this.size = s.length;
        int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
        value2 = new int[nodeCount][26];
        delta = new int[nodeCount];
        init(s);
    }

    public void init(char[] s) {
        init(0, 0, size - 1, s);
    }

    private void init(int root, int left, int right, char[] s) {
        if (left == right) {
            value2[root][s[left] - 'a'] = 1;
        } else {
            int middle = (left + right) >> 1;
            init(2 * root + 1, left, middle, s);
            init(2 * root + 2, middle + 1, right, s);
            for (int i = 0; i < 26; i++)
                value2[root][i] = value2[2 * root + 1][i] + value2[2 * root + 2][i];
        }
    }

    public void setSingle(int index, int value) {
        setSingle(0, index, value, 0, size - 1);
    }

    private void setSingle(int root, int index, int value, int left, int right) {
        if (index < left || index > right)
            return;
        if (left == right) {
            Arrays.fill(this.value2[root], 0);
            this.value2[root][value] = 1;
            return;
        }
        delta[root] = 0;
        int middle = (left + right) >> 1;
        setSingle(2 * root + 1, index, value, left, middle);
        setSingle(2 * root + 2, index, value, middle + 1, right);
        for (int i = 0; i < 26; i++)
            this.value2[root][i] = this.value2[2 * root + 1][i] + this.value2[2 * root + 2][i];
    }

    public void update(int from, int to, int delta) {
        update(0, 0, size - 1, from, to, delta);
    }

    private void update(int root, int left, int right, int from, int to, int delta) {
        if (left > to || right < from)
            return;
        if (left >= from && right <= to) {
            this.delta[root] = delta;
            int skip = left - from;
            Arrays.fill(value2[root], 0);
            int total = right - left + 1;
            if (delta == 1) {
                for (int i = 0; i < 26; i++) {
                    int curSkip = Math.min(skip, result[i] >> 1);
                    skip -= curSkip;
                    int curAdd = Math.min(total, (result[i] >> 1) - curSkip);
                    value2[root][i] = curAdd;
                    total -= curAdd;
                }
            } else if (delta == -1) {
                for (int i = 25; i >= 0; i--) {
                    int curSkip = Math.min(skip, result[i] >> 1);
                    skip -= curSkip;
                    int curAdd = Math.min(total, (result[i] >> 1) - curSkip);
                    value2[root][i] = curAdd;
                    total -= curAdd;
                }
            }
            return;
        }
        this.delta[root] = 0;
        int middle = (left + right) >> 1;
        update(2 * root + 1, left, middle, from, to, delta);
        update(2 * root + 2, middle + 1, right, from, to, delta);
        for (int i = 0; i < 26; i++)
            value2[root][i] = value2[2 * root + 1][i] + value2[2 * root + 2][i];
    }

    public int[] query2(int from, int to) {
        Arrays.fill(result, 0);
        query2(0, 0, size - 1, from, to);
        return result;
    }

    private void query2(int root, int left, int right, int from, int to) {
        if (left > to || right < from)
            return;
        if (left >= from && right <= to) {
            for (int i = 0; i < 26; i++)
                result[i] += value2[root][i];
            return;
        }
        int middle = (left + right) >> 1;
        if (delta[root] != 0) {
            delta[2 * root + 1] = delta[root];
            delta[2 * root + 2] = delta[root];
            int length = middle - left + 1;
            if (delta[root] == 1) {
                for (int i = 0; i < 26; i++) {
                    int cur = Math.min(length, value2[root][i]);
                    length -= cur;
                    value2[2 * root + 1][i] = cur;
                    value2[2 * root + 2][i] = value2[root][i] - cur;
                }
            } else {
                for (int i = 25; i >= 0; i--) {
                    int cur = Math.min(length, value2[root][i]);
                    length -= cur;
                    value2[2 * root + 1][i] = cur;
                    value2[2 * root + 2][i] = value2[root][i] - cur;
                }
            }
            delta[root] = 0;
        }
        query2(2 * root + 1, left, middle, from, to);
        query2(2 * root + 2, middle + 1, right, from, to);
    }

    //Tree 3

    int[] count4, count7, count47, count74;
    boolean[] switched;

    public IntervalTree(char[] sequence, boolean treeType3) {
        int arraySize = Integer.highestOneBit(sequence.length) << 2;
        left = new int[arraySize];
        right = new int[arraySize];
        count4 = new int[arraySize];
        count47 = new int[arraySize];
        count7 = new int[arraySize];
        count74 = new int[arraySize];
        switched = new boolean[arraySize];
        initTree3(0, 0, sequence.length, sequence);
    }

    private void initTree3(int root, int left, int right, char[] sequence) {
        this.left[root] = left;
        this.right[root] = right;
        if (right - left == 1) {
            count47[root] = count74[root] = 1;
            if (sequence[left] == '4')
                count4[root] = 1;
            else
                count7[root] = 1;
        } else {
            initTree3(2 * root + 1, left, (left + right) >> 1, sequence);
            initTree3(2 * root + 2, (left + right) >> 1, right, sequence);
            update3(root);
        }
    }

    public void switchState(int from, int to) {
        switchState(0, from, to);
    }

    private void switchState(int root, int from, int to) {
        if (left[root] >= to || right[root] <= from)
            return;
        if (left[root] >= from && right[root] <= to) {
            switched[root] ^= true;
            return;
        }
        if (switched[root]) {
            switched[2 * root + 1] ^= true;
            switched[2 * root + 2] ^= true;
            switched[root] = false;
        }
        switchState(2 * root + 1, from, to);
        switchState(2 * root + 2, from, to);
        update3(root);
    }

    public int query3() {
        return get47(0);
    }

    private void update3(int root) {
        count4[root] = get4(2 * root + 1) + get4(2 * root + 2);
        count7[root] = get7(2 * root + 1) + get7(2 * root + 2);
        count47[root] = Math.max(get4(2 * root + 1) + get47(2 * root + 2),
                get47(2 * root + 1) + get7(2 * root + 2));
        count74[root] = Math.max(get7(2 * root + 1) + get74(2 * root + 2),
                get74(2 * root + 1) + get4(2 * root + 2));
    }

    private int get4(int root) {
        if (switched[root])
            return count7[root];
        else
            return count4[root];
    }

    private int get7(int root) {
        if (!switched[root])
            return count7[root];
        else
            return count4[root];
    }

    private int get47(int root) {
        if (switched[root])
            return count74[root];
        else
            return count47[root];
    }

    private int get74(int root) {
        if (switched[root])
            return count47[root];
        else
            return count74[root];
    }
}
