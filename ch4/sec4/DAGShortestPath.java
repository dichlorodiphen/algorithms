package ch4.sec4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch4.sec2.Topological;

/**
 * This algorithm finds the shortest path in a weighted DAG that may have
 * negative weights.
 * 
 * We find the shortest path by relaxing the vertices in topological order.
 */
public class DAGShortestPath implements ShortestPath {

    private final WeightedDigraph graph;

    private final List<Edge> edgeTo;

    private final List<Double> distTo;

    /**
     * Initializes the algorithm with the given graph and start vertex.
     * 
     * @param graph a weighted digraph
     * @param start the vertex to start the path from
     */
    public DAGShortestPath(WeightedDigraph graph, int start) {
        this.graph = graph;
        edgeTo = new ArrayList<>();
        distTo = new ArrayList<>();
        for (int v = 0; v < graph.getNumberOfVertices(); v++) {
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
        final List<Integer> topOrder = Topological.topsort(graph);
        for (int v : topOrder) {
            relax(v);
        }
    }

    private void relax(int v) {
        for (Edge e : graph.adj(v)) {
            if (distTo.get(v) + e.weight() <= distTo.get(e.w())) {
                distTo.set(e.w(), distTo.get(v) + e.weight());
                edgeTo.set(e.w(), e);
            }
        }
    }
}
