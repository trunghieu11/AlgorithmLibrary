package trunghieu11.generated.collections.queue;

import trunghieu11.generated.collections.DoubleCollection;

public interface DoubleQueue extends DoubleCollection {
    default public double first() {
        return peek();
    }

    public double peek();

    public double poll();
}
