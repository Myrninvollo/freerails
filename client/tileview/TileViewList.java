
/*
* TileViewList.java
*
* Created on 08 August 2001, 17:11
*/
package jfreerails.client.tileview;

import java.util.HashMap;

/**
*
* @author  Luke Lindsay
* @version 
*/


public class TileViewList {

    private HashMap tiles;
    
    public java.util.Iterator getIterator() {
        return tiles.values().iterator();
    }
    
    /** Creates new TileViewList */
    
    public TileViewList(HashMap tiles) {
        this.tiles = tiles;
    }
    
    public TileView getTileViewWithRGBValue( int rgb ) {
        return (TileView)tiles.get( new Integer( rgb ) );
    }
    
    public TileView GetTileViewWithNumber() {
        return null;
    }
    
    public boolean TestRGBValue( int rgb ) {
        return tiles.containsKey( new Integer( rgb ) );
    }
    
    public boolean TestTileViewNumber() {
        return false;
    }
    
    public int getLength() {
        return tiles.size();
    }
}
