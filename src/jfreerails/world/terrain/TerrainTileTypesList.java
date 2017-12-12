package jfreerails.world.terrain;

import java.util.Iterator;

import jfreerails.world.misc.FreerailsSerializable;



/**
*  Description of the Interface
*
*@author     Luke Lindsay
*     09 October 2001
*/

public interface TerrainTileTypesList extends FreerailsSerializable {

	 String getTerrainName(int rgb);

	 int getTerrainRGBValue(java.lang.String name);

	 TerrainType getTerrainModel(int rgb);

	 Iterator getIterator();
}