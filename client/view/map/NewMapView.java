package jfreerails.client.view.map;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * @stereotype tested
 * @testcase jfreerails.client.view.map.TestNewMapView 
 */
public interface NewMapView {
	
	public Dimension getMapSizeInTiles();
	
	public Dimension getTileSize();
	
	public float getScale();

	public void setScale(float scale);

	public void paintTile(Graphics g, Point tile);

	public void paintRectangleOfTiles(Graphics g, Rectangle tilesToPaint);

	public void paintRect(Graphics g, Rectangle visibleRect);

	public NewMapView getParentMapView();

	public void setParentMapView(NewMapView parent);

	public void refresh();

	public void refreshTile(Point tile);
	
	public void refreshTileAndNotifyParent(Point tile);

	public void refreshAndNotifyParent();

	public Dimension getMapSizeInPixels();

    /**
     * @link
     * @shapeType PatternLink
     * @pattern TestCase
     * @clientRole tested
     * @supplierRole tests
     * @hidden 
     */
    /*# private TestNewMapView _testNewMapView; */

    /**
     * @link
     * @shapeType PatternLink
     * @pattern TestCase
     * @clientRole tested
     * @supplierRole tests
     * @hidden 
     */
    /*# private TestNewMapView _testNewMapView1; */
	}