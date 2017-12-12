
/*
* MapView.java
*
* Created on 01 August 2001, 06:16
*/
package jfreerails.client.view.map;
import java.awt.*;
import jfreerails.client.*;

/**
*
* @author  Luke Lindsay
* @version 
*/


public interface MapView {
    
    public void paintTile( Graphics g, Point tile);
    
    public void paintRectangleOfTiles( Graphics g,  Rectangle tilesToPaint );
    
    //public Dimension getMapSizeInTiles();
    
    public Dimension getMapSizeInPixels();

    public Dimension getTileSize();

    public void paintRect(Graphics g);
}
