package trunghieu11.generated.collections.queue;

import trunghieu11.generated.collections.LongCollection;

public interface LongQueue extends LongCollection {
    default public long first() {
        return peek();
    }

    public long peek();

    public long poll();
}
