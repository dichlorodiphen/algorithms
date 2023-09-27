package ch4.sec2;

import java.util.*;

public class DigraphDFS { 
  public static void main(String[] args) {
    System.out.println(path(new int[][]{{1, 0}, {0, 2}, {2, 3}}, 4, 1, 3));
    System.out.println(path(new int[][]{{1, 0}, {0, 3}, {4, 5}}, 6, 1, 5));

    return;
  }

  public static List<Integer> path(int[][] routes, int n, int start, int end) {
    // Build graph.
    final List<List<Integer>> graph = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int[] r : routes) {
      graph.get(r[0]).add(r[1]);
    }

    // DFS to find path.
    return dfs(graph, start, end, new HashSet<>(), new ArrayList<>());
  }

  private static List<Integer> dfs(List<List<Integer>> graph, int node, int target, Set<Integer> seen, List<Integer> path) {
    if (node == target) {
      return path;
    }

    seen.add(node);
    for (Integer n : graph.get(node)) {
      path.add(n);
      final List<Integer> result = dfs(graph, n, target, seen, path);
      if (result != null) {
        return result;
      }
      path.remove(path.size() - 1);
    }

    return null;
  }
}

