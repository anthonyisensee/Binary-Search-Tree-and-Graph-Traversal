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

        /** Directed Graph Solution */

        // create directed graph
        DirectedGraph g = new DirectedGraph();

        // add edges from file
        try {

            // create needed objects
            File mazeEdges = new File("res\\directedMazeEdges.txt");
            Scanner myScanner = new Scanner(mazeEdges);

            // read file to edges
            while (s.hasNextLine()) {
                String vertex1 = s.next();
                String vertex2 = s.next();
                String action = s.next();
                s.nextLine();

                // add file information to graph
                g.addEdge(vertex1, vertex2, action);
            }

            // close file
            myScanner.close();

        } catch(FileNotFoundException e) {
            System.out.println("File read error. Check format.");
        }

        // TODO: Continue developing ability to easily import from file. Consider implementing .xls or .csv, more common file types
        // TODO: Add ability to import lone vertices from same file same as edges
        // add all end vertexes with no edges extending from them so that all points can be found.
        g.addVertex("(5,0)");
        g.addVertex("(7,0)");
        g.addVertex("(0,2)");
        g.addVertex("(7,4)");
        g.addVertex("(5,7)");
        g.addVertex("(7,7)");
        System.out.println();
        System.out.println();

        System.out.println("Using Breadth First Approach: ");
        g.breadthFirstSearch("(0,0)");
        g.printPath("(0,0)", "(7,7)");
        System.out.println();
        System.out.println();

        System.out.println("Using Depth First Approach: ");
        g.depthFirstSearch("(0,0)");
        g.printPath("(0,0)", "(7,7)");
        System.out.println();
        System.out.println();

        System.out.println("Alternate Coordinates: ");
        g.depthFirstSearch("(0,0)");
        g.printPath("(0,0)", "(7,0)");
        System.out.println();
        System.out.println();

        // clear all information
        g.clear();


        /* Undirected Graph Solution */
        /*
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

        u.breadthFirstSearch("(0,0)");
        u.displayBFS();
        u.printPath("(0,0)", "(7,7)");

        // clear information
        u.clear();

         */





        /* Given Examples */
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
