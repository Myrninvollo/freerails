
/*
*  TileViewList.java
*
*  Created on 08 August 2001, 17:11
*/
package jfreerails.client.tileview;
import java.util.HashMap;

/**
*@author           Luke Lindsay
*@created          09 October 2001
*@createdpublic    class TileViewListImplrsion
*/


public class TileViewListImpl implements TileViewList {

    private HashMap tiles;
    
    /**
    *  Gets the tileViewWithRGBValue attribute of the TileViewList object
    *
    *@param  rgb  Description of Parameter
    *@return      The tileViewWithRGBValue value
    */
    
    public TileView getTileViewWithRGBValue( int rgb ) {
        return (TileView)tiles.get( new Integer( rgb ) );
    }
    
    /**
    *  Description of the Method
    *
    *@return    Description of the Returned Value
    */
    
    public TileView GetTileViewWithNumber() {
        return null;
    }
    
    /**
    *  Gets the length attribute of the TileViewList object
    *
    *@return    The length value
    */
    
    public int getLength() {
        return tiles.size();
    }
    
    /**
    *  Description of the Method
    *
    *@param  rgb  Description of Parameter
    *@return      Description of the Returned Value
    */
    
    public boolean TestRGBValue( int rgb ) {
        return tiles.containsKey( new Integer( rgb ) );
    }
    
    /**
    *  Description of the Method
    *
    *@return    Description of the Returned Value
    */
    
    public boolean TestTileViewNumber() {
        return false;
    }
    
    public TileViewListImpl( HashMap t ) {
        tiles = t;
    }
    
    /**
    *  Creates new TileViewList
    *
    *@return        The iterator value
    */
    
    public java.util.Iterator getIterator() {
        return tiles.values().iterator();
    }
}
