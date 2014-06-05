package myTool.GraphTool;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 8/22/13
 * Time: 9:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleEdge implements Edge {
    protected final int source;
    protected final int destination;
    protected Edge transposed = null;

    public SimpleEdge(int source, int destination) {
        this.source = source;
        this.destination = destination;
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public long getWeight() {
        return 1;
    }

    public long getCapacity() {
        return 0;
    }

    public Edge getTransposedEdge() {
        if (transposed == null)
            transposed = new SimpleTransposedEdge();
        return transposed;
    }

    public Edge getReverseEdge() {
        return null;
    }

    @Override
    public void pushFlow(long flow) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    protected class SimpleTransposedEdge implements Edge {
        public int getSource() {
            return destination;
        }

        public int getDestination() {
            return source;
        }

        public long getWeight() {
            return 1;
        }

        public long getCapacity() {
            return 0;
        }

        public Edge getTransposedEdge() {
            return SimpleEdge.this;
        }

        public Edge getReverseEdge() {
            return null;
        }

        @Override
        public void pushFlow(long flow) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
