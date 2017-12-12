/*
 * Created on Nov 14, 2003
 */
package jfreerails.util;

import junit.framework.TestCase;

/**
 *
 *  @author Luke  
 *
 */
public class SynchronizedQueueTest extends TestCase {

	public void testPut() {
		
		SynchronizedQueue queue = new SynchronizedQueue();
		assertEquals("There should be no items in the queue yet.", 0, queue.length());
		queue.add(new Integer(1));
		assertEquals("There should be 1 in the queue now.", 1, queue.length());
	}

	public void testPopAll() {
		SynchronizedQueue queue = new SynchronizedQueue();
		
		for (int i = 0 ; i < 10 ; i ++){
			queue.add(new Integer(i));
		}
		assertEquals(10, queue.length());
		
		Object [] items = queue.getAll();
		assertEquals("There should be no items in the queue now.", 0, queue.length());
		assertNotNull(items);
		for (int i = 0 ; i < 10 ; i ++){
			assertEquals(new Integer(i), items[i]);			
		}
	}

}
