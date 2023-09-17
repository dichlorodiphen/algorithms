import java.util.*;

public class ShortestDigraphPath {
  public static void main(String[] args) {
    final int[][] routes = {{0, 1}, {1, 2}, {3, 4}};
    final int start = 0;
    final int end = 4;
    final int n = 5;
    System.out.println(shortestPath(routes, start, end, n));
  }

  private static List<Integer> shortestPath(int[][] routes, int start, int end, int n) {
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
    final Deque<Integer> q = new LinkedList<>();
    q.addFirst(start);
    while (!q.isEmpty()) {
      final int current = q.removeLast();
      seen.add(current);
      for (int neighbor : graph.get(current)) {
        if (!seen.contains(neighbor)) {
          q.addFirst(neighbor);
          parent.set(neighbor, current);
        }
      }
    }

    final List<Integer> path = new ArrayList<>();
    if (parent.get(end) == null) {
      return null;
    }
    for (int node = end; node != start; node = parent.get(node)) {
      path.add(node); 
    }
    path.add(start);
    Collections.reverse(path);

    return path;
  }
}

