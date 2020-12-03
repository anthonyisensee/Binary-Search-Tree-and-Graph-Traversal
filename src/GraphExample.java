
/** Test a simple implementation of an adjacency list representation 
  * of a graph.
  * @author rlsummerscales
  * */
public class GraphExample {
    public static void main(String[] args){
        UndirectedGraph g = new UndirectedGraph();
        g.addEdge("alice", "bob");
        g.addEdge("alice", "carol");
        g.addEdge("bob", "carol");
        g.addEdge("alice", "eve");
        g.display();
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
    } 
}
