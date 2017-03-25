package trunghieu11.generated.collections;

import trunghieu11.generated.collections.iterator.IntIterator;

public interface IntReversableCollection extends IntCollection {
    //abstract
    public IntIterator reverseIterator();

    //base
    default public int last() {
        return reverseIterator().value();
    }

    default IntStream reversed() {
        return () -> reverseIterator();
    }
}
