
/*
*  Tile.java
*
*  Created on 04 July 2001, 06:42
*/
package jfreerails.common;

/**
*  This class encapsulates the non-visual properites of a terrain tile. E.g.
*  its type-name, its right-of-way cost.
*
*@author     Luke Lindsay
*@created    16 August 2001
*@version    1.0
*/


public class TileModel extends java.lang.Object {

    private int rgb;

    private String terrainType;
    
    /**
    *  Creates new Tile
    *
    *@param  rgb          The RGB value to be mapped to this terrain type.
    *@param  terrainType  The name of this terrain type. E.g. forest.
    */
    
    public TileModel( int rgb, java.lang.String terrainType ) {
        this.terrainType = terrainType;
        this.rgb = rgb;
    }
    
    /**
    *@return    The RGB value mapped to this terrain type.
    */
    
    public int getRGB() {
        return rgb;
    }
    
    /**
    *@return    The name of this terrain type.
    */
    
    public String getTerrainType() {
        return terrainType;
    }
}
