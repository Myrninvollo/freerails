
/*
* StandardTileIconSelecter.java
*
* Created on 07 July 2001, 12:11
*/
package jfreerails.client.tileview;

import jfreerails.world.terrain.TerrainType;

/**
*
* @author  Luke Lindsay
* @version 
*/


final public class StandardTileView extends jfreerails.client.tileview.AbstractTileView {
    
    /** Creates new StandardTileIconSelecter */
    
    public StandardTileView( jfreerails.lib.ImageSplitter imageSplitter, int[] rgbValues, TerrainType tileModel )  {
        imageSplitter.setTransparencyToOPAQUE();
        tileIcons = new java.awt.Image[ 1 ];
        tileIcons[ 0 ] = imageSplitter.getTileFromSubGrid( 0, 0 );
        super.rgbValues = rgbValues;
        super.tileModel = tileModel;
    }
}
