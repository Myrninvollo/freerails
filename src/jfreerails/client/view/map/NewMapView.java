package jfreerails.client.view.map;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * @stereotype tested
 * @testcase jfreerails.client.view.map.TestNewMapView 
 * 
 */
public interface NewMapView {
	
	 Dimension getMapSizeInTiles();
	
	 Dimension getTileSize();
	
	 float getScale();

	 void setScale(float scale);

	 void paintTile(Graphics g, Point tile);

	 void paintRectangleOfTiles(Graphics g, Rectangle tilesToPaint);

	 void paintRect(Graphics g, Rectangle visibleRect);

	 NewMapView getParentMapView();

	 void setParentMapView(NewMapView parent);

	 void refresh();

	 void refreshTile(Point tile);
	
	 void refreshTileAndNotifyParent(Point tile);

	 void refreshAndNotifyParent();

	 Dimension getMapSizeInPixels();

	}