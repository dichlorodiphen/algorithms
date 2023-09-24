# Algorithms

A collection of algorithm implementations from *Algorithms* by Robert Sedgewick and Kevin Wayne. Note that this will not be exhaustive; it serves more as a convenient way to sync my work across devices.

## To-do

* [ ] Set up unit testing with JUnit

## Chapter 1: Fundamentals

### 1.5: Case Study: Union-Find

* Quick-find algorithm ([ch1/sec5/QuickFind.java](ch1/sec5/QuickFind.java))
* Weighted quick-union algorithm ([ch1/sec5/WeightedQuickUnion.java](ch1/sec5/WeightedQuickUnion.java))

## Chapter 4: Graphs

### 4.1: Undirected graphs

* Find connected components in an undirected graph ([ch4/sec1/ConnectedComponents.java](ch4/sec1/ConnectedComponents.java))

### 4.2: Directed graphs

* Find a path in a digraph using DFS ([ch4/sec2/DigraphDFS.java](ch4/sec2/DigraphDFS.java))
* Determine whether or not a node is reachable from a set of start nodes using DFS ([ch4/sec2/DigraphReachable.java](ch4/sec2/DigraphReachable.java))
* Reverse a digraph ([ch4/sec2/ReverseDigraph.java](ch4/sec2/ReverseDigraph.java))
* Find shortest path in a digraph using BFS ([ch4/sec2/ShortestDigraphPath.java](ch4/sec2/ShortestDigraphPath.java))
* Find a directed cycle in a digraph using DFS ([ch4/sec2/DirectedCycle.java](ch4/sec2/DirectedCycle.java))
* Find a topological ordering for a digraph ([ch4/sec2/Topological.java](ch4/sec2/Topological.java))
* Find strongly connected components in a digraph using the Kosaraju-Sharir algorithm ([ch4/sec2/KosarajuSharir.java](ch4/sec2/KosarajuSharir.java))

### 4.3: Minimum spanning trees

* Find an MST using Prim's algorithm (lazy removal of invalid edges from priority queue) ([ch4/sec3/LazyPrim.java](ch4/sec3/LazyPrim.java))
* Find an MST using Prim's algorithm (priority queue does not maintain invalid edges) ([ch4/sec3/EagerPrim.java](ch4/sec3/EagerPrim.java))
* Kruskal's algorithm ([ch4/sec3/Kruskal.java](ch4/sec3/Kruskal.java))
