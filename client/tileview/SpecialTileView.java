
/*
* SpecialTileView.java
*
* Created on 20 August 2001, 15:41
*/
package jfreerails.client.tileview;

import jfreerails.common.FreerailsMap.TerrainMap;
import java.awt.Point;

/**
*
* @author  Luke Lindsay
* @version 
*/


public class SpecialTileView extends jfreerails.client.tileview.TileView {

    private TileView parentTileView;

    private int parentRGB;
    
    public void renderTile(java.awt.Graphics g, Point renderCoordinate, Point mapCoordinate, TerrainMap map) {
        try {
            if( parentTileView != null ) {
                parentTileView.renderTile( g, renderCoordinate, mapCoordinate, map );
            }
            else {
                System.out.println( "parent tileView==null" );
            }
            java.awt.Image  icon = this.getIcon( mapCoordinate.x, mapCoordinate.y, map );
            if( null != icon ) {
                g.drawImage( icon, renderCoordinate.x, renderCoordinate.y, null );
            }
        }
        catch( jfreerails.common.exception.FreerailsException fe ) {
            fe.printStackTrace();
        }
    }
    
    public int selectTileIcon(int x, int y, TerrainMap map) {
        return 0;
    }
    
    public SpecialTileView(jfreerails.lib.ImageSplitter imageSplitter, int[] rgbValues, jfreerails.common.TileModel tileModel, TileView parentTileView) throws jfreerails.common.exception.FreerailsException {
        imageSplitter.setTransparencyToBITMASK();
        tileIcons = new java.awt.Image[ 1 ];
        tileIcons[ 0 ] = imageSplitter.getTileFromSubGrid( 0, 0 );
        this.rgbValues = rgbValues;
        this.tileModel = tileModel;
        this.parentTileView = parentTileView;
    }
}
