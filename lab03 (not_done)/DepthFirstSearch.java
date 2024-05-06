// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Stack;

// public class DepthFirstSearch {

//     private List<Vertex> traversalOrder;
//     private Map<Vertex, Boolean> visited;

//     public DepthFirstSearch(Graph g) {
//         traversalOrder = new ArrayList<>();
//         visited = new HashMap<>();

//         List<Vertex> vertices = g.getVertices();
//         for (Vertex v : vertices) {
//             visited.put(v, false);
//         }

//         for (Vertex v : vertices) {
//             if (!visited.get(v)) {
//                 dfs(v, g);
//             }
//         }
//     }

//     private void dfs(Vertex v, Graph g) {
//         visited.put(v, true);
//         List<Vertex> neighbors = g.adjacentTo(v);
//         if (neighbors != null) {
//             for (Vertex neighbor : neighbors) {
//                 if (!visited.get(neighbor)) {
//                     dfs(neighbor, g);
//                 }
//             }
//         }
//         traversalOrder.add(v);
//     }

//     public List<Vertex> getDepthFirstTraversalList() {
//         return traversalOrder;
//     }
// }

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepthFirstSearch {

    private List<Vertex> traversalOrder;
    private Map<Vertex, Boolean> visited;

    public DepthFirstSearch(Graph g, Vertex startFrom) {
        traversalOrder = new ArrayList<>();
        visited = new HashMap<>();

        List<Vertex> vertices = g.getVertices();
        for (Vertex v : vertices) {
            visited.put(v, false);
        }

        dfs(startFrom, g);
    }

    private void dfs(Vertex v, Graph g) {
        visited.put(v, true);
        List<Vertex> neighbors = g.adjacentTo(v);
        if (neighbors != null) {
            neighbors.sort(Vertex::compareTo); // Sort neighbors lexicographically
            for (Vertex neighbor : neighbors) {
                if (!visited.get(neighbor)) {
                    dfs(neighbor, g);
                }
            }
        }
        traversalOrder.add(v);
    }

    public List<Vertex> getDepthFirstTraversalList() {
        return traversalOrder;
    }
}
