package com.brandon.datastructures.impl;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import com.brandon.datastructures.UpdateablePriorityQueue.PQEntry;

/**
 * A priorityQueue that allows clients to update the value associated with 
 * a specific element.
 * 
 * This implementation makes use of a Heap for the underlying implementation, along 
 * with a Mapping of element to index location into the heap. This allows for constant 
 * time finds for element locations, and logarithmic time updates of element values.
 * @author Brandon
 *
 * @param <E>
 * @param <V>
 */
public class LocationMapPQ<E, V extends Comparable<? super V>> {
	/**
	 * Default size of the underlying datastructures
	 */
	private static final int DEFAULT_SIZE = 10;
	
	/**
	 * The index of the top of the queue (heap).
	 * This is where we remove from.
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
	
	
}
