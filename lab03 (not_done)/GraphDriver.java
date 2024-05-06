import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;


/**
 *
 * @author lewi0146
 */
public class GraphDriver {

    /**
     * <p>
     * The GraphDriver class expect no command line arguments but process one
     * line from standard input.
     * </p>
     * <p>
     * The format is
     * </p>
     * <code>level_test additional_args</code>
     * <p>
     * The level_test is a number between 1 and 5.
     * <p>
     * <p>
     * Example usage is print graph
     * of undirected ("U") or directed ("D") graph
     * </p>
     * <p>
     * <code>1 U</code>
     * </p>
     * <p>
     * <code>1 D</code>
     * </p>
     * <p>
     * print graph, the traversal order and distance from a starting node of
     * breadth first traversal of undirected ("U") or directed ("D") graph
     * </p>
     * <p>
     * <code>2 U</code>
     * </p>
     * <p>
     * <code>2 D</code>
     * </p>
     * <p>
     * Do the Bacon!
     * </p>
     * <p>
     * <code>5</code>
     * </p>
     * <p>
     * print graph, the traversal order and distance from a starting node of
     * depth first traversal of undirected ("U") or directed ("D") graph
     * </p>
     * <p>
     * <code>3 U</code>
     * </p>
     * <p>
     * <code>3 D</code>
     * </p>
     * <p>
     * Process the topological sort for Bob's language topics. "Y" to print the
     * graph and "N" to only print the topic list.
     * </p>
     * <p>
     * <code>4 Y</code>
     * </p>
     * <p>
     * <code>4 N</code>
     * </p>
     *
     * <p>
     * For 2 and 3 you can also specify a start vertex to traverse from. The
     * default value is "a", for example
     * </p>
     * <p>
     * <code>2 U a</code>
     * </p>
     * <p>
     * will be an undirected graph doing a breadth first traversal start from
     * the node labelled with an "a".
     * </p>
     * <p>
     * <code>3 D c</code>
     * </p>
     * <p>
     * will be an directed graph doing a depth first traversal start from the
     * node labelled with an "c".
     * </p>
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] input = line.split(" ");

        int testLevel = Integer.parseInt(input[0]);

        Graph g = null;
        if (input.length > 1 && testLevel < 4) {
            String graphType = input[1];
            switch (graphType) {
                case "U":
                    g = new AdjacencyListUndirectedGraph(); // your new Adjacency List Undirected Graph
                    break;
                case "D":
                    g = new AdjacencyListDirectedGraph(); // your new Adjacency List Directed Graph
                    break;

            }
        }

        String startFrom = "a";
        if (input.length > 2 && testLevel < 4) {
            startFrom = input[2];
        }

        switch (testLevel) {
            case 1:
                test1(g); // build and print graph
                break;
            case 2:
                test2(g, startFrom); // breadth first search
                break;
            case 3:
                test3(g, startFrom); // depth first seach
                break;
            case 4:
                String printGraph = (input.length > 1) ? input[1] : "N";
                test4(printGraph); // topological sort uses directed graph only
                break;
            case 5:
                // just for fun!
                bacon(); // uses undirected graph only
                break;
        }

    }

    public static void printGraph(Graph g) {

        List<Vertex> vs = g.getVertices();
        System.out.println("Vertices: " + vs);
        for (Vertex v : vs) {
            System.out.print(v + ": ");
            List<Vertex> adjVList = g.adjacentTo(v);
            adjVList.sort(Vertex::compareTo); // Sort neighbors lexicographically
            for (Vertex av : adjVList) {
                System.out.print(av + " ");
            }
            System.out.println();

        }
    }

    public static void test1(Graph g) {

        System.out.println("test 1: Build a graph");

        // build a sample graph and print it
        g.addEdge("a", "b");
        // g.addEdge("a","d"); // Added by me
        g.addEdge("b", "c");
        g.addEdge("b", "d");
        g.addEdge("c", "d");
        g.addEdge("c", "e");
        g.addEdge("d", "a");

        printGraph(g);

    }

    public static void test2(Graph g, String startFrom) {

        System.out.println("test 2: Breadth First Search");

        // build a sample graph and print it
        g.addEdge("a", "b");
        // g.addEdge("a", "d");
        g.addEdge("b", "c");
        g.addEdge("b", "d");
        g.addEdge("c", "d");
        g.addEdge("c", "e");
        g.addEdge("d", "a");

        printGraph(g);

        // perform a breadth first traversal from vertex in startFrom
        BreadthFirstSearch bfs = new BreadthFirstSearch(g, g.getVertex(startFrom));
        System.out.println(bfs.getBreadFirstTraversalList());

        // print out the distances/frontiers from vertex in startFrom
        List<Vertex> vit = g.getVertices();
        Vertex source = g.getVertex(startFrom);
        for (Vertex dest : vit) {
            System.out.println("dist from " + source.getLabel() + " to " + dest + ": " + bfs.getDistanceTo(dest));
        }

    }

    public static void bacon() {
        String castFile = "data/cast.rated.txt";
        try {
            Scanner fs = new Scanner(new FileInputStream(castFile));

            Graph g = new AdjacencyListUndirectedGraph(); // you new Adjacency List Undirected Graph();

            System.out.println("reading in file: " + castFile);
            while (fs.hasNext()) {
                String line = fs.nextLine();
                Scanner movie = new Scanner(line);
                movie.useDelimiter("/");
                String movieName = movie.next();
                while (movie.hasNext()) {
                    // add each actor
                    g.addEdge(movieName, movie.next());
                }
            }
            // System.out.println(g.getVertices());

            System.out.println("calculating bfs for Bacon, Kevin");
            Vertex source = g.getVertex("Bacon, Kevin");
            BreadthFirstSearch bfs = new BreadthFirstSearch(g, source);

            Scanner in = new Scanner(System.in);
            System.out.print("Enter an actor <lastname, firstname>: ");

            String actor = in.nextLine();
            while (!actor.isEmpty()) {

                Vertex dest = g.getVertex(actor);
                System.out
                        .println("dist from " + source.getLabel() + " to " + dest + ": " + bfs.getDistanceTo(dest) / 2);

                for (Vertex v : bfs.pathTo(dest)) {
                    System.out.println("  " + v.getLabel());
                }

                System.out.print("Enter an actor <lastname, firstname>: ");
                actor = in.nextLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find the cast file: " + castFile);
        }

    }

   
    public static void test3(Graph g, String startFrom) {

        System.out.println("test 3: Depth First Search");
        // build a sample graph and print it
        g.addEdge("a", "b");
        // g.addEdge("a", "d"); // Added by me
        g.addEdge("b", "c");
        g.addEdge("b", "d");
        g.addEdge("c", "d");
        g.addEdge("c", "e");
        g.addEdge("d", "a");

        printGraph(g);

        System.out.println("Depth First");
        // List<Vertex> ld = null;
        // this list is from your post-order Depth First Traversal starting
        // at startFrom (defaults to "a");
        System.out.println("starting from: " + startFrom);
        Vertex start = g.getVertex(startFrom);
        DepthFirstSearch dfs = new DepthFirstSearch(g, start);
        List<Vertex> traversalOrder = dfs.getDepthFirstTraversalList();
        // ld = dfs.getPostOrderTraversal();
        System.out.println(traversalOrder);

    }

    public static void test4(String printGraph) {

        System.out.println("test 4: Topological Sort");

        // build a sample graph and print it - only need directed graph.
        Graph g = new AdjacencyListDirectedGraph(); // your new Adjacency List Directed Graph();
        // add edges to the graph
        g.addEdge("LA31", "LA122");
        g.addEdge("LA15", "LA31");
        g.addEdge("LA31", "LA16");
        // g.addEdge("LA126", "LA141");
        // g.addEdge("LA127", "LA16");
        // g.addEdge("LA127", "LA141");
        // g.addEdge("LA141", "LA169");
        // g.addEdge("LA22", "LA141");
        // g.addEdge("LA31", "LA32");
        // g.addEdge("LA32", "LA169");
        // g.addEdge("LA32", "LA126");
        if (printGraph.equals("Y")) {
            printGraph(g);
        }
         // Perform topological sort
         List<Vertex> topologicalOrder = topologicalSort(g);
         printTopologicalSortResult(topologicalOrder);
     }
 
     private static List<Vertex> topologicalSort(Graph g) {
         List<Vertex> topologicalOrder = new ArrayList<>();
         Map<Vertex, Integer> inDegrees = new HashMap<>();
 
         // Initialize in-degrees for each vertex
         List<Vertex> vertices = g.getVertices();
         for (Vertex v : vertices) {
             inDegrees.put(v, g.inDegree(v));
         }
 
         // Find vertices with in-degree of 0
         Queue<Vertex> zeroInDegreeVertices = new LinkedList<>();
         for (Vertex v : vertices) {
             if (inDegrees.get(v) == 0) {
                 zeroInDegreeVertices.add(v);
             }
         }
 
         // Perform DFS from each vertex with in-degree of 0
         while (!zeroInDegreeVertices.isEmpty()) {
             Vertex currentVertex = zeroInDegreeVertices.poll();
             topologicalOrder.add(currentVertex);
 
             for (Vertex neighbor : g.adjacentTo(currentVertex)) {
                 int updatedInDegree = inDegrees.get(neighbor) - 1;
                 inDegrees.put(neighbor, updatedInDegree);
 
                 if (updatedInDegree == 0) {
                     zeroInDegreeVertices.add(neighbor);
                 }
             }
         }
 
         return topologicalOrder;
     }
 
     private static void printTopologicalSortResult(List<Vertex> sortedVertices) {
         sortedVertices.sort(Vertex::compareTo);
 
         System.out.println("Topological Sort");
         System.out.print("[");
         for (int i = 0; i < sortedVertices.size(); i++) {
             System.out.print(sortedVertices.get(i).getLabel());
             if (i < sortedVertices.size() - 1) {
                 System.out.print(", ");
             }
         }
         System.out.println("]");
     }
        //     System.out.println("Topological Sort");
        // TopologicalSort topologicalSort = new TopologicalSort(g);
        // List<Vertex> lt = topologicalSort.getTopologicalOrder(); // this list from your Topological Sort of graph g;
        // System.out.println(lt);
        // } else if (printGraph.equals("N")) {
        //     System.out.println("Topological Sort");
        //     TopologicalSort topologicalSort = new TopologicalSort(g);
        //     List<Vertex> lt = topologicalSort.getTopologicalOrder(); // this list from your Topological Sort of graph g;
        //     System.out.println(lt);
        // }

        

    }


