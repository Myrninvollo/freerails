package jfreerails.client.view.map;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * @version 	1.0
 * @author
 */
public class ParentMapview implements NewMapView {
	
	Graphics screenGraphics;
	
	NewMapView childMapView;
	
	NewMapView parent=null;
	
	public ParentMapview(NewMapView childMapView, Graphics screenGraphics){
		this.screenGraphics=screenGraphics;
		this.childMapView=childMapView;
		childMapView.setParentMapView(this);	
	}

	/*
	 * @see NewMapView#getMapSizeInTiles()
	 */
	public Dimension getMapSizeInTiles() {
		return childMapView.getMapSizeInTiles();
	}

	/*
	 * @see NewMapView#getTileSize()
	 */
	public Dimension getTileSize() {
		return childMapView.getTileSize();
	}

	/*
	 * @see NewMapView#getScale()
	 */
	public float getScale() {
		return childMapView.getScale();
	}

	/*
	 * @see NewMapView#setScale(float)
	 */
	public void setScale(float scale) {
		childMapView.setScale(scale);
	}

	/*
	 * @see NewMapView#paintTile(Graphics, Point)
	 */
	public void paintTile(Graphics g, Point tile) {
		childMapView.paintTile(g, tile);
		
	}

	/*
	 * @see NewMapView#paintRectangleOfTiles(Graphics, Rectangle)
	 */
	public void paintRectangleOfTiles(Graphics g, Rectangle tilesToPaint) {
		childMapView.paintRectangleOfTiles(g,tilesToPaint);
	}

	/*
	 * @see NewMapView#paintRect(Graphics, Rectangle)
	 */
	public void paintRect(Graphics g, Rectangle visibleRect) {
		childMapView.paintRect(g, visibleRect);
	}

	/*
	 * @see NewMapView#getParentMapView()
	 */
	public NewMapView getParentMapView() {
		return parent;
	}

	/*
	 * @see NewMapView#setParentMapView(NewMapView)
	 */
	public void setParentMapView(NewMapView parent) {
		this.parent=parent;
	}

	/*
	 * @see NewMapView#refresh()
	 */
	public void refresh() {
		Dimension size=childMapView.getMapSizeInPixels();
		Rectangle r=new Rectangle(size);
		childMapView.paintRect(screenGraphics, r);
	}

	/*
	 * @see NewMapView#refreshTile(Point)
	 */
	public void refreshTile(Point tile) {
		childMapView.paintTile(screenGraphics, tile);
	}

	/*
	 * @see NewMapView#refreshTileAndNotifyParent(Point)
	 */
	public void refreshTileAndNotifyParent(Point tile) {
		childMapView.paintTile(screenGraphics,tile);
		//parent.refreshTile(tile);  no need, this is the parent
	}

	/*
	 * @see NewMapView#refreshAndNotifyParent()
	 */
	public void refreshAndNotifyParent() {
		Dimension size=childMapView.getMapSizeInPixels();
		Rectangle r=new Rectangle(size);
		childMapView.paintRect(screenGraphics, r);
	
	}

	/*
	 * @see NewMapView#getMapSizeInPixels()
	 */
	public Dimension getMapSizeInPixels() {
		return childMapView.getMapSizeInPixels();
	}

}
