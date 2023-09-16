import java.util.*;

public class ShortestPath {
  public static void main(String[] args) {
    final int[][] routes = {{0, 2}, {2, 1}, {2, 4}, {4, 3}, {3, 5}, {5, 0}, {0, 5}};
    final int n = 6;
    final int start = 0;
    final int end = 5;
    System.out.println(shortestPath(routes, n, start, end));
  }

  private static List<Integer> shortestPath(int[][] routes, int n, int start, int end) {
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int[] r : routes) {
      graph.get(r[0]).add(r[1]);
    }

    final int[] parent = bfs(graph, n, start, end);
    if (parent == null) {
      return null;
    }

    final List<Integer> path = new ArrayList<>();
    for (int x = end; x != start; x = parent[x]) {
      path.add(x);
    }
    path.add(start);
    Collections.reverse(path);
    return path;
  }

  private static int[] bfs(List<List<Integer>> graph, int n, int start, int end) {
    final int[] parent = new int[n];
    final Set<Integer> seen = new HashSet<>();
    final Deque<Integer> q = new LinkedList<>();
    q.addFirst(start);
    while (!q.isEmpty()) {
      final int current = q.removeLast();
      seen.add(current);

      if (current == end) {
        return parent;
      }

      for (Integer neighbor : graph.get(current)) {
        if (!seen.contains(neighbor)) {
          parent[neighbor] = current;
          q.addFirst(neighbor);
        }
      }
    }

    return null;
  }
}
