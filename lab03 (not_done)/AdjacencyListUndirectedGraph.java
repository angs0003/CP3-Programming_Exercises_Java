import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyListUndirectedGraph extends Graph {
    // We need to store vertices and their adjacent vertices
    private Map<Vertex, List<Vertex>> adjacencyList;

    public AdjacencyListUndirectedGraph() {
        adjacencyList = new HashMap<>();
    }
    
    @Override
    void addVertex(Vertex v) {
        if (!adjacencyList.containsKey(v)) {
            adjacencyList.put(v, new ArrayList<>());
        }
    }

    // @Override
    // void addEdge(Vertex v, Vertex w) {
    //     addVertex(v);
    //     addVertex(w);
    //     // For undirected graph, we need to add the edge in both directions
    //     adjacencyList.get(v).add(w);
    //     adjacencyList.get(w).add(v);
    // }

    public void addEdge(Vertex v, Vertex w) {
        // Ensure v and w are vertices in the graph
        addVertex(v);
        addVertex(w);
    
        // Add w to the adjacency list of v
        List<Vertex> vNeighbors = adjacencyList.getOrDefault(v, new ArrayList<>());
        vNeighbors.add(w);
        adjacencyList.put(v, vNeighbors);
    
        // Add v to the adjacency list of w (for an undirected graph)
        List<Vertex> wNeighbors = adjacencyList.getOrDefault(w, new ArrayList<>());
        wNeighbors.add(v);
        adjacencyList.put(w, wNeighbors);
    }

    @Override
    List<Vertex> adjacentTo(Vertex v) {
        return adjacencyList.getOrDefault(v, new ArrayList<>());
    }

    @Override
    int degree(Vertex v) {
        return adjacencyList.getOrDefault(v, new ArrayList<>()).size();
    }

    @Override
    List<Vertex> getVertices() {
        List<Vertex> vertices = new ArrayList<>(adjacencyList.keySet());
        vertices.sort(Vertex::compareTo);
        return vertices;
    }

    @Override
    boolean hasEdge(Vertex v, Vertex w) {
        return adjacencyList.containsKey(v) && adjacencyList.get(v).contains(w);
    }

    @Override
    boolean hasVertex(Vertex vertex) {
        return adjacencyList.containsKey(vertex);
    }

    @Override
    Vertex getVertex(String v) {
        for (Vertex vertex : adjacencyList.keySet()) {
            if (vertex.getLabel().equals(v)) {
                return vertex;
            }
        }
        return null;
    }

    @Override
    int inDegree(Vertex v) {
        int inDegree = 0;
        for (Vertex vertex : adjacencyList.keySet()) {
            if (adjacencyList.get(vertex).contains(v)) {
                inDegree++;
            }
        }
        return inDegree;
    }
}
