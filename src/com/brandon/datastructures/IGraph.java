package com.brandon.datastructures;

/**
 * Interface for a graph datastructure
 * @author Brandon
 *
 */
public interface IGraph<V> {

	/**
	 * Adds a node to the graph
	 * @param data
	 */
	public void addNode(V data);
	
	/**
	 * Removes the given node from the graph.
	 * Can have memory leaks
	 * @param data
	 */
	public void removeNode(V data);
	
	/**
	 * Completely removes the given node by deleting
	 * edges to it
	 * @param data
	 */
	public void removeNodeComp(V data);
	
	/**
	 * Returns whether the given node exists in the graph
	 * @param data
	 * @return
	 */
	public boolean containsNode(V data);
	
	/**
	 * Adds an edge from src to dest
	 * @param src
	 * @param dest
	 */
	public void addEdge(V src, V dest);
	
	/**
	 * Removes the given edge from the graph
	 * @param src
	 * @param dest
	 */
	public void removeEdge(V src, V dest);
	
	/**
	 * Returns whether the given edge exists in the graph
	 * @param src
	 * @param dest
	 * @return 
	 */
	public boolean containsEdge(V src, V dest);
	
	/**
	 * Traverses the graph from the start node, informing the 
	 * given listener when a node is visited. Traversal is 
	 * done depth first
	 * @param start
	 * @param list
	 */
	public void traverseDepth(V start, TraverseListener<V> list);
	
	/**
	 * Traverses the graph from the start node, informing the 
	 * given listener when a node is visited. Traversal is 
	 * done breadth first
	 * @param start
	 * @param list
	 */
	public void traverseBreadth(V start, TraverseListener<V> list);
	
	public interface TraverseListener<V> {
		public void visit(V node);
	}
}