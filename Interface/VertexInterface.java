package Project_5;

public interface VertexInterface<T> {
    public T getLabel();

    public void visit();

    public void unvisit();

    public boolean isVisited();

    public Iterator<VertexInterface<T>> getNeighborIterator();

    public boolean hasNeighbor();

    public VertexInterface<T> getUnvisitedNeighbor();
}
