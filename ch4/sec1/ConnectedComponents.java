package ch4.sec1;

import java.util.*;

public class ConnectedComponents {
  public static void main(String[] args) {
    final int[][] routes = {{0, 6}, {6, 4}, {4, 5}, {4, 3}, {5, 0}, {0, 1}, {0, 2}, {7, 8}, {9, 10}, {9, 11}, {9, 12}, {11, 12}};
    final int n = 13;
    final List<List<Integer>> cc = findConnectedComponents(routes, n);
    System.out.println(cc);
  }

  private static List<List<Integer>> findConnectedComponents(int[][] routes, int n) {
    final List<List<Integer>> graph = buildGraph(routes, n);
    final List<List<Integer>> cc = new ArrayList<>();
    final Set<Integer> seen = new HashSet<>();
    for (int i = 0; i < n; i++) {
      if (!seen.contains(i)) {
        cc.add(dfs(graph, i, seen, new ArrayList<>()));
      }
    }

    return cc;
  }

  private static List<List<Integer>> buildGraph(int[][] routes, int n) {
    final List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int[] r : routes) {
      graph.get(r[0]).add(r[1]);
      graph.get(r[1]).add(r[0]);
    }

    return graph;
  }

  private static List<Integer> dfs(List<List<Integer>> graph, int start, Set<Integer> seen, List<Integer> connected) {
    seen.add(start);
    connected.add(start);
    
    for (int n : graph.get(start)) {
      if (!seen.contains(n)) {
        dfs(graph, n, seen, connected);
      }
    }

    return connected;
  }

  
}
