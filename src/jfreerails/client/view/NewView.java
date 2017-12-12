/*
 * Created on 25-Aug-2003
 *
 */
package jfreerails.client.view;

import jfreerails.client.renderer.ViewLists;
import jfreerails.world.top.ReadOnlyWorld;

/**
 * @author Luke Lindsay
 *
 */
public interface NewView {
	
	void setup(ReadOnlyWorld w, ViewLists vl, CallBacks callBacks);
	
}
