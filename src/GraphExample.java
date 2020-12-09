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

        /* Read from file */

        // create directed graph
        DirectedGraph g = new DirectedGraph();

        // add edges from file
        try {

            File f = new File("res\\directedEdges.csv");

            // set up scanner
            Scanner s = new Scanner(f);
            s.useDelimiter(",");

            // read file to edges
            while (s.hasNextLine()) {
                s.nextLine();   // first time skips header. Rest of times advances current read line by one
                String vertex1 = "(" + s.next() + "," + s.next() + ")";   // must use two s.nexts in order to capture both parts of coordinate
                String vertex2 = "(" + s.next() + "," + s.next() + ")";
                String action = s.next();
                // add file information to graph
                g.addEdge(vertex1, vertex2, action);
            }

            // close file
            s.close();

        } catch(FileNotFoundException e) {
            System.out.println("File read error. Check format.");
        }

        /* Process Solutions */

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

        System.out.println("Backwards: ");
        g.depthFirstSearch("(7,7)");
        g.printPath("(7,7)", "(0,0)");
        System.out.println();
        System.out.println();

        System.out.println("Alternate Coordinates: ");
        g.depthFirstSearch("(5,7)");
        g.printPath("(5,7)", "(0,2)");
        System.out.println();

        // clear all information from graph
        g.clear();

    } 
}
