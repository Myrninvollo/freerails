
/*
* TileView.java
*
* Created on 04 July 2001, 07:01
*/
package jfreerails.client.tileview;

/**
*  This class encapsulates the visible properties of a tile.
* @author  Luke Lindsay
* @version 
*/
import javax.swing.ImageIcon;
import jfreerails.common.TileModel;
import jfreerails.client.tileview.TileIconSelector;
import jfreerails.common.exception.FreerailsException;
import jfreerails.common.TerrainMap;


public class TileView extends java.lang.Object {

    private TileIconSelector tileIconSelector;

    private ImageIcon[] tileIcons;

    private TileModel tileModel;

    private int rgb;

    private static int tileHeight;

    private static int tileWidth;
    
    public ImageIcon getIcon( int x, int y, TerrainMap map ) throws FreerailsException {
        int  tile = tileIconSelector.selectTileIcon( x, y, map );
        if( tileIcons[ tile ] != null ) {
            return tileIcons[ tile ];
        }
        else {
            throw new FreerailsException( "Error in TileView.getIcon: icon no. " + tile + "==null" );
        }
    }
    
    /** Creates new TileView */
    
    public TileView( ImageIcon[] tileIcons, TileIconSelector tileIconSelector, TileModel tileModel ) throws FreerailsException {
        if( ( tileIcons != null ) && ( tileIconSelector != null ) ) {
            this.tileIcons = tileIcons;
            this.tileIconSelector = tileIconSelector;
            this.tileModel = tileModel;
        }
        else {
            throw new FreerailsException( "Error: TileView - tileIcons==" + tileIcons + " and tileIconSelector==" + tileIconSelector );
        }
    }
    
    public ImageIcon getIcon() {
        return tileIcons[ 0 ];
    }
    
    public int getTileWidth() {
        return tileWidth;
    }
    
    public int getTileHeight() {
        return tileHeight;
    }
    
    public String getTerrainType() {
        return tileModel.getTerrainType();
    }
    
    public int getRGB() {
        return tileModel.getRGB();
    }
    
    public static void setTileSize( int height, int width ) {
        tileHeight = height;
        tileWidth = width;
    }
}
