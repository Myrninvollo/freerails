
/*
* ForestStyleTileIconSelector.java
*
* Created on 07 July 2001, 14:36
*/
package jfreerails.client.tileview;

/**
*
* @author  Luke Lindsay
* @version 
*/
import jfreerails.common.TerrainMap;


public class ForestStyleTileIconSelector extends jfreerails.client.tileview.TileIconSelector {

    private static final int[] Y_LOOK_AT =  {
        0, 0
    };

    private static final int[] X_LOOK_AT =  {
        -1, 1
    };
    
    public int selectTileIcon( int x, int y, TerrainMap map ) {
        int  iconNumber = 0;
        for( int  i = 0;i < 2;i++ ) {
            iconNumber = iconNumber | checkTile( x + X_LOOK_AT[ i ], y + Y_LOOK_AT[ i ], map );
            iconNumber = iconNumber << 1;
        }
        iconNumber = iconNumber >> 1;
        return iconNumber;
    }
    
    /** Creates new ForestStyleTileIconSelector */
    
    public ForestStyleTileIconSelector( int[] rgbValues ) {
        super( rgbValues );
    }
}
