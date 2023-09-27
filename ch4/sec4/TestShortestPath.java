package ch4.sec4;

public class TestShortestPath {
    public static void main(String[] args) {
        final WeightedDigraph dag = new WeightedDigraph(8);
        final int[][] edges = {
                { 5, 4, 35 },
                { 4, 7, 37 },
                { 5, 7, 28 },
                { 5, 1, 32 },
                { 4, 0, 38 },
                { 0, 2, 26 },
                { 3, 7, 39 },
                { 1, 3, 29 },
                { 7, 2, 34 },
                { 6, 2, 40 },
                { 3, 6, 52 },
                { 6, 0, 58 },
                { 6, 4, 93 }
        };
        addEdges(dag, edges);
        final int start = 1;
        final int end = 2;

        test(GenericShortestPath.class, dag, start, end);
        test(Dijkstra.class, dag, start, end);
        test(DAGShortestPath.class, dag, start, end);
    }

    private static void addEdges(WeightedDigraph g, int[][] edges) {
        for (int[] e : edges) {
            g.addEdge(new Edge(e[0], e[1], e[2]));
        }
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
