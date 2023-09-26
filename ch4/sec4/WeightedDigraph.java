package ch4.sec4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WeightedDigraph {

    private final List<List<Edge>> adj;

    /**
     * Initialize an empty weighted digraph with n vertices.
     * 
     * @param n the number of vertices
     */
    public WeightedDigraph(int n) {
        adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(Edge e) {
        adj.get(e.v()).add(e);
    }

    public Iterable<Edge> adj(int v) {
        return adj.get(v);
    }

    public Iterable<Edge> edges() {
        final Set<Edge> edges = new HashSet<>();
        for (List<Edge> e : adj) {
            edges.addAll(e);
        }

        return edges;
    }

    @Override
    public String toString() {
        return adj.toString();
    }
}
