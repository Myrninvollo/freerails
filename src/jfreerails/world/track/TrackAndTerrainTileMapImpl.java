/*
 * (c) Copyright 2001 MyCorporation.
 * All Rights Reserved.
 */
package jfreerails.world.track;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.net.URL;

import jfreerails.world.terrain.TerrainTileTypesList;
import jfreerails.world.terrain.TerrainType;

/**
 * @version 	1.0
 */
final public class TrackAndTerrainTileMapImpl implements TrackAndTerrainTileMap {

	private final TrackPieceAndTerrainCompositeTile[][] map;
	private final Rectangle mapRect;

	private final TerrainTileTypesList terrainTileTypesList;
	private final TrackRuleList trackRules;

	/*
	 * @see TrackTileMap#getTrackPiece(Point)
	 */
	public TrackPiece getTrackPiece(Point point) {
		return map[point.x][point.y];
	}

	/*
	 * @see TrackTileMap#getTrackTypeNumber(Point)
	 */
	public int getTrackTypeNumber(Point point) {
		return map[point.x][point.y].getTrackRule().getRuleNumber();
	}

	/*
	 * @see TrackTileMap#getTrackGraphicNumber(Point)
	 */
	public int getTrackGraphicNumber(Point point) {
		return map[point.x][point.y].getTrackGraphicNumber();
	}

	/*
	 * @see TileMap#getMapSize()
	 */
	public Dimension getMapSize() {
		return new Dimension(mapRect.width, mapRect.height);
	}

	/*
	 * @see TrackTileMap#boundsContain(Point)
	 */
	public boolean boundsContain(Point location) {
		return mapRect.contains(location);
	}

	/*
	 * @see TrackTileMap#trackIsAllowed(Point, int)
	 */
	public boolean trackIsAllowed(Point location, int type) {
		return true;
	}

	/*
	 * @see TrackTileMap#setTrackPiece(Point, TrackPiece)
	 */
	public void setTrackPiece(Point point, TrackPiece trackPiece) {
		TrackPieceAndTerrainCompositeTile tile;
		TerrainType terrainType=map[point.x][point.y].getTerrainType();
		tile=new TrackPieceAndTerrainCompositeTile(terrainType, trackPiece);
		map[point.x][point.y]=tile;
	}

	/*
	 * @see TerrainMap#getTerrainTileType(int, int)
	 */
	public int getTerrainTileType(int x, int y) {
		return map[x][y].getTerrainType().getRGB();
	}

	/*
	 * @see TerrainMap#getTerrainTypeName(int, int)
	 */
	public String getTerrainTypeName(int x, int y) {
		return  map[x][y].getTerrainType().getTerrainTypeName();
	}

	public int getRGB(Point p){
		return map[p.x][p.y].getRGB();
	}

	/*
	 * @see TerrainMap#getWidth()
	 */
	public int getWidth() {
		return mapRect.width;
	}

	/*
	 * @see TerrainMap#getHeight()
	 */
	public int getHeight() {
		return mapRect.height;
	}


	public TrackRuleList getTrackRuleList(){
     	return trackRules;
     }

     public TrackAndTerrainTileMapImpl(URL map_url, TerrainTileTypesList terrainTileTypesList, TrackRuleList trackRules){

     	this.trackRules=trackRules;
     	this.terrainTileTypesList=terrainTileTypesList;
     	java.awt.Image mapImage = (new javax.swing.ImageIcon(map_url)).getImage();
			this.mapRect =
				new java.awt.Rectangle(0, 0, mapImage.getWidth(null), mapImage.getHeight(null));
			BufferedImage mapBufferedImage =
				new BufferedImage(mapRect.width, mapRect.height, BufferedImage.TYPE_INT_ARGB);
			java.awt.Graphics g = mapBufferedImage.getGraphics();
			g.drawImage(mapImage, 0, 0, null);
			this.map=new TrackPieceAndTerrainCompositeTile[mapRect.width][mapRect.height];

			for (int x = 0; x < mapRect.width; x++) {
				for (int y = 0; y < mapRect.height; y++) {
					int rgb=mapBufferedImage.getRGB(x,y);
					map[x][y]=new TrackPieceAndTerrainCompositeTile(terrainTileTypesList.getTerrainModel(rgb));
				}
			}



     }

}
