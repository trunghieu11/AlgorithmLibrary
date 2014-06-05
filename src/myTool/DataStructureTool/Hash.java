package myTool.DataStructureTool;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/3/12
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Hash {
    int size = 100003;
    int shift = 30001;
    boolean[] occupied = new boolean[size];
    long[] key = new long[size];
    int[] firstValue = new int[size];
    int[] secondValue = new int[size];

    public void add(long key) {
        int index = (int) (key % size);
        if (index < 0)
            index += size;
        while (occupied[index] && this.key[index] != key) {
            index += shift;
            if (index >= size)
                index -= size;
        }
        occupied[index] = true;
        this.key[index] = key;
    }

    public int addFirstGetSecond(long key) {
        int index = (int) (key % size);
        if (index < 0)
            index += size;
        while (occupied[index] && this.key[index] != key) {
            index += shift;
            if (index >= size)
                index -= size;
        }
        if (!occupied[index])
            return 0;
        firstValue[index]++;
        return secondValue[index];
    }

    public int addSecondGetFirst(long key) {
        int index = (int) (key % size);
        if (index < 0)
            index += size;
        while (occupied[index] && this.key[index] != key) {
            index += shift;
            if (index >= size)
                index -= size;
        }
        if (!occupied[index])
            return 0;
        secondValue[index]++;
        return firstValue[index];
    }
}
