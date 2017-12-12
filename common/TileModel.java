
/*
* Tile.java
*
* Created on 04 July 2001, 06:42
*/
package jfreerails.common;

/**
*  This class encapsulates the non-visual properites of a terrain tile.
* @author  Luke Lindsay
* @version 
*/


public class TileModel extends java.lang.Object {

    private String terrainType;

    private int rgb;
    
    /** Creates new Tile */
    
    public TileModel( int rgb, java.lang.String terrainType ) {
        this.terrainType = terrainType;
        this.rgb = rgb;
    }
    
    public String getTerrainType() {
        return terrainType;
    }
    
    public int getRGB() {
        return rgb;
    }
}
