
/*
* TileView.java
*
* Created on 04 July 2001, 07:01
*/
package jfreerails.client.tileview;

import jfreerails.common.exception.FreerailsException;
import jfreerails.common.FreerailsMap.TerrainMap;

//import jfreerails.common.FreerailsMap.TerrainMap;
import java.awt.Image;
import java.awt.Point;


/**
*  This class encapsulates the visible properties of a tile.
* @author  Luke Lindsay
* @version
*/


public abstract class TileView extends java.lang.Object implements TileRenderer {

    protected jfreerails.common.TileModel tileModel;

    protected int rgb;

    protected int[] rgbValues;

    protected TileIconSelector tileIconSelector;

    protected Image[] tileIcons;

    protected static int tileWidth;

    protected static int tileHeight;
    
    public void renderTile(java.awt.Graphics g, Point renderCoordinate, Point mapCoordinate, TerrainMap map) {
        try {
            java.awt.Image  icon = this.getIcon( mapCoordinate.x, mapCoordinate.y, map );
            if( null != icon ) {
                g.drawImage( icon, renderCoordinate.x, renderCoordinate.y, null );
            }
        }
        catch( jfreerails.common.exception.FreerailsException fe ) {
            fe.printStackTrace();
        }
    }
    
    public String getTerrainType() {
        return tileModel.getTerrainType();
    }
    
    /*The terrain types that are treated as the same.  E.g. for terrain type
    river; ocean, ports, and other rivers are treated as the same terrain type.
    */
    
    public int selectTileIcon(int x, int y, TerrainMap map) {
        return 0;
    }
    
    public int getRGB() {
        return tileModel.getRGB();
    }
    
    public int getTileWidth() {
        return tileWidth;
    }
    
    public int getTileHeight() {
        return tileHeight;
    }
    
    public Image getIcon(int x, int y, TerrainMap map) throws FreerailsException {
        int  tile = selectTileIcon( x, y, map );
        if( tileIcons[ tile ] != null ) {
            return tileIcons[ tile ];
        }
        else {
            throw new FreerailsException( "Error in TileView.getIcon: icon no. " + tile + "==null" );
        }
    }
    
    public Image getIcon() {
        return tileIcons[ 0 ];
    }
    
    public static void setTileSize( int height, int width ) {
        tileHeight = height;
        tileWidth = width;
    }
    
    protected int checkTile(int x, int y, TerrainMap map) {
        int  match = 1;
        
        /*0==match!  (0 is assigned to match because of the way the tiles are set up
        *in the image from which they are grabbed.)
        */
        if( ( ( x < map.getWidth() ) && ( x >= 0 ) ) && ( y < map.getHeight() ) && ( y >= 0 ) ) {
            for( int  i = 0;i < rgbValues.length;i++ ) {
                if( map.getTerrainTileType( x, y ) == rgbValues[ i ] ) {
                    match = 0;
                
                //A match
                }
            }
        }
        else {
            match = 0; //A match
        
        /*If the tile we are checking is off the map, let it be a match.
        This stops coast appearing where the ocean meets the map edge.
        */
        }
        return match;
    }
}
