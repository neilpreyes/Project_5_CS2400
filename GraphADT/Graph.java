package GraphADT;

import java.util.*;
import QueueADT.*;
import StackADT.*;
import DictionaryADT.*;

public class Graph<T> implements GraphInterface {
    private boolean[][] edges;
    private T[] labels;
    private DictionaryInterface<T, VertexInterface<T>> vertices;

    private int currentNumberOfVertices;

    public Graph(int n) {
        edges = new boolean[n][n];
        labels = (T[]) new Object[n];
        vertices = new ArrayDictionary<>();
        currentNumberOfVertices = 0;
    }

    public T getLabel(int vertex){
        return labels[vertex];
    }

    public boolean isEdge(int source, int target){
        return edges[source][target];
    }

    public void addEdge(int source, int target){
        edges[source][target] = true;

        T sourceLabel = getLabel(source-1);
        T targetLabel = getLabel(target-1);

        VertexInterface<T> sourceVertex = vertices.getValue(sourceLabel);
        VertexInterface<T> targetVertex = vertices.getValue(targetLabel);
        ((Vertex<T>) sourceVertex).connect(targetVertex);
    }

    public boolean addVertex(T vertexLabel)
   {
        if (currentNumberOfVertices == labels.length)
            throw new ArrayIndexOutOfBoundsException("No space for more vertrx");
        labels[currentNumberOfVertices++] = vertexLabel;
        VertexInterface<T> addOutcome = vertices.add(vertexLabel, new Vertex<>(vertexLabel));
        return addOutcome == null;
   }

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

    public void removeEdge(int source, int target){
        edges[source][target] = false;
    }

    public void setLabel(int vertex, T newLabel){
        labels[vertex] = newLabel;
    }

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

    public QueueInterface<T> getBreadthFirstTraversal(T origin) {
        //Assumes graph is not empty
        resetVertices();
        QueueInterface<T> traversalOrder = new LinkedQueue<T>();
        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<VertexInterface<T>>();

        VertexInterface<T> originVertex = vertices.getValue(origin);
        originVertex.visit();
        vertexQueue.enqueue(originVertex);
        VertexInterface<T> frontVertex, neighborVertex;

        while(!vertexQueue.isEmpty()){
            frontVertex = vertexQueue.dequeue();

            traversalOrder.enqueue(frontVertex.getLabel());

            Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();

            while(neighbors.hasNext()){
                neighborVertex = neighbors.next();
                if(!neighborVertex.isVisited()){
                    neighborVertex.visit();
                    vertexQueue.enqueue(neighborVertex);
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

        vertexStack.push(vertices.getValue(origin));

        VertexInterface<T> topVertex, neighborVertex;

        while(!vertexStack.isEmpty()){

            topVertex = vertexStack.pop();

            if (!topVertex.isVisited()) {
                traversalOrder.enqueue(topVertex.getLabel());
                topVertex.visit();
            }

            Iterator<VertexInterface<T>> itr = topVertex.getNeighborIterator();

            while (itr.hasNext()) {
                neighborVertex = itr.next();
                if (!neighborVertex.isVisited())
                    vertexStack.push(neighborVertex);
            }
        }
        return traversalOrder;
    }
}
