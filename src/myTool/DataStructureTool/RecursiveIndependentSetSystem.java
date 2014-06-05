package myTool.DataStructureTool;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 9/30/12
 * Time: 9:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class RecursiveIndependentSetSystem implements IndependentSetSystem {
    private final int[] color;
    private int setCount;
    private Listener listener;

    public RecursiveIndependentSetSystem(int size) {
        color = new int[size];
        for (int i = 0; i < size; i++)
            color[i] = i;
        setCount = size;
    }

    public RecursiveIndependentSetSystem(RecursiveIndependentSetSystem other) {
        color = other.color.clone();
        setCount = other.setCount;
    }

    public boolean join(int first, int second) {
        first = get(first);
        second = get(second);
        if (first == second)
            return false;
        setCount--;
        color[second] = first;
        if (listener != null)
            listener.joined(second, first);
        return true;
    }

    public int get(int index) {
        if (color[index] == index)
            return index;
        return color[index] = get(color[index]);
    }

    public int getSetCount() {
        return setCount;
    }
}
