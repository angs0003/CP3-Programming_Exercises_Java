import java.util.List;
import java.util.ArrayList;

public interface Graph {
    public void getEdge(int i);


    public void addEdge(int source, int target, int weight);
    List<Edge> getAdjacent(int vertex);
    int size();

}
    class Edge {
        int target;
        int weight;
    
        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }

        @Override

        public String toString() {
            return "Edge{" + "target=" + target + ", weight=" + weight + '}';
        }
}





