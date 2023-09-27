package ch4.sec4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Dijkstra's algorithm calculates the shortest path between two vertices in a
 * directed graph with nonnegative weights.
 * 
 * We begin by initializing the distance to the start vertex to 0 and all other
 * distances to positive infinity. Then, we relax and add to the tree a non-tree
 * vertex with the lowest distance, continuing until all vertices are on the
 * tree or no non-tree vertex has a finite distance.
 */
public class Dijkstra implements ShortestPath {

    private final WeightedDigraph graph;

    private final List<Edge> edgeTo;

    private final List<Double> distTo;

    /**
     * Initializes the algorithm with the given graph and start vertex.
     * 
     * @param graph a weighted digraph
     * @param start the vertex to start the path from
     */
    public Dijkstra(WeightedDigraph graph, int start) {
        this.graph = graph;
        edgeTo = new ArrayList<>();
        distTo = new ArrayList<>();
        for (int i = 0; i < graph.getNumberOfVertices(); i++) {
            edgeTo.add(null);
            distTo.add(Double.POSITIVE_INFINITY);
        }
        distTo.set(start, 0.0);
        precomputeShortestPaths(start);
    }

    @Override
    public List<Edge> shortestPath(int end) {
        if (Double.isInfinite(distTo.get(end))) {
            return null;
        }

        final List<Edge> path = new ArrayList<>();
        for (Edge x = edgeTo.get(end); x != null; x = edgeTo.get(x.v())) {
            path.add(x);
        }
        Collections.reverse(path);

        return path;
    }

    private void precomputeShortestPaths(int start) {
        final NavigableSet<Integer> candidates = new TreeSet<>(Comparator.comparing(distTo::get));
        candidates.add(start);
        while (!candidates.isEmpty()) {
            final int v = candidates.pollFirst();
            relax(v, candidates);
        }
    }

    private void relax(int v, NavigableSet<Integer> candidates) {
        for (Edge e : graph.adj(v)) {
            if (distTo.get(v) + e.weight() <= distTo.get(e.w())) {
                distTo.set(e.w(), distTo.get(v) + e.weight());
                edgeTo.set(e.w(), e);
                candidates.add(e.w());
            }
        }
    }
}
