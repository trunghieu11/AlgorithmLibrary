package myTool.GraphTool;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 8/24/13
 * Time: 12:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class FlowEdge extends SimpleEdge {
    private long capacity;
    private long flow = 0;
    private Edge reverseEdge;

    public FlowEdge(int source, int destination, long capacity) {
        super(source, destination);
        this.capacity = capacity;
        reverseEdge = new ReverseEdge();
    }

    public long getWeight() {
        return 0;
    }

    public long getCapacity() {
        return capacity;
    }

    public long getFlow() {
        return flow;
    }

    public void pushFlow(long flow) {
        if (flow > 0) {
            if (this.capacity < flow)
                throw new IllegalArgumentException();
        } else {
            if (this.flow < -flow)
                throw new IllegalArgumentException();
        }
        capacity -= flow;
        this.flow += flow;
    }

    public Edge getReverseEdge() {
        return reverseEdge;
    }

    private class ReverseEdge implements Edge {
        public int getSource() {
            return destination;
        }

        public int getDestination() {
            return source;
        }

        public long getWeight() {
            return -FlowEdge.this.getWeight();
        }

        public long getCapacity() {
            return flow;
        }

        @Override
        public Edge getTransposedEdge() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public void pushFlow(long flow) {
            FlowEdge.this.pushFlow(-flow);
        }

        public Edge getReverseEdge() {
            return FlowEdge.this;
        }


    }
}
