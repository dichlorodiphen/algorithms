import java.util.*;

public class Topological {
  public static void main(String[] args) {
    final int[][] edges = {{0, 1}, {0, 6}, {0, 5}, {2, 0}, {2, 3}, {3, 5}, {5, 4}, {6, 4}, {6, 9}, {7, 6}, {8, 7}, {9, 10}, {9, 12}, {9, 11}, {11, 12}};
    final int n = 13;
    System.out.println(topsort(edges, n));
  }

  private static List<Integer> topsort(int[][] edges, int n) {
    final List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int[] e : edges) {
      graph.get(e[0]).add(e[1]);
    }

    final Set<Integer> seen = new HashSet<>();
    final List<Integer> sorted = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      if (!seen.contains(i)) {
        dfs(graph, i, seen, sorted);
      }
    }
    Collections.reverse(sorted);

    return sorted;
  }

  private static void dfs(List<List<Integer>> graph, int current, Set<Integer> seen, List<Integer> sorted) {
    seen.add(current);
    for (int n : graph.get(current)) {
      if (!seen.contains(n)) {
        dfs(graph, n, seen, sorted);
      }
    }
    sorted.add(current);
  }
}

