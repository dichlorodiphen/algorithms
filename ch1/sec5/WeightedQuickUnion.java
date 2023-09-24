package ch1.sec5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * WeightedQuickUnion is an implementation of UnionFind that guarantees
 * logarithmic runtime by maintaining balanced trees.
 */
public class WeightedQuickUnion implements UnionFind {

    int numComponents;

    final List<Integer> ids;

    // Indexed by root.
    final List<Integer> treeSizes;

    /**
     * Initializes with n elements (0 to n-1).
     * 
     * @param n the number of elements
     */
    public WeightedQuickUnion(int n) {
        numComponents = n;
        ids = new ArrayList<>(n);
        treeSizes = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            ids.add(i);
            treeSizes.add(1);
        }
    }

    @Override
    public void union(int p, int q) {
        final int pRoot = find(p);
        final int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }

        if (treeSizes.get(pRoot) < treeSizes.get(qRoot)) {
            ids.set(qRoot, pRoot);
            treeSizes.set(qRoot, treeSizes.get(qRoot) + treeSizes.get(pRoot));
        } else {
            ids.set(pRoot, qRoot);
            treeSizes.set(pRoot, treeSizes.get(qRoot) + treeSizes.get(pRoot));
        }
        numComponents--;
    }

    @Override
    public int find(int p) {
        int current = p;
        while (ids.get(current) != current) {
            current = ids.get(current);
        }

        return current;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int count() {
        return numComponents;
    }

    @Override
    public List<Integer> getIDs() {
        return Collections.unmodifiableList(ids);
    }
}
