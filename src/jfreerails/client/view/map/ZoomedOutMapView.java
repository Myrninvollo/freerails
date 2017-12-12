package jfreerails.client.view.map;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import jfreerails.move.MapUpdateMove;
import jfreerails.move.Move;
import jfreerails.move.receiver.MoveReceiver;
import jfreerails.move.status.MoveStatus;
import jfreerails.world.std_track.TrackAndTerrainTileMap;
import jfreerails.world.std_track.TrackTileMap;
import jfreerails.world.terrain.TerrainMap;

final public class ZoomedOutMapView implements NewMapView, MoveReceiver {

	private Class mapUpdateMoveClass;

	private TerrainMap terrainMap;

	private TrackTileMap trackSystem;

	private BufferedImage mapImage;//, scaledMapImage;

	private NewMapView parent;

	protected GraphicsConfiguration defaultConfiguration =
		GraphicsEnvironment
			.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice()
			.getDefaultConfiguration();

	public ZoomedOutMapView(TrackAndTerrainTileMap map) {
		this.terrainMap = map;
		this.trackSystem = map;
		this.refresh();
		//mapImage.setRGB(0, 0, mapWidth, mapHeight, rgbArrary, 0, mapWidth);

		try {
			mapUpdateMoveClass = Class.forName("jfreerails.move.MapUpdateMove");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	/*
	 * @see NewMapView#getMapSizeInTiles()
	 */
	public Dimension getMapSizeInTiles() {
		return new Dimension(terrainMap.getWidth(), terrainMap.getHeight());
	}

	/*
	 * @see NewMapView#getTileSize()
	 */
	public Dimension getTileSize() {
		return new Dimension(1, 1);
	}

	/*
	 * @see NewMapView#getScale()
	 */
	public float getScale() {
		return 1;
	}

	/*
	 * @see NewMapView#setScale(float)
	 */
	public void setScale(float scale) {
	}

	/*
	 * @see NewMapView#paintTile(Graphics, Point)
	 */
	public void paintTile(Graphics g, Point tile) {
		g.drawImage(mapImage, 0, 0, null);
	}

	/*
	 * @see NewMapView#paintRectangleOfTiles(Graphics, Rectangle)
	 */
	public void paintRectangleOfTiles(Graphics g, Rectangle tilesToPaint) {
		g.drawImage(mapImage, 0, 0, null);
	}

	/*
	 * @see NewMapView#paintRect(Graphics, Rectangle)
	 */
	public void paintRect(Graphics g, Rectangle visibleRect) {
		g.drawImage(mapImage, 0, 0, null);
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
		this.parent = parent;
	}

	/*
	 * @see NewMapView#refreshTile(Point)
	 */
	public void refreshTile(Point tile) {

		int rgb;
//		TrackNode node = trackSystem.getTrackNode(tile);
//		if (node != null) {
//			rgb = node.getRGB();
//		} else {
//			rgb = terrainMap.
			rgb = trackSystem.getRGB(tile);
		//}

		mapImage.setRGB(tile.x, tile.y, rgb);

		//mapImage.setRGB(tile.x, tile.y, 0);
	}

	/*
	 * @see NewMapView#refresh()
	 */
	public void refresh() {
		int mapWidth = terrainMap.getWidth();
		int mapHeight = terrainMap.getHeight();
		mapImage =
			defaultConfiguration.createCompatibleImage(
				mapWidth,
				mapHeight,
				Transparency.OPAQUE);

		//int[] rgbArrary=new int[mapWidth*mapWidth];

		Point tile = new Point();

		for (tile.x = 0; tile.x < mapWidth; tile.x++) {
			for (tile.y = 0; tile.y < mapHeight; tile.y++) {
				refreshTile(tile);
				//rgbArrary[x+(y*mapWidth)]=terrainMap.getTerrainTileType(x,y);
			}
		}
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
		return new Dimension(terrainMap.getWidth(), terrainMap.getHeight());
	}
	public MoveStatus processMove(Move move) {
		if (mapUpdateMoveClass.isInstance(move)) {
			//System.out.println("updating tiles");
			Rectangle r = ((MapUpdateMove) move).getUpdatedTiles();
			//System.out.println(r);
			Point tile = new Point();
			for (tile.x = r.x; tile.x < (r.x + r.width); tile.x++) {
				for (tile.y = r.y; tile.y < (r.y + r.height); tile.y++) {
					int rgb;
					//TrackNode node = trackSystem.getTrackNode(tile);
					//if (node != null) {
					//	rgb = node.getRGB();
						//System.out.println("track here: "+tile.toString());
					//} else {
						rgb = trackSystem.getRGB(tile);
						//System.out.println("no track here: "+tile.toString());
					//}

					mapImage.setRGB(tile.x, tile.y, rgb);
				}
			}
			parent.refreshAndNotifyParent();

		} 
		return MoveStatus.MOVE_RECEIVED;

	}

}