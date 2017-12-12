
/*
 *  MapViewJComponent.java
 *
 *  Created on 06 August 2001, 14:12
 */
package jfreerails.client.view.map;

import java.awt.Point;
import java.awt.Rectangle;

/**
 *@author     Luke Lindsay
 *@created    01 November 2001
 *@version
 */

public abstract class MapViewJComponent
	extends javax.swing.JComponent
	implements javax.swing.Scrollable, NewMapView {

	/**
	 *  Description of the Field
	 */
	protected NewMapView mapView;

	/**
	 *  Description of the Field
	 */
	//protected NewMapView tiledBackgroundPainter;

	/**
	 *  Creates new MapViewJComponent
	 *
	 *@param  tiledBackgroundPainter  Description of the Parameter
	 *@param  mapView                 Description of the Parameter
	 */

	public MapViewJComponent(NewMapView mapView) {
		//this.tiledBackgroundPainter = tiledBackgroundPainter;
		this.mapView = mapView;
		this.setPreferredSize(mapView.getMapSizeInPixels());
	}

	/**
	 *  Description of the Method
	 *
	 *@param  g  Description of the Parameter
	 */
	public void paint(java.awt.Graphics g) {
		java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
		java.awt.Rectangle r = this.getVisibleRect();
		mapView.paintRect(g2, r);
	}

	/**
	 *  Gets the scrollableUnitIncrement attribute of the MapViewJComponent
	 *  object
	 *
	 *@param  rectangle    Description of the Parameter
	 *@param  orientation  Description of the Parameter
	 *@param  direction    Description of the Parameter
	 *@return              The scrollableUnitIncrement value
	 */
	public int getScrollableUnitIncrement(
		java.awt.Rectangle rectangle,
		int orientation,
		int direction) {
		if (javax.swing.SwingConstants.VERTICAL == orientation) {
			return mapView.getTileSize().height;
		} else {
			return mapView.getTileSize().width;
		}
	}

	/**
	 *  Gets the scrollableTracksViewportWidth attribute of the
	 *  MapViewJComponent object
	 *
	 *@return    The scrollableTracksViewportWidth value
	 */
	public boolean getScrollableTracksViewportWidth() {
		return false;
	}

	/**
	 *  Gets the scrollableBlockIncrement attribute of the MapViewJComponent
	 *  object
	 *
	 *@param  rectangle    Description of the Parameter
	 *@param  orientation  Description of the Parameter
	 *@param  direction    Description of the Parameter
	 *@return              The scrollableBlockIncrement value
	 */
	public int getScrollableBlockIncrement(
		java.awt.Rectangle rectangle,
		int orientation,
		int direction) {
		if (javax.swing.SwingConstants.VERTICAL == orientation) {
			int best =
				((rectangle.height / mapView.getTileSize().height) - 2)
					* mapView.getTileSize().height;
			if (best > 0) {
				return best;
			} else {
				return rectangle.height;
			}
		} else {
			int best =
				((rectangle.width / mapView.getTileSize().width) - 2)
					* mapView.getTileSize().width;
			if (best > 0) {
				return best;
			} else {
				return rectangle.width;
			}
		}
	}

	/**
	 *  Gets the scrollableTracksViewportHeight attribute of the
	 *  MapViewJComponent object
	 *
	 *@return    The scrollableTracksViewportHeight value
	 */
	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

	/**
	 *  Gets the preferredScrollableViewportSize attribute of the
	 *  MapViewJComponent object
	 *
	 *@return    The preferredScrollableViewportSize value
	 */
	public java.awt.Dimension getPreferredScrollableViewportSize() {
		return this.getPreferredSize();
	}
	public boolean isRectVisible(Rectangle r) {

		Rectangle visRect = this.getVisibleRect();
		if ((r.x < visRect.x)
			|| (r.y < visRect.y)
			|| ((r.x + r.width) > (visRect.x + visRect.width))
			|| ((r.y + r.height) > (visRect.y + visRect.height))) {
			return false;
		} else {
			return true;
		}

	}
	public void centerOnTile(Point tile) {

		float scale = mapView.getScale();
		Rectangle visRect = new Rectangle(this.getVisibleRect());
		visRect.x = (int) (tile.x * scale - (visRect.width / 2));
		visRect.y = (int) (tile.y * scale - (visRect.height / 2));
		this.scrollRectToVisible(visRect);
	}
	public boolean isWrappedVertically(){
		return false;
	}
	public boolean isWrappedHorizontally(){
		return false;
	}

}