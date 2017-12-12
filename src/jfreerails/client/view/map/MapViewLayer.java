
/*
* MapView.java
*
* Created on 01 August 2001, 06:16
*/
package jfreerails.client.view.map;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
*
* @author  Luke Lindsay
* @version 
*/


public interface MapViewLayer {
    
     void paintTile(Graphics g, int tileX, int tileY);
    
     void paintRectangleOfTiles( Graphics g,  int x, int y, int width, int height);
                 
     void refreshTile(int x, int y);
    
     void refreshRectangleOfTiles( int x, int y, int width, int height);
     
     void paintRect(Graphics g, Rectangle visibleRect);                
        
}
