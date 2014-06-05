package myTool.DataStructureTool;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 2/3/13
 * Time: 11:50 PM
 * To change this template use File | Settings | File Templates.
 */
class IntSortedArray extends IntSortedList {
    private final int[] array;

    public IntSortedArray(int[] array) {
        this(array, IntComparator.DEFAULT);
    }

    public IntSortedArray(IntCollection collection) {
        this(collection, IntComparator.DEFAULT);
    }

    public IntSortedArray(int[] array, IntComparator comparator) {
        super(comparator);
        this.array = array;
        ensureSorted();
    }

    public IntSortedArray(IntCollection collection, IntComparator comparator) {
        super(comparator);
        array = new int[collection.size()];
        int i = 0;
        for (IntIterator iterator = collection.iterator(); iterator.isValid(); iterator.advance())
            array[i++] = iterator.value();
        ensureSorted();
    }

    public int get(int index) {
        return array[index];
    }

    public int size() {
        return array.length;
    }
}