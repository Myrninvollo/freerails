
/*
* MapView.java
*
* Created on 01 August 2001, 06:16
*/
package jfreerails.client;
import jfreerails.client.MapViewLayer;
import java.awt.*;

/**
*
* @author  Luke Lindsay
* @version 
*/


public interface MapView {
    
    public void updateTiles( Rectangle tileMapRectangle );
    
    public Dimension getTileSize();
    
    public void paintTile( Graphics g, Point tileMapCoordinate, Point screenPosition );
    
    public void paintTiles( Graphics g, Point tileMapCoordinate, Rectangle tilesToPaint );
    
    public Dimension getMapSizeInTiles();
    
    public Dimension getMapSizeInPixels();
    
    public void updateTile( Point tileMapCoordinate );
}
