import java.util.*;

public class ReverseDiagraph {
  public static void main(String[] args) {
    final int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {3, 0}};
    final List<List<Integer>> digraph = buildDigraph(edges, 4);
    System.out.println(digraph);
    System.out.println(reverseDigraph(digraph));
  }

  private static List<List<Integer>> buildDigraph(int[][] edges, int n) {
    final List<List<Integer>> dg = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      dg.add(new ArrayList<>());
    }
    for (int[] e : edges) {
      dg.get(e[0]).add(e[1]);
    }

    return dg;
  }

  private static List<List<Integer>> reverseDigraph(List<List<Integer>> dg) {
    final List<List<Integer>> reversed = new ArrayList<>();
    for (int i = 0; i < dg.size(); i++) {
      reversed.add(new ArrayList<>());
    }
    for (int i = 0; i < dg.size(); i++) {
      final List<Integer> adj = dg.get(i);
      for (Integer n : adj) {
        reversed.get(n).add(i);
      }
    }

    return reversed;
  }
}
