package jfreerails.client;
import java.util.Iterator;
import jfreerails.client.tileview.TileView;

/**
*  Description of the Interface
*
*@author     Luke Lindsay
*@created    09 October 2001
*/


public interface NewTileViewList {
    
    /**
    *  Description of the Method
    *
    *@return    Description of the Returned Value
    */
    
    public TileView GetTileViewWithNumber();
    
    /**
    *  Description of the Method
    *
    *@param  rgb  Description of Parameter
    *@return      Description of the Returned Value
    */
    
    public boolean TestRGBValue( int rgb );
    
    /**
    *  Description of the Method
    *
    *@return    Description of the Returned Value
    */
    
    public boolean TestTileViewNumber();
    
    /**
    *  Gets the length attribute of the TVL object
    *
    *@return    The length value
    */
    
    public int getLength();
    
    /**
    *  Gets the iterator attribute of the TVL object
    *
    *@return    The iterator value
    */
    
    public Iterator getIterator();
    
    /**
    *  Gets the tileViewWithRGBValue attribute of the TVL object
    *
    *@param  rgb  Description of Parameter
    *@return      The tileViewWithRGBValue value
    */
    
    public TileView getTileViewWithRGBValue( int rgb );
}
