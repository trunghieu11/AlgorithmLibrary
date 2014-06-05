package myTool.DataStructureTool;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 2/3/13
 * Time: 9:21 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IntComparator {
    public static final IntComparator DEFAULT = new IntComparator() {
        public int compare(int first, int second) {
            if (first < second)
                return -1;
            if (first > second)
                return 1;
            return 0;
        }
    };

    public static final IntComparator REVERSE = new IntComparator() {
        public int compare(int first, int second) {
            if (first < second)
                return 1;
            if (first > second)
                return -1;
            return 0;
        }
    };

    public int compare(int first, int second);
}
