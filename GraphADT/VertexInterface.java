package GraphADT;

import java.util.*;

public interface VertexInterface<T> {
    public T getLabel();
    public void visit();
    public void unvisit();
    public boolean isVisited();
    public Iterator<VertexInterface<T>> getNeighborIterator();
    public boolean hasNeighbor();
    public VertexInterface<T> getUnvisitedNeighbor();
    public void setPredecessor(VertexInterface<T> predecessor);
    public VertexInterface<T> getPredecessor();
    public boolean hasPredecessor();
}