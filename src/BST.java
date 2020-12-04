/**
 *  Compilation:  javac BST.java
 *  Execution:    java BST
 *  Dependencies: StdIn.java StdOut.java Queue.java
 *  Data files:   http://algs4.cs.princeton.edu/32bst/tinyST.txt
 *
 *  A symbol table implemented with a binary search tree.
 *
 *  % more tinyST.txt
 *  S E A R C H E X A M P L E
 *
 *  % java BST < tinyST.txt
 *  A 8
 *  C 4
 *  E 12
 *  H 5
 *  L 11
 *  M 9
 *  P 10
 *  R 3
 *  S 0
 *  X 7
 */

import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<Key extends Comparable<Key>, Value> {

    /** Binary Search Tree's (BST's) Root */
    private Node root;

    /** A BST Node */
    private class Node {

        /** Key that determine position of data in BST. */
        private Key key;

        /** Data associated with key */
        private Value val;

        /** Left and Right children, potentially parents of subtrees */
        private Node left, right;

        /** Number of nodes in subtree */
        private int N;

        /** Node Constructor */
        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    /** Check to see if BST is empty */
    public boolean isEmpty() {
        return size() == 0;
    }

    /** Return size of BST */
    public int size() {
        return size(root);
    }

    /**
     * Return number of key-value pairs in BST at a certain root.
     * @param x the Node to examine as the root.
     */
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

   /**
    * Search BST for given key, and return associated value if found,
    * return null if not found
    */

    /**
     * Checks the search tree to see if there exists a value with a given key.
     * does there exist a key-value pair with given key?
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /** Return a value associated with a given key in the full BST, or null if no such key exists. */
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * Retrieve a value from any root given a certain key.
     * @param x Root of tree (or subtree) to begin searching.
     * @param key Key to search for.
     * @return Value from given key (or null if nonexistent?).
     */
    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        // Recursively searches until it finds correct key, then returns value at that node.
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

   /**
    * Insert key-value pair into BST
    * If key already exists, update with new value
    */
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }
        root = put(root, key, val);
        assert check();
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

   /**
    *  Deletion
    */

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
        assert check();
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
        assert check();
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
        assert check();
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else { 
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        } 
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    } 


   /**
    * Min, max, floor, and ceiling
    */
    public Key min() {
        if (isEmpty()) return null;
        return min(root).key;
    } 

    private Node min(Node x) { 
        if (x.left == null) return x; 
        else                return min(x.left); 
    }

    /** Iteratively finds the minimum key in the BST. */
    public Key iterativeMin() {
        if (isEmpty()) return null;
        return iterativeMin(root).key;
    }

    /**
     * Returns the node with the minimum key at the specified root.
     * @param x Root to search.
     * @return Node containing specified key or null if root is empty.
     */
    private Node iterativeMin(Node x) {

        // if empty
        if (x == null)
            return null;
        // if not empty
        else {
            // continue along all left nodes until empty node is found, then stop
            while (x.left != null) {
                x = x.left;
            }
            // return node x that must contain minimum key
            return x;
        }
    }

    public Key max() {
        if (isEmpty()) return null;
        return max(root).key;
    } 

    private Node max(Node x) { 
        if (x.right == null) return x; 
        else                 return max(x.right); 
    }

    /** Iteratively finds the maximum key in the BST. */
    public Key iterativeMax() {
        if (isEmpty()) return null;
        return iterativeMax(root).key;
    }

    /**
     * Returns the node with the minimum key at the specified root.
     * @param x Root to search.
     * @return Node containing specified key or null if root is empty.
     */
    private Node iterativeMax(Node x) {

        // if empty
        if (x == null)
            return null;
            // if not empty
        else {
            // continue along all right nodes until an empty node is found, then stop.
            while (x.right != null) {
                x = x.right;
            }
            // return node x that must contain minimum key
            return x;
        }
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    } 

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp <  0) return floor(x.left, key);
        Node t = floor(x.right, key); 
        if (t != null) return t;
        else return x; 
    } 

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) { 
            Node t = ceiling(x.left, key); 
            if (t != null) return t;
            else return x; 
        } 
        return ceiling(x.right, key); 
    } 

   /**
    * Rank and selection
    */
    public Key select(int k) {
        if (k < 0 || k >= size())  return null;
        Node x = select(root, k);
        return x.key;
    }

    // Return key of rank k. 
    private Node select(Node x, int k) {
        if (x == null) return null; 
        int t = size(x.left); 
        if      (t > k) return select(x.left,  k); 
        else if (t < k) return select(x.right, k-t-1); 
        else            return x; 
    } 

    public int rank(Key key) {
        return rank(key, root);
    } 

    // Number of keys in the subtree less than key.
    private int rank(Key key, Node x) {
        if (x == null) return 0; 
        int cmp = key.compareTo(x.key); 
        if      (cmp < 0) return rank(key, x.left); 
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right); 
        else              return size(x.left); 
    } 

   /**
    * Range count and range search.
    */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    } 

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) { 
        if (x == null) return; 
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key); 
        if (cmplo < 0) keys(x.left, queue, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key); 
        if (cmphi > 0) keys(x.right, queue, lo, hi); 
    } 

    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }


    /** Returns the height of the BST. */
    public int height() { return height(root); }

    /**
     * Returns the height of the BST at root x.
     * @param x The root to determine height of.
     */
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }


    // level order traversal
    public Iterable<Key> levelOrder() {
        Queue<Key> keys = new Queue<Key>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }

  /**
   *  Check integrity of BST data structure
   */
    private boolean check() {
        if (!isBST())            StdOut.println("Not in symmetric order");
        if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
        if (!isRankConsistent()) StdOut.println("Ranks not consistent");
        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    } 

    // are the size fields correct?
    private boolean isSizeConsistent() { return isSizeConsistent(root); }
    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.N != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    } 

    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }

    /** Additional Functionality added by Anthony Isensee */

    /**
     * Prints a path from the root to the node with Key key.
     * @param key The key being searched.
     * TODO: Modify to follow assignment guidelines.
     */
    public void printPath(Key key) {
        // check to see if bst contains the key at all. If not, let user know.
        if (recursivePrintPath(root, key)) {
            System.out.println("Supplied key (" + key + ") is not in the binary search tree.");
        }
        // if it does contain the key, use recursivePrintPath to print path
        else {
            System.out.print("Key was found. Path from Root (" + root.key + ") to Key (" + key + ") is : ");
            boolean somethingIsNotBroken = recursivePrintPath(root, key);
        }
    }

    /**
     * Returns true if key k is found among one of x's descendents.
     * @param x The node to be searched.
     * @param k The key to search for.
     * @return true if key is among x's descendents, false if it is not.
     * TODO: Modify to follow assignment guidelines.
     */
    private boolean recursivePrintPath(Node x, Key k) {

        // if x is null we are working with an empty tree. Thus, return false.
        if (x == null) {
            return false;
        }
        // compare our two keys using the methodology already written into this class
        int cmp = k.compareTo(x.key);
        // recursively searches until it finds correct key
        if      (cmp < 0) {
            // if we're headed down the left side, print node's key and continue
            System.out.print(x.key + " -> ");
            return recursivePrintPath(x.left, k);
        }
        else if (cmp > 0) {
            // if we're headed down the right side, print node's key and continue
            System.out.print(x.key + " -> ");
            return recursivePrintPath(x.right, k);
        }
        // if key not smaller or larger than key we're looking for (null case has already been eliminated), we've found it!
        else {
            // print final and found element
            System.out.print(x.key);
            System.out.println();
            // finally, return true.
            return true;
        }
    }

    /**
     * Prints the keys associated with all nodes at a certain depth.
     * @param depth Depth of all keys to be printed.
     */
    public void printDepth(int depth) {
        if (isEmpty()) {
            System.out.println("Binary Search Tree is Empty, cannot print items at any depth.");
        }
        else {
            System.out.print("Keys at Depth " + depth + ": ");
            recursivePrintDepth(root, 0, depth);
            System.out.println();
        }
    }

    private void recursivePrintDepth(Node x, int depth, int targetDepth) {
        if (x == null) {    // if node is null
            // do nothing
        }
        else if (depth == targetDepth) { // if we've reached a node with the correct target depth
            // print the key at the node
            System.out.print(x.key + " ");
        }
        else if (depth < targetDepth) { // if we have yet to reach the target depth
            // add one to depth we will now begin searching
            ++depth;
            // continue searching
            recursivePrintDepth(x.left, depth, targetDepth);
            recursivePrintDepth(x.right, depth, targetDepth);
        }

    }



    /**
    * Test client
    */
    public static void main(String[] args) { 

        // keys to be placed in the binary search tree
        String[] keys = {"g", "f", "r", "m", "t", "s", "w", "v", "b", "c", "e", "a"};
        // values to be associated with each key based on alphabetical order
        int[] values = {7, 6, 18, 13, 20, 19, 23, 22, 2, 3, 5, 1};

        BST<String, Integer> bst = new BST<String, Integer>();
        for(String key : keys) {
            int i = 0;
            bst.put(key, values[i]);
            i++;    // increment i so next loop places correct value
        }

        System.out.println();
        System.out.println("Minimum Key Found Iteratively: " + bst.iterativeMin());
        System.out.println("Maximum Key Found Iteratively: " + bst.iterativeMax());
        System.out.println();

        bst.printPath("v");
        //bst.printPath("e");
        bst.printPath("h");

        System.out.println();
        bst.printDepth(0);
        bst.printDepth(2);
        bst.printDepth(4);
        bst.printDepth(42);


        int i = 0;

        /*
        BST<String, Integer> st = new BST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.levelOrder())
            StdOut.println(s + " " + st.get(s));

        StdOut.println();

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        */
    }
}
