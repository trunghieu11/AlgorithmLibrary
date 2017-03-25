package trunghieu11.generated.collections.set;

import trunghieu11.generated.collections.IntCollection;

public interface IntSet extends IntCollection {
    @Override
    default public int count(int value) {
        return contains(value) ? 1 : 0;
    }
}
