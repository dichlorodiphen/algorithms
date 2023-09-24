import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import ch1.sec5.UnionFind;
import ch1.sec5.WeightedQuickUnion;

public class Kruskal {
    private static record Edge(int v, int w, double weight) {
    }

    public static void main(String[] args) {
        final Set<Edge> edges = Set.of(new Edge(0, 1, 0.2), new Edge(1, 2, 0.3),
                new Edge(1, 3, 0.15), new Edge(2, 3, 0.9));
        System.out.println(kruskal(edges, 4));
    }

    private static List<Edge> kruskal(Set<Edge> edges, int n) {
        final PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(Edge::weight));
        final UnionFind uf = new WeightedQuickUnion(n);
        final List<Edge> mst = new ArrayList<>();
        pq.addAll(edges);
        while (!pq.isEmpty()) {
            final Edge e = pq.poll();
            if (uf.connected(e.v(), e.w())) {
                continue;
            }

            uf.union(e.v(), e.w());
            mst.add(e);
        }

        return mst;
    }
}
