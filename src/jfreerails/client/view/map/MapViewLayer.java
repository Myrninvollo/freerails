
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


public interface MapViewLayer {
    
     void paintTile( Graphics g, Point tile);
    
     void paintRectangleOfTiles( Graphics g,  Rectangle tilesToPaint );
        
     void paintRect(Graphics g);
     
     void refreshTile(Point tile);
    
     void refreshRectangleOfTiles( Rectangle tilesToRefresh );
        
     void reset();
     
     boolean canDoScale(float scale);
     
     float[] getPreferedScales();
        
}
