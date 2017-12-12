/*
 * Created on Nov 14, 2003
 *
 */
package jfreerails.util;

import java.util.LinkedList;

/**
 * This class is a synchronized queue intended to be used to pass objects between threads.
 * 
 * @author Luke
 *
 */
public class SynchronizedQueue {

	private LinkedList queue = new LinkedList();
	
	/** Adds the specified object to the back of the queue.
	 * Warning: if the object is mutable decide whether you want
	 * to pass the object itself or a copy of the object.
	 */
	public synchronized void add(Object o){
		queue.add(o);
	}
	
	/** Removes all the objects from the queue and puts them in 
	 * the returned array.  The first element in the array is the object
	 * that was at the front of the queue.  If there are no objects in 
	 * the queue, an empty array is returned.
	 */
	public synchronized Object[] getAll(){
		int length = queue.size();
		Object[] items = new Object[length];
		for(int i = 0 ; i < length ; i ++){
			items[i] = queue.removeFirst();
		}		
		return items;
	}
	
	public synchronized int length(){
		return queue.size();
	}	
}
