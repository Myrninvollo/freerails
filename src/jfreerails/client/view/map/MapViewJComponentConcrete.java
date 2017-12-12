
/*
* MapViewJComponent.java
*
* Created on 31 July 2001, 13:56
*/
package jfreerails.client.view.map;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.SwingUtilities;
import jfreerails.client.FreerailsCursor;
import jfreerails.client.event.CursorEvent;
import jfreerails.move.source.TrackMoveProducer;

/**
*
* @author  Luke Lindsay
* @version
*/

final public class MapViewJComponentConcrete
	extends MapViewJComponent
	implements jfreerails.client.event.CursorEventListener {

	private TrackMoveProducer trackBuilder;

	private FreerailsCursor cursor;

	final private class MapViewJComponentMouseAdapter
		extends java.awt.event.MouseAdapter {

		public void mousePressed(java.awt.event.MouseEvent mouseEvent) {
			if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
				int x = mouseEvent.getX();
				int y = mouseEvent.getY();
				Dimension tileSize = mapView.getTileSize();
				cursor.TryMoveCursor(
					new java.awt.Point(
						x / tileSize.width,
						y / tileSize.height));
				MapViewJComponentConcrete.this.requestFocus();
			}
		}
	}

	protected void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;

		//java.awt.Rectangle  r = this.getVisibleRect();

		//tiledBackgroundPainter.paint( g2, r );
		cursor.cursorRenderer.paintCursor(g2, new java.awt.Dimension(30, 30));
	}

	public MapViewJComponentConcrete(
		NewMapView mv,
		TrackMoveProducer trackBuilder) {

		super(new SquareTileBackgroundPainter(mv));
		this.setBorder(null);
		this.trackBuilder = trackBuilder;
		this.cursor = new FreerailsCursor(mv);
		cursor.addCursorEventListener(this);
		this.addMouseListener(new MapViewJComponentMouseAdapter());
		this.addKeyListener(cursor);
		this.requestFocus();
	}

	public void cursorJumped(CursorEvent ce) {

		trackBuilder.doTrackBuilderAction(ce.newPosition);

		Point tile = new Point();
		for (tile.x = ce.newPosition.x - 1;
			tile.x < ce.newPosition.x + 2;
			tile.x++) {
			for (tile.y = ce.newPosition.y - 1;
				tile.y < ce.newPosition.y + 2;
				tile.y++) {
				mapView.refreshTile(tile);
			}
		}

		reactToCursorMovement(ce);

	}

	public void cursorOneTileMove(CursorEvent ce) {
		if (null != trackBuilder) {

			trackBuilder.performAction(ce.oldPosition, ce.vector);
			Point tile = new Point();
			for (tile.x = ce.oldPosition.x - 1;
				tile.x < ce.oldPosition.x + 2;
				tile.x++) {
				for (tile.y = ce.oldPosition.y - 1;
					tile.y < ce.oldPosition.y + 2;
					tile.y++) {
					mapView.refreshTile(tile);
				}
			}

		} else {
			System.out.println("No track builder available!");
		}
		reactToCursorMovement(ce);
	}

	public void cursorKeyPressed(CursorEvent ce) {
		reactToCursorMovement(ce);
	}

	public void updateTiles(java.awt.Rectangle rect) {

	}

	public void updateTile(java.awt.Point tileCoodinate) {

	}

	private void reactToCursorMovement(CursorEvent ce) {
		java.awt.Dimension tileSize = mapView.getTileSize();
		Rectangle vr = this.getVisibleRect();
		Rectangle rectangleSurroundingCursor = new Rectangle(0, 0, 1, 1);
		rectangleSurroundingCursor.setLocation(
			(ce.newPosition.x - 1) * tileSize.width,
			(ce.newPosition.y - 1) * tileSize.height);
		rectangleSurroundingCursor.setSize(
			tileSize.width * 3,
			tileSize.height * 3);
		if (!(vr.contains(rectangleSurroundingCursor))) {
			int x = ce.newPosition.x * tileSize.width - vr.width / 2;
			int y = ce.newPosition.y * tileSize.height - vr.height / 2;
			this.scrollRectToVisible(new Rectangle(x, y, vr.width, vr.height));
		}
		this.repaint(
			(ce.newPosition.x - 1) * tileSize.width,
			(ce.newPosition.y - 1) * tileSize.height,
			tileSize.width * 3,
			tileSize.height * 3);
		this.repaint(
			(ce.oldPosition.x - 1) * tileSize.width,
			(ce.oldPosition.y - 1) * tileSize.height,
			tileSize.width * 3,
			tileSize.height * 3);
	}
	public Dimension getTileSize() {
		return mapView.getTileSize();

	}

	public float getScale() {
		return mapView.getScale();

	}

	public void setScale(float scale) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	public void paintTile(Graphics g, Point tile) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	public void paintRectangleOfTiles(Graphics g, Rectangle tilesToPaint) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	public void paintRect(Graphics g, Rectangle visibleRect) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	public NewMapView getParentMapView() {
		throw new UnsupportedOperationException("Method not yet implemented.");

	}

	public void setParentMapView(NewMapView parent) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	public void refreshTile(Point tile) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	public void refresh() {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	public Dimension getMapSizeInPixels() {
		return mapView.getMapSizeInPixels();
	}
	public Dimension getMapSizeInTiles() {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}
	public void refreshTileAndNotifyParent(Point tile) {
		throw new UnsupportedOperationException("Method not yet implemented.");

	}
	public void refreshAndNotifyParent() {
		throw new UnsupportedOperationException("Method not yet implemented.");

	}

	

	//public Dimension getMapSizeInPixels();

	
	

	public void paintRect(Graphics g) {
	}

	
	public void refreshRectangleOfTiles(Rectangle tilesToRefresh) {
	}

	public void reset() {
	}

	public boolean canDoScale(float scale) {
		return false;
		 }

	public float[] getPreferedScales() {
		return null;
		 }

}