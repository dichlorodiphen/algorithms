package ch1.sec5;

import java.util.ArrayList;
import java.util.List;

/**
 * QuickFind is an implementation of the union-find algorithm that maintains
 * the invariant that all elements in a component have the same identifier in
 * our identifier map. This method is called quick-find because it enables us
 * to perform find() operations in constant time.
 */
public class QuickFind implements UnionFind {

    private final List<Integer> ids;

    private int numComponents;

    /**
     * Initialize with n elements (0 to n - 1).
     * 
     * @param n the number of elements
     */
    public QuickFind(int n) {
        this.ids = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            ids.add(i);
        }
        this.numComponents = n;
    }

    public void union(int p, int q) {
        if (ids.get(p).equals(ids.get(q))) {
            return;
        }

        final int oldID = ids.get(p);
        final int newID = ids.get(q);
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i) == oldID) {
                ids.set(i, newID);
            }
        }
        numComponents--;
    }

    public int find(int p) {
        return ids.get(p);
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return numComponents;
    }

    public List<Integer> getIDs() {
        return ids;
    }
}
