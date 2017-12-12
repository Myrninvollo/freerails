package jfreerails.client.view.map;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import jfreerails.client.tileview.TileViewList;
import jfreerails.client.trackview.TrackPieceViewList;
import jfreerails.world.std_track.TrackAndTerrainTileMap;

public class DetailMapView implements MapView {

	private final MapViewLayer background;
	
	private final Dimension mapSizeInPixels;

	public DetailMapView(
		TrackAndTerrainTileMap map,
		TileViewList tiles,
		TrackPieceViewList trackPieceViewList) {
			
		background = new SquareTileBackgroundPainter(new BackgroundMapView(map, tiles, trackPieceViewList), 30);
		Dimension mapSize=map.getMapSize();
		mapSizeInPixels=new Dimension(mapSize.width*30,mapSize.height*30);
	}

	public float getScale() {
		return 30;
	}

	public Dimension getMapSizeInPixels() {
		return mapSizeInPixels;
	}

	public void paintTile(Graphics g, int tileX, int tileY) {
		background.paintTile(g, tileX, tileY);
	}

	public void paintRectangleOfTiles(
		Graphics g,
		int x,
		int y,
		int width,
		int height) {
		background.paintRectangleOfTiles(g, x, y, width, height);
	}

	public void refreshTile(int x, int y) {
		background.refreshTile(x, y);
	}

	public void refreshRectangleOfTiles(int x, int y, int width, int height) {
		background.refreshRectangleOfTiles(x, y, width, height);
	}

	public void paintRect(Graphics g, Rectangle visibleRect) {
		background.paintRect(g, visibleRect);
	}
}
