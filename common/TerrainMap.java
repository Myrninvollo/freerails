
/*
* TerrainMap.java
*
* Created on 13 July 2001, 09:06
*/
package jfreerails.common;

/**
*  This class encapsulates the terrain map.
*
* @author  Luke Lindsay
* @version 
*/
import java.awt.image.BufferedImage;
import java.awt.Point;


public class TerrainMap extends java.lang.Object {

    private BufferedImage map;
    
    /** Creates new TerrainMap */
    
    public TerrainMap( BufferedImage map ) {
        this.map = map;
    }
    
    public int getHeight() {
        return map.getHeight();
    }
    
    public int getTerrainTileType( int x, int y ) {
        return map.getRGB( x, y );
    }
    
    public int getWidth() {
        return map.getWidth();
    }
}
