package jfreerails.world.terrain;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.net.URL;

import jfreerails.world.TerrainTileTypesList;

/**
 *  This class encapsulates the terrain map. It is composed of a 2D array of
 *  terrain tiles, and provides methods to access them.
 *
 *@author     Luke Lindsay
 *@created    21 September 2001
 */
public class TerrainMapImpl extends java.lang.Object implements TerrainMap {

	private int[] mapArray;

	private Rectangle mapRect;

	private TerrainTileTypesList terrainTileTypesList;

	private TerrainType[][] tileMap;

	/**
	 *  Constructor for the TerrainMap object
	 *
	 *@param  url                     the png file that stores the map
	 *@param  terrainTileTypesList    specifies the terrain tyes that may be
	 *      used on this map.
	 *@exception  FreerailsException  Description of Exception
	 */

	public TerrainMapImpl(URL url, TerrainTileTypesList terrainTileTypesList) {

		System.out.println("\nLoading map " + url);

		java.awt.Image map = (new javax.swing.ImageIcon(url)).getImage();
		this.mapRect =
			new java.awt.Rectangle(0, 0, map.getWidth(null), map.getHeight(null));
		BufferedImage mapBufferedImage =
			new BufferedImage(mapRect.width, mapRect.height, BufferedImage.TYPE_INT_ARGB);
		java.awt.Graphics g = mapBufferedImage.getGraphics();
		g.drawImage(map, 0, 0, null);
		this.terrainTileTypesList = terrainTileTypesList;

		this.mapArray =
			mapBufferedImage.getRGB(
				0,
				0,
				mapRect.width,
				mapRect.height,
				null,
				0,
				mapRect.width);

	}

	/**
	 *  Gets the terrainTileType attribute of the TerrainMap object
	 *
	 *@param  x  Description of Parameter
	 *@param  y  Description of Parameter
	 *@return    The terrainTileType value
	 */

	public int getTerrainTileType(int x, int y) {
		if (mapRect.contains(x, y)) {
			return this.mapArray[y * mapRect.width + x];
		} else {
			throw new java.lang.IllegalArgumentException(
				"Tried to get tile from a point outside the map");
		}
	}

	/**
	 *  Gets the terrainTypeName attribute of the TerrainMap object
	 *
	 *@param  x  Description of Parameter
	 *@param  y  Description of Parameter
	 *@return    The terrainTypeName value
	 */

	public String getTerrainTypeName(int x, int y) {
		return terrainTileTypesList.getTerrainName(
			this.mapArray[y * mapRect.width + x]);
	}

	/**
	 *  Gets the width attribute of the TerrainMap object
	 *
	 *@return    The width value
	 */

	public int getWidth() {
		return this.mapRect.width;
		//return mapBufferedImage.getWidth();
	}

	/**
	 *  Creates new TerrainMap
	 *
	 *@param  map                   Description of Parameter
	 *@param  terrainTileTypesList  Description of Parameter
	 */

	public TerrainMapImpl(
		BufferedImage map,
		TerrainTileTypesList terrainTileTypesList) {
		this.mapRect = new Rectangle(map.getWidth(), map.getHeight());
		this.mapArray =
			map.getRGB(0, 0, mapRect.width, mapRect.height, null, 0, mapRect.width);
		this.terrainTileTypesList = terrainTileTypesList;
	}

	/**
	 *  Gets the height attribute of the TerrainMap object
	 *
	 *@return    The height value
	 */

	public int getHeight() {
		return this.mapRect.height;
		// return mapBufferedImage.getHeight();
	}

	public boolean contains(Point location) {

		return mapRect.contains(location);
	}

	
	public TerrainMapImpl(
		int w,
		int h,
		TerrainTileTypesList terrainTileTypesList) {

		System.out.println("\nGenerating blank map ");
		this.mapRect = new java.awt.Rectangle(0, 0, w, h);
		this.mapArray = new int[w * h];
		this.terrainTileTypesList = terrainTileTypesList;

	}
}