
/*
* TileIconSelecter.java
*
* Created on 07 July 2001, 12:02
*/
package jfreerails.client.renderer;

import jfreerails.world.top.ReadOnlyWorld;


/**
*
* @author  Luke Lindsay
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

    public int selectTileIcon( int x, int y, ReadOnlyWorld w ) {
        return 0;
    }
	/* This seems to be duplicated in AbstractTileRenderer
    protected int checkTile( int x, int y,  World w  ) {
        int  match = 1;

       //0==match!  (0 is assigned to match because of the way the tiles are set up
       //in the image from which they are grabbed.)
       //
        if( ( ( x < w.getMapWidth() ) && ( x >= 0 ) ) && ( y < w.getMapHeight() ) && ( y >= 0 ) ) {
            for( int  i = 0;i < rgbValues.length;i++ ) {
            	TerrainTile tt = (TerrainTile)w.getTile(x, y);
                if( tt.terrainRgb() == rgbValues[ i ] ) {
                    match = 0;

                //A match
                }
            }
        }
        else {
            match = 0; //A match

        //If the tile we are checking is off the map, let it be a match.
        //This stops coast appearing where the ocean meets the map edge.
        //
        }
        return match;
    }
    */
}
