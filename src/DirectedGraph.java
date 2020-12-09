import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

/**
 * DirectedGraph Class
 * @author Original Source Code Creator, Anthony Isensee (conversion to directed graph)
 */
public class DirectedGraph {
    
    /** Edge object contains information about an edge to a given vertex */ 
    public class Edge {
        /** label for the vertex that the edge is connects to */
        public String vertexLabel = "";
        
        /** weight for the edge */
        public int weight = 0;

        /** action to be taken to move along the edge */
        public String action = "";
        
        public Edge(String vLabel, String vAction){
            vertexLabel = vLabel;
            action = vAction;
        }
        
        public Edge(String vLabel, int w){
            vertexLabel = vLabel;
            weight = w;
        }


    }
    
    /** Vertex object contains all of the relevant information for a vertex */
    public class Vertex {
        /** adjacency list specifying edges from this vertex */
        public LinkedList<Edge> adjacencyList = new LinkedList<Edge>();
        /** label for the vertex */
        public String label = null;

        /** used in BFS and DFS */
        public boolean discovered = false;
        public boolean visited = false;
        public String parent = null;
        /** label for action to get to vertex */
        public String parentAction = null;
        public int distance = Integer.MAX_VALUE;
        public int discoveryTime = 0;
        public int finishingTime = 0;
        
        public Vertex(String label){
            this.label = label;
        }
        
        /** add an edge to the vertex with the given label */
        public void addEdge(String vLabel, String vAction){
            adjacencyList.add(new Edge(vLabel, vAction));
        }
        
        /** add a weighted edge to the vertex with the given label */
        public void addWeightedEdge(String vLabel, int w){
            adjacencyList.add(new Edge(vLabel, w));
        }
        
        /** output all of the vertices that this vertex has edges to */
        public void displayEdges() {
            System.out.print("Edges: ");
            for (Edge e: adjacencyList){
                System.out.print(e.action+" ");
                System.out.print(e.vertexLabel+" ");
            }
            if(adjacencyList.isEmpty()) {
                System.out.print("N/A");
            }
            System.out.println();
        }
        
        /** remove all of the edges from this vertex */
        public void clear() {
            adjacencyList.clear();
        }
    }
    
    /** stores the set of vertices in the graph. keyed by the name of the vertex. */
    public HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();
    
    /** current finishing time. used by DFS */
    private int time = 0;
    
    /** add the directed edge (v,u) to the graph along with the action that needs to be taken to make the move */
    public void addEdge(String vLabel, String uLabel, String action){
        // add edge (v,u,action)
        if (vertices.containsKey(vLabel)){
            vertices.get(vLabel).addEdge(uLabel,action);
        } else {
            Vertex v = new Vertex(vLabel);
            v.addEdge(uLabel,action);
            vertices.put(vLabel, v);
        }
    }

    /** add a single vertex with no edges */
    public void addVertex(String vLabel) {
        if (vertices.containsKey(vLabel)){
            // do nothing, vertex with no edges is already present
        } else {
            Vertex v = new Vertex(vLabel);
            vertices.put(vLabel, v);
        }
    }

    
    /** output the vertices and their edges */
    public void display() {
        for (Entry<String, Vertex> vertexEntry: vertices.entrySet()){
            Vertex v = vertexEntry.getValue();
            System.out.print("Vertex: "+v.label+", ");
            v.displayEdges();
        }
    }
    
    /** remove all vertices and their edges from the graph */
    public void clear() {
        for (Entry<String, Vertex> vertexEntry: vertices.entrySet()){
            Vertex v = vertexEntry.getValue();
            v.clear();   // clear adjacency list for v
        }
        vertices.clear();      // delete set of vertices
    }
    
    
    /** output the vertices and their edges and information relevant to BFS*/
    public void displayBFS() {
        for (Entry<String, Vertex> vertexEntry: vertices.entrySet()){
            Vertex v = vertexEntry.getValue();
            System.out.print("Vertex: "+v.label+", d = "+v.distance+", ");
            v.displayEdges();
        }
    }
    
