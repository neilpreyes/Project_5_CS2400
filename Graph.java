public class Graph<E> {
    private boolean[][] edges; // edges[i][j] is true if there is a vertex from i to j
    private E[] labels; // labels[i] contains the label for vertex i

    public Graph(int n) {
        edges = new boolean[n][n]; //all values initially false
        labels = (E[]) new Object[n]; //All values initially null
    }

    //Accessor method to get the label of a vertex of this Graph
    public E getLabel(int vertex){
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

    //obatain a list of neighbors of a specific vertex of this Graph
    public int[] neighbors(int vertex){
        int i;
        int count = 0;
        int[] answer;

        for(i = 0; i < labels.length; i++){
            if(edges[vertex])
                count++;
        }
        answer = new int[count];
        for(i = 0; i < labels.length; i++){
            if(edges[vertex][i])
                answer[count++] = i;
        }

        return answer;
    }
}
