
/*
* TileRenderer.java
*
* Created on 31 July 2001, 22:17
*/
package jfreerails.client.tileview;

import java.awt.Point;

/**
*
* @author  Luke Lindsay
* @version 
*/


public interface TileRenderer {
    
    public void renderTile(java.awt.Graphics g, Point renderCoordinate, Point mapCoordinate, jfreerails.common.FreerailsMap.TerrainMap map);
}
