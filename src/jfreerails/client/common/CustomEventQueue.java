/*
 * (c) Copyright 2001 MyCorporation.
 * All Rights Reserved.
 */
package jfreerails.client.common;
import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.util.LinkedList;

/** This class changes the way awt events are dispatched.  When dispatchEvent(..) is called,
 * the specified event is added to a second queue rather than dispatched right away.
 * The events stored in this second queue can be dispatched by calling dispatchAllEvents().
 * This allows a thread other than the awt event dispatch thread to control when events get
 * dispatched.
 * @version 1.0
 * @author Luke Lindsay
 */
final public class CustomEventQueue extends EventQueue {
    /**
     *  AWTEvent
     */
    LinkedList secondEventQueue = new LinkedList();

    /**
     * @param event
     */
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