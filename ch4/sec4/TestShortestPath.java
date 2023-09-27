package ch4.sec4;

public class TestShortestPath {
    public static void main(String[] args) {
        final WeightedDigraph g = new WeightedDigraph(4);
        g.addEdge(new Edge(0, 1, 0.2));
        g.addEdge(new Edge(1, 2, 0.3));
        g.addEdge(new Edge(2, 3, 0.1));
        g.addEdge(new Edge(3, 0, 0.4));
        g.addEdge(new Edge(0, 2, 0.4));

        test(GenericShortestPath.class, g, 0, 3);
        test(Dijkstra.class, g, 0, 3);
    }

    private static <T extends ShortestPath> void test(Class<T> algorithm, WeightedDigraph g, int start, int end) {
        try {
            final ShortestPath sp = algorithm.getDeclaredConstructor(WeightedDigraph.class, int.class).newInstance(g,
                    start);
            System.out.println(sp.shortestPath(end));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
