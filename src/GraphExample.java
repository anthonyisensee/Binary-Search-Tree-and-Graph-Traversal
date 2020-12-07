import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Test a simple implementation of an adjacency list representation
 * of a graph.
 * @author rlsummerscales, Anthony Isensee
 * */
public class GraphExample {
    public static void main(String[] args) throws FileNotFoundException {

        // create graph
        UndirectedGraph u = new UndirectedGraph();

        // add edges from file
        try {

            // create needed objects
            File mazeEdges = new File("res\\mazeEdges.txt");
            Scanner myScanner = new Scanner(mazeEdges);

            // read file to edges
            while (myScanner.hasNextLine()) {
                String vertex1 = "(" + myScanner.nextLine() + ")";
                String vertex2 = "(" + myScanner.nextLine() + ")";

                // skip whitespace separating edges
                if (myScanner.hasNextLine()) {
                    myScanner.nextLine();
                }

                // add file information to graph
                u.addEdge(vertex1, vertex2);
            }

            // close file
            myScanner.close();

        } catch(FileNotFoundException e) {
            System.out.println("File read error. Check format.");
        }


        //u.display();
        u.breadthFirstSearch("(0,0)");
        u.printPath("(0,0)", "(7,7)");

        // clear information
        u.clear();




        /*

        UndirectedGraph g = new UndirectedGraph();
        g.addEdge("alice", "bob");
        g.addEdge("alice", "carol");
        g.addEdge("bob", "carol");
        g.addEdge("alice", "eve");
        g.display();
        g.depthFirstSearch("eve");
        g.printPath("eve", "carol");
        g.clear();
        
        g.addEdge("s", "a");
        g.addEdge("s", "b");
        g.addEdge("s", "e");
        g.addEdge("a", "b");
        g.addEdge("a", "d");
        g.addEdge("b", "c");
        g.addEdge("b", "e");
        g.addEdge("c", "d");
        g.addEdge("d", "e");
        
        g.breadthFirstSearch("s");
        System.out.println();
        g.displayBFS();
        
        g.printPath("s", "c");
        System.out.println();
        g.printPath("s", "d");
        System.out.println();
        
        g.depthFirstSearch();
        g.displayDFS();
        
        g.depthFirstSearch("s");
        System.out.println();
        g.displayDFS();
        
        g.printPath("s", "c");
        System.out.println();
        g.printPath("s", "d");
        System.out.println();    
        g.clear();



         */
    } 
}