    /** output the vertices and their edges and information relevant to DFS */
    public void displayDFS() {
        for (Entry<String, Vertex> vertexEntry: vertices.entrySet()){
            Vertex v = vertexEntry.getValue();
            System.out.print("Vertex: "+v.label);
            System.out.print(", discovery time = "+v.discoveryTime);
            System.out.print(", finishing time = "+v.finishingTime+", ");
            v.displayEdges();
        }
    }
    
    
    /** perform a breadth-first search of the graph starting at 
      * the vertex with given label */
    public void breadthFirstSearch(String label) {
        Vertex s = vertices.get(label);    // root of the graph
        
        // Initialize every vertex 
        for (Entry<String, Vertex> vertexEntry: vertices.entrySet()) {
            Vertex u = vertexEntry.getValue();
            u.discovered = false;     // has it been added to the queue?
            u.visited = false;        // has it been removed from the queue and explored? 
            u.distance = Integer.MAX_VALUE;   // distance from the root of the graph (vertex s)
            u.parent = null;          // parent in BFS tree of graph
            u.parentAction = null;
        }
        
        s.discovered = true;
        s.distance = 0;
        s.parentAction = null;
        
        // Create a queue of vertices to explore 
        LinkedList<Vertex> q = new LinkedList<Vertex>();
        q.addLast(s);
        
        while(q.isEmpty() == false) {
            // remove next vertex, u, to explore from front of queue
            Vertex u = q.remove();
            
            // check vertices this one is connected to
            // if they have not been discovered yet (i.e. not explored and not in queue)
            // then add them to the back of the queue
            for(Edge e: u.adjacencyList){
                Vertex v = vertices.get(e.vertexLabel);
                // note that the v!=null will catch any vertices without edges leading away from them
                if (v != null && v.discovered == false) {
                    v.discovered = true;       // vertex has now been discovered 
                    v.distance = u.distance + 1;
                    v.parent = u.label;
                    v.parentAction = e.action;
                    q.addLast(v);              // add new vertex to end of the queue    
                }
            }
            // we have finished exploring u, mark it as visited
            u.visited = true;
        }
    }
    
    /** output the vertices on the path from s to v */
    public void printPath(String sLabel, String vLabel){
        Vertex s = vertices.get(sLabel);
        Vertex v = vertices.get(vLabel);
        
        if (s == v) {
            System.out.print("Start at " + sLabel);
        } else if (v.parent == null) {
            System.out.println("No path from "+s.label+" to "+v.label+" exists");
        } else {
            printPath(s.label, v.parent);
            System.out.print(", "+ v.parentAction + " " +v.label);
        }
    }
    
    /** perform depth first search of graph */
    public void depthFirstSearch() {
        // initialize every vertex
        for (Entry<String, Vertex> vertexEntry: vertices.entrySet()) {
            Vertex u = vertexEntry.getValue();
            u.discovered = false;        // has the vertex been discovered?
            u.visited = false;           // has the vertex been fully explored?
            u.parent = null;             // parent in DFS tree in graph
            u.parentAction = null;
        }
        
        time = 0;       // finishing time -- time stamp recorded when the vertex was completely explored
        for (Entry<String, Vertex> vertexEntry: vertices.entrySet()) {
            Vertex u = vertexEntry.getValue();
            if (u.discovered == false){
                dfsVisit(u);      // treat u as root of graph and visit all vertices reachable from u
            }
        }  
    }
    
    /** recursively visit each vertex connected to vertex u */
    private void dfsVisit(Vertex u){
        time = time + 1;        // time stamp increments as each vertex is visited
        u.discoveryTime = time;
        u.discovered = true;
        
        // explore each edge (u,v)
        // If a vertex v is undiscovered, then recursively visit it and its children
        // If it has been discovered, ignore it
        for(Edge e: u.adjacencyList){
            Vertex v = vertices.get(e.vertexLabel);
            if (v.discovered == false){
                v.parent = u.label;
                dfsVisit(v);
                v.parentAction = u.parentAction;
            }
        }
        u.visited = true;
        time = time + 1;      // time stamp increments as each vertex finished
        u.finishingTime = time;
    }
    
    /** perform depth first search of graph starting at a node with a given label*/
    public void depthFirstSearch(String sLabel) {
        for (Entry<String, Vertex> vertexEntry: vertices.entrySet()) {
            Vertex u = vertexEntry.getValue();
            u.discovered = false;
            u.visited = false;
            u.parent = null;
            u.parentAction = null;
        }
        
        time = 0;
        Vertex u = vertices.get(sLabel);
        // begin DFS of graph starting at vertex with given label
        dfsVisit(u); 
    }

    public static void main(String[] args) {


        DirectedGraph g = new DirectedGraph();

        g.addEdge("0", "1", "east to");
        g.addEdge("1", "2", "south to");
        g.addEdge("1", "3", "east to");
        //g.addVertex("3");
        g.addVertex("2");   // note that this vertex must be added to the system so the algorithm can actually find it

        g.display();
        g.breadthFirstSearch("0");
        g.printPath("0", "2");
        System.out.println();


    }
}