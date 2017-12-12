
/*
* MapViewLayer.java
*
* Created on 06 August 2001, 16:03
*/
package jfreerails.client;
import java.awt.*;

/**
*
* @author  Luke Lindsay
* @version
*/


public interface MapViewLayer {
    
    public Dimension getTileSize();
    
    public void paintTile( Graphics g, Point tileMapCoordinate, Point screenPosition );
    
    public boolean aTileIsHere( Point mapCoordinate );
}
