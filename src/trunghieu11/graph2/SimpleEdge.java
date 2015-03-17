package trunghieu11.graph2;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 8/22/13
 * Time: 9:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleEdge<V> implements Edge<V> {
    protected final V source;
    protected final V destination;
    protected Edge<V> transposed = null;

    public SimpleEdge(V source, V destination) {
        this.source = source;
        this.destination = destination;
    }

    public V getSource() {
        return source;
    }

    public V getDestination() {
        return destination;
    }

    public long getWeight() {
        return 1;
    }

    public Edge<V> getTransposedEdge() {
        if (transposed == null)
            transposed = new SimpleTransposedEdge();
        return transposed;
    }

    public Edge<V> getReverseEdge() {
        return null;
    }

    @Override
    public void pushFlow(long flow) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    protected class SimpleTransposedEdge implements Edge<V> {
        public V getSource() {
            return destination;
        }

        public V getDestination() {
            return source;
        }

        public long getWeight() {
            return 1;
        }

        public Edge<V> getTransposedEdge() {
            return SimpleEdge.this;
        }

        public Edge<V> getReverseEdge() {
            return null;
        }

        @Override
        public void pushFlow(long flow) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

    }
}
