package GraphADT;

import java.util.*;
import QueueADT.*;
import StackADT.*;
import DictionaryADT.*;

public class Graph<T> implements GraphInterface {
    private boolean[][] edges; // edges[i][j] is true if there is a vertex from i to j
    private T[] labels; // labels[i] contains the label for vertex i
    private DictionaryInterface<T, VertexInterface<T>> vertices;

    public Graph(int n) {
        edges = new boolean[n][n]; //all values initially false
        labels = (T[]) new Object[n]; //All values initially null
        vertices = new ArrayDictionary<>();
    }

    //Accessor method to get the label of a vertex of this Graph
    public T getLabel(int vertex){
        return labels[vertex];
    }

    //Test whether an edge exists
    public boolean isEdge(int source, int target){
        return edges[source][target];
    }

    //Add an edge
    public void addEdge(int source, int target){
        edges[source][target] = true;
    }

    public boolean addVertex(T vertexLabel)
   {
        VertexInterface<T> addOutcome = vertices.add(vertexLabel, new Vertex<>(vertexLabel));
        return addOutcome == null;
   }

    //obatain a list of neighbors of a specific vertex of this Graph
    public int[] neighbors(int vertex){
        int i;
        int count = 0;
        int[] answer;

        for(i = 0; i < labels.length; i++){
            if(edges[vertex][i])
                count++;
        }
        answer = new int[count];
        for(i = 0; i < labels.length; i++){
            if(edges[vertex][i])
                answer[count++] = i;
        }

        return answer;
    }

    //Remove an edge
    public void removeEdge(int source, int target){
        edges[source][target] = false;
    }

    //Change the label of a vertex of this Graph
    public void setLabel(int vertex, T newLabel){
        labels[vertex] = newLabel;
    }

    //Accessor method to determine the number of vertices in this Graph
    public int size(){
        return labels.length;
    }

    public void resetVertices(){
        Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
        while(vertexIterator.hasNext()){
            VertexInterface<T> nextVertex = vertexIterator.next();
            nextVertex.unvisit();
            nextVertex.setPredecessor(null);
        }
    }

    //Uses a queue to keep track of vertices that still need to be visited
    public QueueInterface<T> getBreadthFirstTraversal(T origin) {
        resetVertices();
        QueueInterface<T> traversalOrder = new LinkedQueue<T>();
        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<VertexInterface<T>>();

        VertexInterface<T> originVertex = vertices.getValue(origin);
        originVertex.visit();
        traversalOrder.enqueue(origin); //enqueue vertex label
        vertexQueue.enqueue(originVertex); //enqueue vertex

        while(!vertexQueue.isEmpty()){
            VertexInterface<T> frontVertex = vertexQueue.dequeue();

            Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();

            while(neighbors.hasNext()){
                VertexInterface<T> nextNeighbor = neighbors.next();
                if(!nextNeighbor.isVisited()){
                    nextNeighbor.visit();
                    traversalOrder.enqueue(nextNeighbor.getLabel());
                    vertexQueue.enqueue(nextNeighbor);
                }
            }
        }
        return traversalOrder;
    }

    public QueueInterface<T> getDepthFirstTraversal(T origin){
        //Assumes graph is not empty
        resetVertices();
        QueueInterface<T> traversalOrder = new LinkedQueue<T>();
        StackInterface<VertexInterface<T>> vertexStack = new LinkedStack<>();

        while(!vertexStack.isEmpty()){

            VertexInterface<T> topVertex = vertexStack.peek();
            VertexInterface<T> nextNeighbor = topVertex.getUnvisitedNeighbor();

            if(nextNeighbor != null){
                nextNeighbor.visit();
                traversalOrder.enqueue(nextNeighbor.getLabel());
                vertexStack.push(nextNeighbor);
            }else{
                vertexStack.pop();
            }
        }

        return traversalOrder;
    }
}
