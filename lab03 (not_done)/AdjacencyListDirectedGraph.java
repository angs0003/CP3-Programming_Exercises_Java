import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyListDirectedGraph extends Graph {
    // We need to store vertices and their adjacent vertices
    private Map<Vertex, List<Vertex>> adjacencyList;

    public AdjacencyListDirectedGraph() {
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
    //     // For directed graph, we only add the edge in one direction
    //     adjacencyList.get(v).add(w);
    // }


    public void addEdge(Vertex v, Vertex w) {
        // Ensure v and w are vertices in the graph
        addVertex(v);
        addVertex(w);
    
        // Add edge from v to w
        List<Vertex> vNeighbors = adjacencyList.getOrDefault(v, new ArrayList<>());
        vNeighbors.add(w);
        adjacencyList.put(v, vNeighbors);
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
