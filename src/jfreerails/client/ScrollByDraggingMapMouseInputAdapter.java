package jfreerails.client;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;



final public class ScrollByDraggingMapMouseInputAdapter extends MouseInputAdapter {

	Point oldScreenLocation = new Point(); //Screen location in pixels

	Point newScreenLocation = new Point(); //Screen location in pixels

	Point mapLocation = new Point(); //Location in tiles

	boolean noNeedToCenter = false; //Stop us centering twice.
	


	JComponent component;

	public void mouseDragged(MouseEvent evt) {
		if (SwingUtilities.isRightMouseButton(evt)) {
			component = (JComponent) (evt.getComponent());
			noNeedToCenter = true;
			Rectangle thisVisibleRect = component.getVisibleRect();

			oldScreenLocation.setLocation(newScreenLocation);
			newScreenLocation.x = evt.getX() - thisVisibleRect.x;
			newScreenLocation.y = evt.getY() - thisVisibleRect.y;

			Rectangle r = new Rectangle(thisVisibleRect);

			r.x -= newScreenLocation.x - oldScreenLocation.x;
			r.y -= newScreenLocation.y - oldScreenLocation.y;

			component.scrollRectToVisible(r);
			//component.scrollRectToVisible(getScrollTargetRect(r));
		}
	}

	public void mousePressed(MouseEvent evt) {
		if (SwingUtilities.isRightMouseButton(evt)) {
		
			component = (JComponent) (evt.getComponent());
			Rectangle thisVisibleRect = component.getVisibleRect();
			oldScreenLocation.setLocation(oldScreenLocation);
			newScreenLocation.x = evt.getX() - thisVisibleRect.x;
			newScreenLocation.y = evt.getY() - thisVisibleRect.y;
		}
	}
	public void mouseReleased(MouseEvent evt) {
		if (SwingUtilities.isRightMouseButton(evt)) {
			//		float scale = mapView.getScale();
			if (!noNeedToCenter) {
				component = (JComponent) (evt.getComponent());
				Rectangle thisVisibleRect = component.getVisibleRect();
				thisVisibleRect.x = evt.getX() - (thisVisibleRect.width / 2);
				thisVisibleRect.y = evt.getY() - (thisVisibleRect.height / 2);
				component.scrollRectToVisible(thisVisibleRect);

			}
			noNeedToCenter = false;
	
		}
		
	}

}