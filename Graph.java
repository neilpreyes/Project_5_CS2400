public class Graph<E> {
    private boolean[][] edges; // edges[i][j] is true if there is a vertex from i to j
    private E[] labels; // labels[i] contains the label for vertex i

    public Graph(int n) {
        edges = new boolean[n][n]; //all values initially false
        labels = (E[]) new Object[n]; //All values initially null
    }
}
