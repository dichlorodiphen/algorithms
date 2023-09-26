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
public class GenericShortestPath {
    public static void main(String[] args) {
        final GenericShortestPath sp = new GenericShortestPath();
        final WeightedDigraph g = new WeightedDigraph(4);
        g.addEdge(new Edge(0, 1, 0.2));
        g.addEdge(new Edge(1, 2, 0.3));
        g.addEdge(new Edge(2, 3, 0.1));
        g.addEdge(new Edge(3, 0, 0.4));
        g.addEdge(new Edge(0, 2, 0.4));

        System.out.println(sp.shortestPath(g, 0, 1));
    }

    public List<Edge> shortestPath(WeightedDigraph g, int start, int end) {
        final List<Edge> edgeTo = new ArrayList<>();
        final List<Double> distTo = new ArrayList<>();
        for (int i = 0; i < g.getNumberOfVertices(); i++) {
            edgeTo.add(null);
            distTo.add(Double.POSITIVE_INFINITY);
        }
        distTo.set(start, 0.0);

        boolean hasEligibleEdges = true;
        final Iterable<Edge> edges = g.edges();
        while (hasEligibleEdges) {
            hasEligibleEdges = false;
            for (Edge e : edges) {
                hasEligibleEdges =
                        hasEligibleEdges || relax(g, e, edgeTo, distTo);
            }
        }

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

    private boolean relax(WeightedDigraph g, Edge e, List<Edge> edgeTo,
            List<Double> distTo) {
        if (distTo.get(e.v()) + e.weight() < distTo.get(e.w())) {
            distTo.set(e.w(), distTo.get(e.v()) + e.weight());
            edgeTo.set(e.w(), e);

            return true;
        }

        return false;
    }
}
