package trunghieu11.graph1;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 8/22/13
 * Time: 9:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class WeightedEdge extends SimpleEdge {
    private final long weight;

    public WeightedEdge(int source, int destination, long weight) {
        super(source, destination);
        this.weight = weight;
    }

    public long getWeight() {
        return weight;
    }

    public Edge getTransposedEdge() {
        if (transposed == null)
            transposed = new WeightedTransposedEdge();
        return transposed;
    }

    protected class WeightedTransposedEdge extends SimpleTransposedEdge {
        public long getWeight() {
            return weight;
        }
    }
}
