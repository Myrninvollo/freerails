
/*
*  TerrainTileTypesList.java
*
*  Created on 30 July 2001, 14:28
*/
package jfreerails.world.terrain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**  This class encapsulates the list of terrain types that where loaded.
*
*@author     Luke Lindsay
*     09 October 2001
*@version    1.0
*/

final public class TerrainTileTypesListImpl implements TerrainTileTypesList {
    /**
     *  String
     */
	private HashMap terrainNames;

    /**
     *  Integer
     */
	private HashMap terrainRGBValues;

    /**
     *  TerrainType
     */
	private HashMap terrainModels;

	private ArrayList tilesModels;

	public TerrainType getTerrainModel(int rgb) {
		return (TerrainType) terrainModels.get(new Integer(rgb));
	}

	public String getTerrainName(int rgb) {
		return (String) terrainNames.get(new Integer(rgb));
	}

	public TerrainTileTypesListImpl(ArrayList tilesModels) {
		this.tilesModels = tilesModels;
		terrainNames = new HashMap();
		terrainRGBValues = new HashMap();
		terrainModels = new HashMap();
		for (int i = 0; i < tilesModels.size(); i++) {
			TerrainType tilemodel = ((TerrainType) tilesModels.get(i));
			terrainNames.put(
				new Integer(tilemodel.getRGB()),
				tilemodel.getTerrainTypeName());
			terrainRGBValues.put(
				tilemodel.getTerrainTypeName(),
				new Integer(tilemodel.getRGB()));
			terrainModels.put(new Integer(tilemodel.getRGB()), tilemodel);
		}
	}

	public int getTerrainRGBValue(String name) {
		Integer i = (Integer) terrainRGBValues.get(name);
		return i.intValue();
	}

	public Iterator getIterator(){
		return tilesModels.iterator();
	}
}