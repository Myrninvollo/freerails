package jfreerails.client.view.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class BoringMapView implements NewMapView {
	int[][] map;

	float scale = 10;

	Dimension tileSize = new Dimension(10, 10);
	
	NewMapView parent=null;

	public BoringMapView(int[][] map) {
		this.map = map;
	}

	public Dimension getMapSizeInTiles() {
		return new Dimension(map.length, map[0].length);
	}

	public Dimension getTileSize() {
		return tileSize;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
		this.tileSize = new Dimension((int) scale, (int) scale);
	}

	public void paintTile(Graphics g, Point tile) {
		int rgbIn = map[tile.x][tile.y];
		Color col = new Color(rgbIn);
		g.setColor(col);
		g.fillRect(
			tile.x * tileSize.width,
			tile.y * tileSize.height,
			tileSize.width,
			tileSize.height);
	}

	public void paintRectangleOfTiles(Graphics g, Rectangle tilesToPaint) {
		Point tile = new Point();
		for (tile.x = tilesToPaint.x;
			tile.x < (tilesToPaint.x + tilesToPaint.width);
			tile.x++) {

			for (tile.y = tilesToPaint.y;
				tile.y < (tilesToPaint.y + tilesToPaint.height);
				tile.y++) {
				paintTile(g, tile);

			}
		}
	}

	public void paintRect(Graphics g, Rectangle visibleRect) {
		Point tile = new Point();
		for (tile.x = 0;
			tile.x < map.length;
			tile.x++) {

			for (tile.y = 0;
				tile.y < map[0].length;
				tile.y++) {
				paintTile(g, tile);

			}
		}
	}

	public NewMapView getParentMapView() {
		return parent;
	}

	public void setParentMapView(NewMapView parent) {
		this.parent=parent;
	}

	public void refreshTile(Point tile) {
		
	}

	public void refresh() {
		
	}

	public Dimension getMapSizeInPixels() {
		return new Dimension(map.length*tileSize.width, map[0].length*tileSize.height);

	}
	public void refreshTileAndNotifyParent(Point tile) {
		parent.refreshTileAndNotifyParent(tile);

	}
	public void refreshAndNotifyParent() {
		parent.refreshAndNotifyParent();

	}
}