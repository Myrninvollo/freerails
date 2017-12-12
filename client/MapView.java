
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
    
    public void paintTile( Graphics g, int x, int y);
    
    public void paintRectangleOfTiles( Graphics g,  Rectangle tilesToPaint );
    
    public Dimension getMapSizeInTiles();
    
    public Dimension getMapSizeInPixels();

    public Dimension getTileSize();

    public void paintRect(java.awt.Graphics g);
}
