// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Stack;

// public class TopologicalSort {

//     private List<Vertex> traversalOrder;

//     public TopologicalSort(Graph g) {
//         traversalOrder = new ArrayList<>();
//         Map<Vertex, Integer> inDegrees = new HashMap<>();

//         // Step 1: Find vertices with in-degree of 0
//         for (Vertex v : g.getVertices()) {
//             inDegrees.put(v, g.inDegree(v));
//         }

//         // Step 2: Perform DFS for vertices with in-degree of 0
//         for (Vertex v : g.getVertices()) {
//             if (inDegrees.get(v) == 0) {
//                 depthFirstSearch(v, g, new HashMap<>());
//             }
//         }
//     }

//     private void depthFirstSearch(Vertex v, Graph g, Map<Vertex, Boolean> visited) {
//         visited.put(v, true);
//         List<Vertex> neighbors = g.adjacentTo(v);
//         if (neighbors != null) {
//             // Sort neighbors lexicographically
//             neighbors.sort(Vertex::compareTo);
//             for (Vertex neighbor : neighbors) {
//                 if (!visited.containsKey(neighbor)) {
//                     depthFirstSearch(neighbor, g, visited);
//                 }
//             }
//         }
//         traversalOrder.add(v);
//     }
    
//     public List<Vertex> getTopologicalOrder() {
//         return traversalOrder;
//     }
// }


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class TopologicalSort {

    private List<Vertex> traversalOrder;

    public TopologicalSort(Graph g) {
        traversalOrder = new ArrayList<>();
        Map<Vertex, Integer> inDegrees = new HashMap<>();

        // Step 1: Find vertices with in-degree of 0
        for (Vertex v : g.getVertices()) {
            inDegrees.put(v, g.inDegree(v));
        }

        // Step 2: Perform DFS for vertices with in-degree of 0
        for (Vertex v : g.getVertices()) {
            if (inDegrees.get(v) == 0) {
                depthFirstSearch(v, g, new HashMap<>());
            }
        }
    }

    private void depthFirstSearch(Vertex v, Graph g, Map<Vertex, Boolean> visited) {
        visited.put(v, true);
        List<Vertex> neighbors = g.adjacentTo(v);
        if (neighbors != null) {
            // Sort neighbors lexicographically
            neighbors.sort(Vertex::compareTo);
            for (Vertex neighbor : neighbors) {
                if (!visited.containsKey(neighbor)) {
                    depthFirstSearch(neighbor, g, visited);
                }
            }
        }
        traversalOrder.add(v);
    }

    public List<Vertex> getTopologicalOrder() {
        return traversalOrder;
    }
}
