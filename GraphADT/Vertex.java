package GraphADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ListADT.*;


public class Vertex<T> implements VertexInterface<T>
{
   private T label;
   private ListIteratorInterface<Edge> edgeList; 
   private boolean visited; 
   private VertexInterface<T> previousVertex; 
   
   public Vertex(T vertexLabel)
   {
      label = vertexLabel;
      edgeList = new LinkedList<>();
      visited = false;
      previousVertex = null;
   } 

    public T getLabel()
    {
        return label;
    }

    public void visit()
    {
        visited = true;
    }

    public void unvisit()
    {
        visited = false;
    }

    public boolean isVisited()
    {
        return visited;
    }

    public boolean connect(VertexInterface<T> endVertex, double edgeWeight)
    {
        boolean result = false;

        if (!this.equals(endVertex))
        { 
            Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
            boolean duplicateEdge = false;

            while (!duplicateEdge && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor))
                    duplicateEdge = true;
            } 

            if (!duplicateEdge)
            {
                edgeList.add(new Edge(endVertex, edgeWeight));
                result = true;
            } 
        }

        return result;
    } 

    public boolean connect(VertexInterface<T> endVertex)
    {
        return connect(endVertex, 0);
    }

    public Iterator<VertexInterface<T>> getNeighborIterator()
    {
       return new NeighborIterator();
    } 

    public boolean hasNeighbor()
    {
        return !edgeList.isEmpty();
    } 

    public VertexInterface<T> getUnvisitedNeighbor()
    {
        VertexInterface<T> result = null;

        Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
        while ( neighbors.hasNext() && (result == null) )
        {
            VertexInterface<T> nextNeighbor = neighbors.next();
            if (!nextNeighbor.isVisited())
                result = nextNeighbor;
        }

        return result;
    }
    
    public void setPredecessor(VertexInterface<T> predecessor)
    {
        previousVertex = predecessor;
    }

    public VertexInterface<T> getPredecessor()
    {
        return previousVertex;
    }

    public boolean hasPredecessor()
    {
        return previousVertex != null;
    }
    
    private class NeighborIterator implements Iterator<VertexInterface<T>>
    {
       private Iterator<Edge> edges;
       
       private NeighborIterator()
       {
          edges = edgeList.getIterator();
       }
       
       public boolean hasNext()
       {
          return edges.hasNext();
       }
       
       public VertexInterface<T> next()
       {
          VertexInterface<T> nextNeighbor = null;
          
          if (edges.hasNext())
          {
             Edge edgeToNextNeighbor = edges.next();
             nextNeighbor = edgeToNextNeighbor.getEndVertex();
          }
          else
             throw new NoSuchElementException();
          
          return nextNeighbor;
       }
       
       @Override
       public void remove()
       {
          throw new UnsupportedOperationException();
       }
    }

   protected class Edge
   {
      private VertexInterface<T> vertex;
      private double weight;
      
      protected Edge(VertexInterface<T> endVertex, double edgeWeight)
      {
         vertex = endVertex;
         weight = edgeWeight;
      }
      
      protected Edge(VertexInterface<T> endVertex)
      {
         vertex = endVertex;
         weight = 0;
      }

      protected VertexInterface<T> getEndVertex()
      {
         return vertex;
      }
      
      protected double getWeight()
      {
         return weight; 
      }
   }
}