package jfreerails.factory;
import java.awt.Point;

import jfreerails.client.tileview.TileViewList;
import jfreerails.common.exception.FreerailsException;
import jfreerails.list.TerrainTileTypesList;



/**
*  Description of the Interface
*
*@author     Luke Lindsay
*@created    09 October 2001
*/


public interface TileFactory {
    
    /**
    *  Gets the terrainTileTypesList attribute of the TileFactoryInterface
    *  object
    *
    *@return    The terrainTileTypesList value
    */
    
    public TerrainTileTypesList getTerrainTileTypesList();
    
    /**
    *  Gets the tileSize attribute of the TileFactoryInterface object
    *
    *@return                         The tileSize value
    *@exception  FreerailsException  Description of Exception
    */
    
    public Point getTileSize() throws FreerailsException;
    
    /**
    *  Gets the tileViewList attribute of the TileFactoryInterface object
    *
    *@return                         The tileViewList value
    *@exception  FreerailsException  Description of Exception
    */
    
    public TileViewList getTileViewList() throws FreerailsException;
}
