package ch4.sec3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

public class LazyPrim {
    private static class Edge {
        public int v;

        public int w;

        public double weight;

        public Edge(int v, int w, double weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.v, this.w, this.weight);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || !(o instanceof Edge)) {
                return false;
            }

            final Edge that = (Edge) o;

            return this.v == that.v && this.w == that.w
                    && this.weight == that.weight;
        }

        @Override
        public String toString() {
            return "[" + this.v + " " + this.w + "]";
        }

        public int other(int a) {
            if (this.v == a) {
                return this.w;
            }

            return this.v;
        }
    }

    public static void main(String[] args) {
        final Set<Edge> edges = Set.of(new Edge(0, 1, 0.2), new Edge(1, 2, 0.3),
                new Edge(1, 3, 0.15), new Edge(2, 3, 0.9));
        System.out.println(lazyPrim(graphFromEdges(edges, 4)));
    }

    private static List<List<Edge>> graphFromEdges(Set<Edge> edges, int n) {
        final List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final List<Edge> adj = new ArrayList<>();
            for (Edge e : edges) {
                if (e.v == i || e.w == i) {
                    adj.add(e);
                }
            }
            graph.add(adj);
        }

        return graph;
    }

    private static List<Edge> lazyPrim(List<List<Edge>> graph) {
        final Set<Integer> seen = new HashSet<>();
        final PriorityQueue<Edge> pq = new PriorityQueue<>(
                (a, b) -> Double.compare(a.weight, b.weight));
        final List<Edge> mst = new LinkedList<>();

        // Add initial vertex.
        seen.add(0);
        graph.get(0).forEach(pq::add);

        // Build MST.
        while (!pq.isEmpty()) {
            final Edge e = pq.poll();
            if (seen.contains(e.v) && seen.contains(e.w)) {
                continue;
            }

            mst.add(e);
            if (!seen.contains(e.v)) {
                visit(e.v, graph, seen, pq);
            } else {
                visit(e.w, graph, seen, pq);
            }
        }

        return mst;
    }

    private static void visit(int v, List<List<Edge>> graph, Set<Integer> seen,
            PriorityQueue<Edge> pq) {
        seen.add(v);
        for (Edge e : graph.get(v)) {
            if (!seen.contains(e.other(v))) {
                pq.add(e);
            }
        }
    }
}
