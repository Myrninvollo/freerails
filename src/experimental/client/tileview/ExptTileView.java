
/*
* ExptTileView.java
*
* Created on 10 October 2001, 02:48
*/
package experimental.client.tileview;

import java.awt.Color;
import java.awt.Image;

import jfreerails.client.tileview.TileView;
import jfreerails.world.terrain.TerrainMap;

/**
*
* @author  Luke Lindsay
* @version 
*/


public class ExptTileView implements TileView {
    
    
    public Image getIcon( int x, int y, TerrainMap map ) {
        return null;
    }
    
    
    
    public int getRGB() {
        return 0;
    }
    
    
    
    public ExptTileView() {
        
    }
   
    
    public Image getIcon() {
        return null;
    }
    
  
    public int getTileWidth() {
        return 30;
    }
    
   
    
    public void renderTile( java.awt.Graphics g,  int renderX, int renderY, int mapX, int mapY, TerrainMap map ) {
        g.setColor( Color.cyan );
        g.fillRect( renderX, renderY, 30, 30 );
        g.setColor( Color.lightGray );
        g.drawString( String.valueOf( mapX), renderX + 3, renderY + 15 );
        g.drawString( String.valueOf( mapY ), renderX + 3, renderY + 29 );
        g.drawRect( renderX, renderY, 30, 30 );
    }
  
    public int selectTileIcon( int x, int y, TerrainMap map ) {
        return 0;
    }
    
   
    
    public String getTerrainType() {
        return "Experimental tile type";
    }
    
   
    
    public int getTileHeight() {
        return 30;
    }
}
