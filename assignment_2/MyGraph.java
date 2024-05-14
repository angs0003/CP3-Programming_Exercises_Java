import java.util.ArrayList;
import java.util.List;

class MyGraph implements Graph {
    private final List<List<Edge>> adjacencyList;

    public MyGraph(int nNodes) {
        adjacencyList = new ArrayList<>(nNodes);
        for (int i = 0; i < nNodes; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    @Override
    public void addEdge(int source, int target, int weight) {
        adjacencyList.get(source).add(new Edge(target, weight));
    }

    @Override
    public List<Edge> getAdjacent(int vertex) {
        return adjacencyList.get(vertex);
    }

    @Override
    public int size() {
        return adjacencyList.size();
    }

    @Override
    public void getEdge(int i) {
        System.out.println(adjacencyList.get(i));
    }
}