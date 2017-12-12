
/*
* SpecialTileView.java
*
* Created on 20 August 2001, 15:41
*/
package jfreerails.client.tileview;
import jfreerails.map.TerrainMap;


/**
*
* @author  Luke Lindsay
* @version 
*/


public class SpecialTileView extends jfreerails.client.tileview.AbstractTileView {

    private TileView parentTileView;

    private int parentRGB;
    
    public SpecialTileView( jfreerails.lib.ImageSplitter imageSplitter, int[] rgbValues, jfreerails.type.TileType tileModel, TileView parentTileView )  {
        imageSplitter.setTransparencyToTRANSLUCENT();
        tileIcons = new java.awt.Image[ 1 ];
        tileIcons[ 0 ] = imageSplitter.getTileFromSubGrid( 0, 0 );
        this.rgbValues = rgbValues;
        this.tileModel = tileModel;
        this.parentTileView = parentTileView;
    }
    
    public void renderTile( java.awt.Graphics g, int renderX, int renderY, int mapX, int mapY, TerrainMap map ) {
        try {
            if( parentTileView != null ) {
                parentTileView.renderTile( g,   renderX,  renderY,  mapX,  mapY, map );
            }
            else {
                System.out.println( "parent tileView==null" );
            }
            java.awt.Image  icon = this.getIcon( mapX, mapX, map );
            if( null != icon ) {
                g.drawImage( icon, renderX, renderX, null );
         
            } else {
                System.out.println( "special tileView icon==null" );
            }
        }
        catch( jfreerails.common.exception.FreerailsException fe ) {
            fe.printStackTrace();
        }
    }
    
    public int selectTileIcon( int x, int y, TerrainMap map ) {
        return 0;
    }
}
