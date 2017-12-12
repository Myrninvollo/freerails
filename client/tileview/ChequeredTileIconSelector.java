
/*
* ChequeredTileIconSelector.java
*
* Created on 07 July 2001, 14:25
*/
package jfreerails.client.tileview;

/**
*
* @author  Luke Lindsay
* @version 
*/
import jfreerails.common.TerrainMap;


public class ChequeredTileIconSelector extends jfreerails.client.tileview.TileIconSelector {
    
    /** Creates new ChequeredTileIconSelector */
    
    public ChequeredTileIconSelector( int[] rgbValues ) {
        super( rgbValues );
    }
    
    public int selectTileIcon( int x, int y, TerrainMap map ) {
        return ( x + y ) % 2;
    }
}
