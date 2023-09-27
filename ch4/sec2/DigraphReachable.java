package ch4.sec2;

import java.util.*;

public class DigraphReachable {
  public static void main(String[] args) {
    final int[][] routes = {{0, 1}, {2, 1}, {2, 3}, {3, 4}};
    final Set<Integer> nodes = Set.of(1, 2);
    final int target = 4;
    final int n = 5;
    System.out.println(findPath(routes, nodes, target, n));
  }

  private static List<Integer> findPath(int[][] routes, Set<Integer> nodes, int target, int n) {
    final List<List<Integer>> graph = new ArrayList<>();
    final List<Integer> parent = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
      parent.add(null);
    }
    for (int[] r : routes) {
      graph.get(r[0]).add(r[1]);
    }

    final Set<Integer> seen = new HashSet<>();
    for (int start : nodes) {
      if (dfs(graph, start, target, seen, parent)) {
        final List<Integer> path = new ArrayList<>();
        for (int current = target; current != start; current = parent.get(current)) {
          path.add(current);
        }
        path.add(start);
        Collections.reverse(path);

        return path;
      }
    }

    return null;
  }

  private static boolean dfs(List<List<Integer>> graph, int current, int end, Set<Integer> seen, List<Integer> parent) {
    if (current == end) {
      return true;
    }

    seen.add(current);
    for (int n : graph.get(current)) {
      if (!seen.contains(n)) {
        parent.set(n, current);
        if (dfs(graph, n, end, seen, parent)) {
          return true;
        }
      }
    }

    return false;
  }
}

