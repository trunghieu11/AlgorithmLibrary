package myTool.DataStructureTool;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 8/21/13
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Heap {
    private IntComparator comparator;
    private int size = 0;
    private int[] elements;
    private int[] at;

    public Heap(int maxElement) {
        this(10, maxElement);
    }

    public Heap(IntComparator comparator, int maxElement) {
        this(10, comparator, maxElement);
    }

    public Heap(int capacity, int maxElement) {
        this(capacity, IntComparator.DEFAULT, maxElement);
    }

    public Heap(int capacity, IntComparator comparator, int maxElement) {
        this.comparator = comparator;
        elements = new int[capacity];
        at = new int[maxElement];
        Arrays.fill(at, -1);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int add(int element) {
        ensureCapacity(size + 1);
        elements[size] = element;
        at[element] = size;
        shiftUp(size++);
        return at[element];
    }

    public void shiftUp(int index) {
//		if (index < 0 || index >= size)
//			throw new IllegalArgumentException();
        int value = elements[index];
        while (index != 0) {
            int parent = (index - 1) >>> 1;
            int parentValue = elements[parent];
            if (comparator.compare(parentValue, value) <= 0) {
                elements[index] = value;
                at[value] = index;
                return;
            }
            elements[index] = parentValue;
            at[parentValue] = index;
            index = parent;
        }
        elements[0] = value;
        at[value] = 0;
    }

    public void shiftDown(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException();
        while (true) {
            int child = (index << 1) + 1;
            if (child >= size)
                return;
            if (child + 1 < size && comparator.compare(elements[child], elements[child + 1]) > 0)
                child++;
            if (comparator.compare(elements[index], elements[child]) <= 0)
                return;
            swap(index, child);
            index = child;
        }
    }

    public int getIndex(int element) {
        return at[element];
    }

    private void swap(int first, int second) {
        int temp = elements[first];
        elements[first] = elements[second];
        elements[second] = temp;
        at[elements[first]] = first;
        at[elements[second]] = second;
    }

    private void ensureCapacity(int size) {
        if (elements.length < size) {
            int[] oldElements = elements;
            elements = new int[Math.max(2 * elements.length, size)];
            System.arraycopy(oldElements, 0, elements, 0, this.size);
        }
    }

    public int poll() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        int result = elements[0];
        at[result] = -1;
        if (size == 1) {
            size = 0;
            return result;
        }
        elements[0] = elements[--size];
        at[elements[0]] = 0;
        shiftDown(0);
        return result;
    }
}
