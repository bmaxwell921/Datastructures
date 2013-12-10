package com.brandon.datastructures.impl;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import com.brandon.datastructures.UpdateablePriorityQueue;

/**
 * A priorityQueue that allows clients to update the value associated with a
 * specific element.
 * 
 * This implementation makes use of a Heap for the underlying implementation,
 * along with a Mapping of element to index location into the heap. This allows
 * for constant time finds for element locations, and logarithmic time updates
 * of element values.
 * 
 * @author Brandon
 * 
 * @param <E>
 * @param <V>
 */
public class LocationMapPQ<E, V extends Comparable<? super V>> implements
		UpdateablePriorityQueue<E, V> {
	/**
	 * Default size of the underlying datastructures
	 */
	private static final int DEFAULT_SIZE = 10;

	/**
	 * The index of the top of the queue (heap). This is where we remove from.
	 */
	private static final int QUEUE_TOP = 0;

	/**
	 * The heap where elements are stored
	 */
	private PQEntry<E, V>[] heap;

	/**
	 * Map that holds each element's location in the heap
	 */
	private Map<E, Integer> locMap;

	/**
	 * The size of the queue
	 */
	private int size;

	public LocationMapPQ() {
		this(DEFAULT_SIZE);
	}

	@SuppressWarnings("unchecked")
	public LocationMapPQ(int initialSize) {
		heap = (PQEntry<E, V>[]) Array.newInstance(PQEntry.class, initialSize);
		locMap = new HashMap<E, Integer>(initialSize);

		this.size = 0;
	}

	@Override
	public void enqueue(E element, V value) {
		if (value == null) {
			throw new IllegalArgumentException("Null values are not allowed");
		}
		if (locMap.containsKey(element)) {
			throw new IllegalArgumentException("Duplicate elements are not allowed.");
		}
		
		// Add it to the queue, breaking heap ordering
		heap[size] = new LocMapEntry(element, value);
		
		// Fix heap ordering
		percolateUp(size);
		++size;
	}
	
	private void percolateUp(int index) {
		// STOP! Top of the heap
		if (index == QUEUE_TOP) return;
		
		// Swap with the parent if the parent is greater
		int parent = this.parent(index);
		
		if (lessThan(heap[parent].getValue(), heap[index].getValue()) 
				|| heap[parent].getValue().equals(heap[index].getValue())) {
			return;
		}
		
		swap(parent, index);
		
		// Continue percolating up
		percolateUp(parent);
	}

	/*
	 * -------------------------------------------------------------------------------------------
	 * Getting Children Methods
	 * -------------------------------------------------------------------------------------------
	*/
	
	// (child - 1) / 2
	private int parent(int child) {
		return (child - 1) >>> 1;
	}
	
	// parent * 2 + 1
	private int firstChild(int parent) {
		return (parent << 1) + 1;
	}
	
	// parent * 2 + 2
	private int secondChild(int parent) {
		return (parent << 1) + 2;
	}
	/*
	 * -------------------------------------------------------------------------------------------
	*/
	
	
	/*
	 * -------------------------------------------------------------------------------------------
	 * Comparison Methods
	 * -------------------------------------------------------------------------------------------
	*/
	private boolean lessThan(V lhs, V rhs) {
		return lhs.compareTo(rhs) < 0;
	}
	
	private boolean greaterThan(V lhs, V rhs) {
		return lhs.compareTo(rhs) > 0;
	}
	/*
	 * -------------------------------------------------------------------------------------------
	*/
	
	/*
	 *  Swaps the element at heap[first] with heap[second] and update the location map
	 *  to know where each element ends up
	 */
	private void swap(int first, int second) {
		// Let the location map know where the elements are now
		locMap.put(heap[first].getElement(), second);
		locMap.put(heap[second].getElement(), first);
		
		// Actually swap them
		PQEntry<E, V> temp = heap[first];
		heap[first] = heap[second];
		heap[second] = temp;
	}

	private class LocMapEntry implements PQEntry<E, V> {
		
		// The element
		public E element;
		
		// The value associated with the element
		public V value;
		
		public LocMapEntry(E element, V value) {
			this.element = element;
			this.value = value;
		}

		@Override
		public E getElement() {
			return element;
		}

		@Override
		public void setElement(E element) {
			this.element = element;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public void setValue(V value) {
			this.value = value;
		}
	}

	@Override
	public PQEntry<E, V> dequeue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(E element, V value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		return size <= 0;
	}

	@Override
	public int size() {
		return size;
	}
}
