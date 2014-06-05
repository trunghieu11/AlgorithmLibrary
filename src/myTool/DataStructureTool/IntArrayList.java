package myTool.DataStructureTool;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 3/1/13
 * Time: 8:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class IntArrayList extends IntList {
    private int[] array;
    private int size;

    public IntArrayList() {
        this(10);
    }

    public IntArrayList(int capacity) {
        array = new int[capacity];
    }

    public IntArrayList(IntList list) {
        this(list.size());
        addAll(list);
    }

    public int get(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException();
        return array[index];
    }

    public void set(int index, int value) {
        if (index >= size)
            throw new IndexOutOfBoundsException();
        array[index] = value;
    }

    public int size() {
        return size;
    }

    public void add(int value) {
        ensureCapacity(size + 1);
        array[size++] = value;
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity > array.length) {
            int[] newArray = new int[Math.max(newCapacity, array.length << 1)];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    public int[] toArray() {
        int[] array = new int[size];
        System.arraycopy(this.array, 0, array, 0, size);
        return array;
    }
}
