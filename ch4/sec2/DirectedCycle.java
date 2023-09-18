import java.util.*;

public class DirectedCycle {
  public static void main(String[] args) {
    final int[][] edges = {{0, 5}, {3, 5}, {4, 3}, {5, 4}};
    final int n = 6;
    System.out.println(findCycle(edges, n));
  }

  private static List<Integer> findCycle(int[][] edges, int n) {
    final List<List<Integer>> graph = new ArrayList<>();
    final List<Integer> parent = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
      parent.add(null);
    }
    for (int[] e : edges) {
      graph.get(e[0]).add(e[1]);
    }

    final Set<Integer> seen = new HashSet<>();
    for (int i = 0; i < n; i++) {
      if (!seen.contains(i)) {
        final List<Integer> result = dfs(graph, i, seen, parent);
        if (result != null) {
          return result;
        }
      }
    }

    return null;
  }

  private static List<Integer> dfs(List<List<Integer>> graph, int current, Set<Integer> seen, List<Integer> parent) {
    seen.add(current);
    for (int neighbor : graph.get(current)) {
      if (seen.contains(neighbor)) {
        final List<Integer> cycle = new ArrayList<>();
        for (int node = current; node != neighbor; node = parent.get(node)) {
          cycle.add(node);
        }
        cycle.add(neighbor);
        cycle.add(current);

        return cycle;
      }

      parent.set(neighbor, current);
      final List<Integer> result = dfs(graph, neighbor, seen, parent);
      if (result != null) {
        return result;
      }
    }

    return null;
  }
}

