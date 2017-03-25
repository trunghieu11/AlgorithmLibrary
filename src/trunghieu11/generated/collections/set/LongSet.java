package trunghieu11.generated.collections.set;

import trunghieu11.generated.collections.LongCollection;

public interface LongSet extends LongCollection {
    @Override
    default public int count(long value) {
        return contains(value) ? 1 : 0;
    }
}
