package jfreerails.map;

import java.awt.Point;

/**
 *  Description of the Interface
 *
 *@author     Luke Lindsay
 *@created    07 November 2001
 */
public interface TerrainMap {
    /**
     *  Gets the terrainTileType attribute of the TM object
     *
     *@param  x  Description of the Parameter
     *@param  y  Description of the Parameter
     *@return    The terrainTileType value
     */
    public int getTerrainTileType(int x, int y);


    /**
     *  Gets the terrainTypeName attribute of the TM object
     *
     *@param  x  Description of the Parameter
     *@param  y  Description of the Parameter
     *@return    The terrainTypeName value
     */
    public String getTerrainTypeName(int x, int y);


    /**
     *  Gets the width attribute of the TM object
     *
     *@return    The width value
     */
    public int getWidth();


    /**
     *  Gets the height attribute of the TM object
     *
     *@return    The height value
     */
    public int getHeight();
    
    public boolean contains(Point location);
    	
}
