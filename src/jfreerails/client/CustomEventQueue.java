/*
 * (c) Copyright 2001 MyCorporation.
 * All Rights Reserved.
 */
package jfreerails.client;
import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.util.LinkedList;

/**
 * @version 	1.0
 * @author
 */
final public class CustomEventQueue extends EventQueue {
    /**
     * @associates AWTEvent 
     */
	LinkedList secondEventQueue = new LinkedList();

	protected void dispatchEvent(AWTEvent event) {

		synchronized (secondEventQueue) {

			secondEventQueue.add(event);
		}
	}
	public void dispatchAllEvents() throws InterruptedException {
		synchronized (secondEventQueue) {

			while (secondEventQueue.size() != 0) {
				AWTEvent event = (AWTEvent) secondEventQueue.removeFirst();

				super.dispatchEvent(event);

			}
		}

	}
}