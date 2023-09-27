package ch4.sec4;

import java.util.List;

public interface ShortestPath {
    /**
     * Find a shortest path to end in the graph.
     * 
     * @param end the end vertex
     * @return a shortest path from start to end
     */
    public List<Edge> shortestPath(int end);
}
