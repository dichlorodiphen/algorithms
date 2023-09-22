package ch1.sec5;

import java.util.List;

public interface UnionFind {
    /**
     * Adds a connection between p and q.
     * 
     * @param p an element
     * @param q another element
     */
    public void union(int p, int q);

    /**
     * Gets the component identifier for p.
     * 
     * @param p an element
     * @return the component identifier for p
     */
    public int find(int p);

    /**
     * Returns whether or not p and q are connected.
     * 
     * @param p an element
     * @param q another element
     * @return whether or no p and q are connected
     */
    public boolean connected(int p, int q);

    /**
     * Gets the number of components.
     * 
     * @return the number of components
     */
    public int count();

    /**
     * Gets the list of identifiers.
     * 
     * @return the list of identifier
     */
    public List<Integer> getIDs();
}