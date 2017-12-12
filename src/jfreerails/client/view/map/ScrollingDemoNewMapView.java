package jfreerails.client.view.map;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public class ScrollingDemoNewMapView implements NewMapView {
	protected Rectangle visibleTiles;

	/** Pixels per tile.
	 */
	protected float scale;

	protected Image map;

	protected Image scaledImage;

	protected Dimension mapSizeInPixels = new Dimension();

	protected Dimension mapSizeInTiles = new Dimension();

	protected GraphicsConfiguration defaultConfiguration =
		GraphicsEnvironment
			.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice()
			.getDefaultConfiguration();

	public ScrollingDemoNewMapView(float scale) {
		java.net.URL imageURL =
			ScrollingDemoMapView.class.getResource("/scrollingdemo/simplemap.gif");
		map = new javax.swing.ImageIcon(imageURL).getImage();

		setScale(scale);
	}

	/*
	 * @see NewMapView#getTileSize()
	 */
	public Dimension getTileSize() {
		return null;
	}

	/*
	 * @see NewMapView#getScale()
	 */
	public float getScale() {
		return scale;
	}

	/*
	 * @see NewMapView#setScale(float)
	 */
	public void setScale(float scale) {
		this.scale = scale;

		this.mapSizeInPixels.height = (int) (map.getHeight(null) * scale);

		this.mapSizeInPixels.width = (int) (map.getWidth(null) * scale);

		this.scaledImage =
			defaultConfiguration.createCompatibleImage(
				mapSizeInPixels.width,
				mapSizeInPixels.height);
		Graphics g = scaledImage.getGraphics();
		g.drawImage(map, 0, 0, mapSizeInPixels.width, mapSizeInPixels.height, null);
		g.dispose();
	}

	/*
	 * @see NewMapView#paintTile(Graphics, Point)
	 */
	public void paintTile(Graphics g, Point tile) {
	}

	/*
	 * @see NewMapView#paintRectangleOfTiles(Graphics, Rectangle)
	 */
	public void paintRectangleOfTiles(Graphics g, Rectangle tilesToPaint) {
	}

	/*
	 * @see NewMapView#paintRect(Graphics, Rectangle)
	 */
	public void paintRect(Graphics g, Rectangle visibleRect) {
		//g.fillRect(visibleRect.x, visibleRect.y, visibleRect.width, visibleRect.height);
		for (int x = 0;
			x <= ((visibleRect.x + visibleRect.width) / mapSizeInPixels.width);
			x++) {
			for (int y = 0;
				y <= ((visibleRect.y + visibleRect.height) / mapSizeInPixels.height);
				y++) {
				g.drawImage(
					scaledImage,
					x * mapSizeInPixels.width,
					y * mapSizeInPixels.height,
					null);
			}
		}
	}

	/*
	 * @see NewMapView#getParentMapView()
	 */
	public NewMapView getParentMapView() {
		return null;
	}

	/*
	 * @see NewMapView#setParentMapView(NewMapView)
	 */
	public void setParentMapView(NewMapView parent) {
	}

	/*
	 * @see NewMapView#refreshTile(Point)
	 */
	public void refreshTile(Point tile) {
	}

	/*
	 * @see NewMapView#refresh()
	 */
	public void refresh() {
	}

	/*
	 * @see NewMapView#refreshTileAndNotifyParent(Point)
	 */
	public void refreshTileAndNotifyParent(Point tile) {
	}

	/*
	 * @see NewMapView#refreshAndNotifyParent()
	 */
	public void refreshAndNotifyParent() {
	}

	/*
	 * @see NewMapView#getMapSizeInPixels()
	 */
	public Dimension getMapSizeInPixels() {
		return mapSizeInPixels;
	}
	public Dimension getMapSizeInTiles() {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

}