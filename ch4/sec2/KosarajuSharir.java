package ch4.sec2;

import java.util.*;

public class KosarajuSharir {
  public static void main(String[] args) {
    final int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {3, 2}};
    final int n = 4;
    System.out.println(findStronglyConnectedComponents(edges, n)); 
  }

  private static List<List<Integer>> findStronglyConnectedComponents(int[][] edges, int n) {
    final List<List<Integer>> graph = buildGraph(edges, n);
    final List<List<Integer>> reversed = reverseGraph(graph);
    final List<Integer> topsorted = topsort(reversed);

    final Set<Integer> seen = new HashSet<>();
    final List<List<Integer>> components = new ArrayList<>();
    for (Integer node : topsorted) {
      if (!seen.contains(node)) {
        final List<Integer> component = new ArrayList<>();
        findComponent(graph, node, component, seen);
        components.add(component);
      }
    }

    return components;
  }

  private static List<List<Integer>> buildGraph(int[][] edges, int n) {
    final List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>()); 
    }
    for (int[] e : edges) {
      graph.get(e[0]).add(e[1]);
    }

    return graph;
  }

  private static List<List<Integer>> reverseGraph(List<List<Integer>> graph) {
    final List<List<Integer>> reversed = new ArrayList<>();
    for (int i = 0; i < graph.size(); i++) {
      reversed.add(new ArrayList<>());
    }
    for (int i = 0; i < graph.size(); i++) {
      final List<Integer> adj = graph.get(i);
      for (int n : adj) {
        reversed.get(n).add(i);
      }
    }

    return reversed;
  }

  private static List<Integer> topsort(List<List<Integer>> graph) {
    final Set<Integer> seen = new HashSet<>(); 
    final List<Integer> sorted = new ArrayList<>();
    for (int i = 0; i < graph.size(); i++) {
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

  private static void findComponent(List<List<Integer>> graph, int current, List<Integer> component, Set<Integer> seen) {
    seen.add(current);
    component.add(current);
    for (int n : graph.get(current)) {
      if (!seen.contains(n)) {
        findComponent(graph, n, component, seen);
      }
    }
  }
}
