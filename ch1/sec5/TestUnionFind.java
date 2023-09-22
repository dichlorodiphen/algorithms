package ch1.sec5;

import java.util.List;

public class TestUnionFind {
    public static void main(String[] args) {
        final int[][] connections = {
                { 4, 3 },
                { 3, 8 },
                { 6, 5 },
                { 9, 4 },
                { 2, 1 },
                { 8, 9 },
                { 5, 0 },
                { 7, 2 },
                { 6, 1 },
                { 1, 0 },
                { 6, 7 }
        };
        System.out.println(test(QuickFind.class, connections));
    }

    private static <T extends UnionFind> List<Integer> test(Class<T> algorithm, int[][] connections) {
        try {
            int n = 0;
            for (int[] c : connections) {
                n = Math.max(Math.max(n, c[0]), c[1]);
            }
            n++;

            final UnionFind uf = algorithm.getDeclaredConstructor(int.class).newInstance(n);
            for (int[] c : connections) {
                uf.union(c[0], c[1]);
            }

            return uf.getIDs();
        } catch (Exception e) {
            System.out.println(e);

            return null;
        }

    }
}
