
/*
* ChequeredTileView.java
*
* Created on 07 July 2001, 14:25
*/
package jfreerails.client.tileview;

import jfreerails.world.terrain.TerrainType;
import jfreerails.world.terrain.TerrainMap;

/**
*
* @author  Luke Lindsay
* @version 
*/


final public class ChequeredTileView extends jfreerails.client.tileview.AbstractTileView {
    
    public int selectTileIcon( int x, int y, jfreerails.world.terrain.TerrainMap map ) {
        return ( x + y ) % 2;
    }
    
    /** Creates new ChequeredTileView */
    
    public ChequeredTileView( jfreerails.lib.ImageSplitter imageSplitter, int[] rgbValues, TerrainType tileModel )  {
        imageSplitter.setTransparencyToOPAQUE();
        tileIcons = new java.awt.Image[ 2 ];
        for( int  i = 0;i < tileIcons.length;i++ ) {
            tileIcons[ i ] = imageSplitter.getTileFromSubGrid( 0 + i, 0 );
        }
        super.rgbValues = rgbValues;
        super.tileModel = tileModel;
    }
}
