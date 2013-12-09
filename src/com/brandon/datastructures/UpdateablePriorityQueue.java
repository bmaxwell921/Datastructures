package com.brandon.datastructures;

/**
 * Interface used to define behavior for priority queues that allow updating
 * of the values associated with a given element in the queue. 
 * 
 * Values are sorted using the natural ordering of V
 * @author Brandon
 *
 * @param <E>
 * 				The actual elements in the queue
 * @param <V>
 * 				The values associated with elements.
 */
public interface UpdateablePriorityQueue<E, V extends Comparable<? super V>> {
	
	/**
	 * Adds the given element to the queue, using value to maintain the order
	 * of elements.
	 * @throws IllegalArgumentException if the value is null or if this element 
	 * 		is already in the queue
	 * @param element
	 * @param value
	 */
	public void enqueue(E element, V value);
	
	/**
	 * Dequeues the element at the 'top' of the priority queue. The top is defined
	 * as the smallest element as defined by the natural ordering of V.
	 * @throws UnderflowException if the queue is empty
	 * @return
	 */
	public PQEntry<E, V> dequeue();
	
	/**
	 * Updates the given element to have the given value. Optional reordering of the
	 * queue takes place to ensure that the queue will always remove the smallest element.
	 * @param element
	 * @param value
	 * @return
	 */
	public boolean update(E element, V value);
	
	/**
	 * Returns whether the queue is empty or not
	 * @return
	 */
	public boolean isEmpty();
	
	/**
	 * Returns the number of elements currently in the queue.
	 * @return
	 */
	public int size();
	
	/**
	 * Convenience class used to box up elements and their associated values
	 * @author Brandon
	 *
	 * @param <E>
	 * @param <V>
	 */
	public interface PQEntry<E, V extends Comparable<? super V>> {
		public E getElement();
		public void setElement(E element);
		
		public V getValue();
		public void setValue(V value);
	}
}
