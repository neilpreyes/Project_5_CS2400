import GraphADT.*;
import QueueADT.*;
import StackADT.*;

public class GraphTest {
    public static void main(String[] args)
    {
        GraphInterface<Character> GraphTest = new Graph<>(10);

        //Add vertices to the graph
        ((Graph) GraphTest).addVertex('A'); //1
        ((Graph) GraphTest).addVertex('B'); //2
        ((Graph) GraphTest).addVertex('C'); //3
        ((Graph) GraphTest).addVertex('D'); //4
        ((Graph) GraphTest).addVertex('E'); //5
        ((Graph) GraphTest).addVertex('F'); //6
        ((Graph) GraphTest).addVertex('G'); //7
        ((Graph) GraphTest).addVertex('H'); //8
        ((Graph) GraphTest).addVertex('I'); //9

        //Connect the vertices
        GraphTest.addEdge(1, 2);
        GraphTest.addEdge(1, 5);
        GraphTest.addEdge(1, 4);
        GraphTest.addEdge(2, 5);
        GraphTest.addEdge(4, 7);
        GraphTest.addEdge(5, 6);
        GraphTest.addEdge(5, 8);
        GraphTest.addEdge(7, 8);
        GraphTest.addEdge(8, 9);
        GraphTest.addEdge(6, 8);
        GraphTest.addEdge(6, 3);
        GraphTest.addEdge(3, 2);

        QueueInterface<Character> depthFirstTravel = ((Graph) GraphTest).getDepthFirstTraversal('A');
        System.out.print("Depth First Travel: ");
        while(!depthFirstTravel.isEmpty())
        {
            System.out.print(depthFirstTravel.dequeue() + ", ");
        }

        System.out.println();

        QueueInterface<Character> breadthFirstSearch = ((Graph) GraphTest).getBreadthFirstTraversal('A');
        System.out.print("Breadth First Travel: ");
        while(!breadthFirstSearch.isEmpty())
        {
            System.out.print(breadthFirstSearch.dequeue() + ", ");
        }

    }
}
