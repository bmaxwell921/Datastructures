package com.brandon.datastructures.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.brandon.datastructures.IGraph;

public class DirectedGraph<V> implements IGraph<V> {

	private Map<V, Set<V>> nodeList;

	/**
	 * Creates a new empty directed graph
	 */
	public DirectedGraph() {
		nodeList = new HashMap<>();
	}

	@Override
	public void addNode(V data) {
		Set<V> lst = nodeList.get(data);
		if (lst != null) {
			// This node already exists
			return;
		}
		// Create the node by adding in an empty edge list
		nodeList.put(data, new HashSet<V>());
	}

	@Override
	public void removeNode(V data) {
		if (!nodeList.containsKey(data)) {
			return;
		}
		nodeList.remove(data);
	}

	@Override
	public void removeNodeComp(V data) {
		this.removeNode(data);
		removeAsEdge(data);
	}

	@Override
	public boolean containsNode(V data) {
		return nodeList.containsKey(data);
	}

	// Finds all nodes connecting to dest and removes the edge
	private void removeAsEdge(V dest) {
		Set<V> srcs = new HashSet<>();
		for (V src : nodeList.keySet()) {
			if (containsEdge(src, dest)) {
				srcs.add(src);
			}
		}
		for (V src : srcs) {
			removeEdge(src, dest);
		}
	}

	@Override
	public void addEdge(V src, V dest) {
		Set<V> dests = nodeList.get(src);
		if (dests == null) {
			dests = new HashSet<>();
		}
		dests.add(dest);
		nodeList.put(src, dests);
	}

	@Override
	public void removeEdge(V src, V dest) {
		if (!containsNode(src)) {
			return;
		}
		nodeList.get(src).remove(dest);
	}

	@Override
	public boolean containsEdge(V src, V dest) {
		if (!containsNode(src)) {
			return false;
		}
		if (!containsNode(dest)) {
			return false;
		}

		return nodeList.get(src).contains(dest);
	}

	@Override
	public void traverseDepth(V start, TraverseListener<V> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void traverseBreadth(V start, TraverseListener<V> list) {
		// TODO Auto-generated method stub

	}

}
