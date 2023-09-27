package ch4.sec4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An implementation of the generic shortest-paths algorithm.
 * 
 * Found on page 651, this algorithm relaxes edges in the given graph until no
 * more edges can be relaxed.
 */
public class GenericShortestPath implements ShortestPath {

    private final WeightedDigraph graph;

    private final List<Edge> edgeTo;

    private final List<Double> distTo;

    /**
     * Initializes the algorithm with the given graph and start vertex.
     * 
     * @param graph a weighted digraph
     * @param start the vertex to start the path from
     */
    public GenericShortestPath(WeightedDigraph graph, int start) {
        this.graph = graph;
        edgeTo = new ArrayList<>();
        distTo = new ArrayList<>();
        for (int i = 0; i < graph.getNumberOfVertices(); i++) {
            edgeTo.add(null);
            distTo.add(Double.POSITIVE_INFINITY);
        }
        distTo.set(start, 0.0);
        precomputeShortestPaths();
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

    private void precomputeShortestPaths() {
        boolean hasEligibleEdges = true;
        final Iterable<Edge> edges = graph.edges();
        while (hasEligibleEdges) {
            hasEligibleEdges = false;
            for (Edge e : edges) {
                hasEligibleEdges = hasEligibleEdges || relax(e);
            }
        }
    }

    private boolean relax(Edge e) {
        if (distTo.get(e.v()) + e.weight() < distTo.get(e.w())) {
            distTo.set(e.w(), distTo.get(e.v()) + e.weight());
            edgeTo.set(e.w(), e);

            return true;
        }

        return false;
    }
}
