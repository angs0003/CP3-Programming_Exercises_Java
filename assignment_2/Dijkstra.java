import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
    public static int[] shortestPaths(Graph graph, int source) {
        int[] dist = new int[graph.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.vertex;
            int distance = node.distance;

            if (distance > dist[u]) continue;

            for (Edge edge : graph.getAdjacent(u)) {
                int v = edge.target;
                int alt = dist[u] + edge.weight;
                if (alt < dist[v]) {
                    dist[v] = alt;
                    pq.add(new Node(v, alt));
                }
            }
        }

        return dist;
    }

    static class Node implements Comparable<Node> {
        int vertex;
        int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(distance, other.distance);
        }
    }
}
