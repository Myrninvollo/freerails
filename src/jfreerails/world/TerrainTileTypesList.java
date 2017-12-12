package jfreerails.world;
import java.util.Iterator;

import jfreerails.lib.FreerailsSerializable;
import jfreerails.world.terrain.TerrainType;


/**
*  Description of the Interface
*
*@author     Luke Lindsay
*@created    09 October 2001
*/

public interface TerrainTileTypesList extends FreerailsSerializable {

	 String getTerrainName(int rgb);

	 int getTerrainRGBValue(java.lang.String name);

	 TerrainType getTerrainModel(int rgb);

	 Iterator getIterator();
}