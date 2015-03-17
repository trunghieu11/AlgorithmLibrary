package trunghieu11.graph1;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 8/24/13
 * Time: 12:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class WeightedFlowEdge extends FlowEdge {
    private final long weight;

    public WeightedFlowEdge(int source, int destination, long weight, long capacity) {
        super(source, destination, capacity);
        this.weight = weight;
    }

    public long getWeight() {
        return weight;
    }
}
