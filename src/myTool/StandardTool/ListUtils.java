package myTool.StandardTool;

import myTool.DataStructureTool.ArrayUtils;
import myTool.DataStructureTool.CollectionUtils;

import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/2/12
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListUtils {
    public static Integer[] order(final List<? extends Comparable<?>>...sequences) {
        return ArrayUtils.order(sequences[0].size(), new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                for (List<? extends Comparable> sequence : sequences) {
                    //noinspection unchecked
                    int value = sequence.get(o1).compareTo(sequence.get(o2));
                    if (value != 0)
                        return value;
                }
                return 0;
            }
        });
    }

    public static<T> int find(List<T> sequence, T value) {
        int size = sequence.size();
        for (int i = 0; i < size; i++) {
            if (MiscUtils.equals(sequence.get(i), value))
                return i;
        }
        return size;
    }

    public static<T extends Comparable<T>> int maxIndex(List<T> sequence) {
        return find(sequence, CollectionUtils.maxElement(sequence));
    }
}
