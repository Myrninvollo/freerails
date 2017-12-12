
/*
* TileIconSelecter.java
*
* Created on 07 July 2001, 12:02
*/
package jfreerails.client.tileview;

import jfreerails.common.FreerailsMap.TerrainMap;

/**
*
* @author  Luke Lindsay
* @version 
*/


public abstract class TileIconSelector extends java.lang.Object {

    private int[] rgbValues;
    
    /*The terrain types that are treated as the same.  E.g. for terrain type 
    river; ocean, ports, and other rivers are treated as the same terrain type. 
    */
    
    /** Creates new TileIconSelecter */
    
    public TileIconSelector( int[] rgbValues ) {
        if( rgbValues != null ) {
            this.rgbValues = rgbValues;
        }
        else {
            System.out.println( "Error: TileIconSelector - int[] rgbValues == null" );
        }
    }
    
    public int selectTileIcon(int x, int y, TerrainMap map) {
        return 0;
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
