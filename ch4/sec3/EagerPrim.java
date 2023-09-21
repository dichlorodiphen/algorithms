import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class EagerPrim {
    private static record Edge(int v, int w, double weight) {
    }

    public static void main(String[] args) {
        final List<Edge> edges = List.of(
                new Edge(0, 1, 0.3),
                new Edge(1, 2, 0.4),
                new Edge(2, 3, 0.1),
                new Edge(1, 3, 0.6),
                new Edge(1, 2, 0.2));
        System.out.println(eagerPrim(edges, 4));
    }

    public static List<Edge> eagerPrim(List<Edge> edges, int n) {
        final List<List<Edge>> graph = buildGraph(edges, n);
        final List<Edge> minEdgeTo = new ArrayList<>();
        final NavigableSet<Integer> candidates =
                new TreeSet<>(
                        (a, b) -> Double.compare(minEdgeTo.get(a).weight(),
                                minEdgeTo.get(b).weight()));
        final Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; i++) {
            minEdgeTo.add(null);
        }
        minEdgeTo.set(0, new Edge(0, 0, Double.MAX_VALUE));

        candidates.add(0);
        while (!candidates.isEmpty()) {
            visit(candidates.pollFirst(), graph, seen, minEdgeTo, candidates);
        }

        minEdgeTo.remove(0);
        return minEdgeTo;
    }

    private static List<List<Edge>> buildGraph(List<Edge> edges, int n) {
        final List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final List<Edge> adj = new ArrayList<>();
            for (Edge e : edges) {
                if (e.v() == i || e.w() == i) {
                    adj.add(e);
                }
            }
            graph.add(adj);
        }

        return graph;
    }

    private static void visit(int v, List<List<Edge>> graph, Set<Integer> seen,
            List<Edge> minEdgeTo, NavigableSet<Integer> candidates) {
        seen.add(v);
        for (Edge e : graph.get(v)) {
            final int w = e.v() == v ? e.w() : e.v();
            if (!seen.contains(w)) {
                final Edge currentMin = minEdgeTo.get(w);
                if (currentMin == null || e.weight() <= currentMin.weight()) {
                    minEdgeTo.set(w, e);
                }
                candidates.remove(w);
                candidates.add(w);
            }
        }
    }
}
