/*
 * TileMap.java
 *
 * Created on 23 January 2002, 21:39
 */

package jfreerails.world.tilemap;
import java.awt.Dimension;
import java.awt.Point;
/**
 *
 * @author  lindsal
 * @version 
 */
public interface TileMap {
    
     Dimension getMapSize();
     
     int getRGB(Point p);
     
     
    
    boolean boundsContain(Point location);    

}

