/**
 * @author Scott Bennett
 * Date: 7th April 2003
 *
 * Class to randomly position the city tiles on the game map, within a 5x5 tile
 * area around a city. A random number of between 1 and 5 tiles are initially 
 * chosen with the idea to have these increase over the period of a game. 
 */

package jfreerails.world.city;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

import jfreerails.world.city.CityModel;
import jfreerails.world.terrain.TerrainType;
import jfreerails.world.top.KEY;
import jfreerails.world.top.World;
import jfreerails.world.track.FreerailsTile;

public class CityTilePositioner {

	private World w;
	private int numberOfTiles = 0;
	private double probability;
	private int startX;
	private int startY;
	private TerrainType type;
	private FreerailsTile tile;
	private ArrayList terrainTypes;

	public CityTilePositioner(World world) {
		this.w = world;

		terrainTypes = new ArrayList();

		//get the different types of Urban terrain
		for (int i = 0; i < w.size(KEY.TERRAIN_TYPES); i++) {
			type = (TerrainType) w.get(KEY.TERRAIN_TYPES, i);

			if (type.getTerrainCategory().equals("Urban")) {
				terrainTypes.add(new Integer(i));
			}
		}

		doTilePositioning();
	}

	public void doTilePositioning() {

		for (int i = 0; i < w.size(KEY.CITIES); i++) {

			//0.04 represents a 1/25 probability, ie. 1 tile, based on a 5x5 city 
			numberOfTiles = calcNumberOfInitialTiles();
			probability = 0.04 * numberOfTiles;

			CityModel tempCity = (CityModel) w.get(KEY.CITIES, i);

			startX = tempCity.getCityX();
			if (startX < 2) {
				startX = 2;
			}

			startY = tempCity.getCityY();
			if (startY < 2) {
				startY = 2;
			}

			processCityTiles(calcTilesToBuildOn());
		}
	}

	public int calcNumberOfInitialTiles() {

		double myRand = Math.random();
		int numberInitialTiles = 5;

		if (myRand < 0.8) {
			numberInitialTiles = 4;
		}
		if (myRand < 0.6) {
			numberInitialTiles = 3;
		}
		if (myRand < 0.4) {
			numberInitialTiles = 2;
		}
		if (myRand < 0.2) {
			numberInitialTiles = 1;
		}

		return numberInitialTiles;
	}

	public void processCityTiles(Vector v) {

		Vector tiles = v;

		int x = 0;
		int y = 0;
		String category = null;
		double randValue;
		double scale = 0.0;
		double size = 0.0;
		int index = 0;

		for (int j = 0; j < tiles.size(); j++) {

			Point p = (Point) tiles.elementAt(j);
			x = (int) p.getX();
			y = (int) p.getY();

			//We need to check whether p is on the map before we process the tile, LL
			if (w.boundsContain(x, y)) {

				int tileTypeNumber = w.getTile(x, y).getTerrainTypeNumber();
				category = ((TerrainType)w.get(KEY.TERRAIN_TYPES,tileTypeNumber)).getTerrainCategory();

				//if the tile has a Country terrain, then build a tile on it, otherwise ignore
				if (category.equals("Country")) {

					//randomly select an Urban terrain type
					randValue = Math.random();

					size = terrainTypes.size();
					scale = 1 / size;

					for (int k = (int) size; k > 0; k--) {

						if (randValue <= scale * k) {
							index = k - 1;
						} else {
							break;
						}
					}
					Integer typeToAdd = (Integer)terrainTypes.get(index);
					tile = new FreerailsTile(typeToAdd.intValue());
					w.setTile(x, y, tile);

					//if the category isn't Country then:	
					//we can't build a tile here because there's something in the way
					//which may be a Resource, another Urban tile or Water.

					//the situation may arise where this would've been the only tile
					//to have been built for this city, if this is the case it will now
					//have no urban tiles, this is a consideration for positioning cities
					//initially i guess. Positioning a city in water or a clump of resources
					//may cause this issue.

				} //end if

			} //end if

		} //end for loop

	}

	public Vector calcTilesToBuildOn() {

		int tilesBuiltOnCount = 0;
		double randomTileValue;
		Vector tileLocations = new Vector();

		for (int Y = startY - 2; Y < startY + 3; Y++) {

			for (int X = startX - 2; X < startX + 3; X++) {

				randomTileValue = Math.random();

				if (randomTileValue < probability) {
					Point p = new Point(X, Y);
					tileLocations.addElement(p);

					tilesBuiltOnCount++;
					if (tilesBuiltOnCount == numberOfTiles) {
						break;
					}
				}
			} //end X for loop

			if (tilesBuiltOnCount == numberOfTiles) {
				break;
			}

		} //end Y for loop

		//in going through the probability process and no tiles were created,
		//we need at least 1 tile, so we'll just add one at the centre
		if (tilesBuiltOnCount == 0) {
			Point p = new Point(startX, startY);
			tileLocations.addElement(p);
		}

		return tileLocations;
	}
}