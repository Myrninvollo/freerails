
package jfreerails.client.common;
import java.awt.AWTEvent;
import java.awt.EventQueue;

/** This event queue is  synchronized on an object passed to its constructor.  This lets one control 
 * when events can be dispatched.
 * @author Luke
 *
 */

final public class CustomEventQueue2 extends EventQueue {
   
   	Object o;
   	
   	public CustomEventQueue2(Object object){
   		this.o = object;
   	}
   
    protected void dispatchEvent(AWTEvent event) {

        synchronized (o) {

			super.dispatchEvent(event);
        }
    }
}