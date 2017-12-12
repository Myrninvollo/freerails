
/*
* RiverStyleTileIconSelecter.java
*
* Created on 07 July 2001, 12:36
*/
package jfreerails.client.tileview;

/**
*
* @author  Luke Lindsay
* @version 
*/
import jfreerails.common.TerrainMap;


public class RiverStyleTileIconSelector extends jfreerails.client.tileview.TileIconSelector {

    private static final int[] X_LOOK_AT =  {
        -1, 0, 1, 0
    };

    private static final int[] Y_LOOK_AT =  {
        0, 1, 0, -1
    };
    
    public int selectTileIcon( int x, int y, TerrainMap map ) {
        int  iconNumber = 0;
        for( int  i = 0;i < 4;i++ ) {
            iconNumber = iconNumber << 1;
            iconNumber = iconNumber | checkTile( x + X_LOOK_AT[ i ], y + Y_LOOK_AT[ i ], map );
        }
        return iconNumber;
    }
    
    /** Creates new RiverStyleTileIconSelecter */
    
    public RiverStyleTileIconSelector( int[] rgbValues ) {
        super( rgbValues );
    }
}
