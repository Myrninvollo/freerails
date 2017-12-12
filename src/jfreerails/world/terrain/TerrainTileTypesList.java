package jfreerails.world.terrain;

import jfreerails.world.misc.FreerailsSerializable;
import java.util.Iterator;



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