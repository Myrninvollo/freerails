package jfreerails.client.tileview;
import jfreerails.map.TerrainMap;
import java.awt.Image;
import java.awt.Point;
import jfreerails.map.*;


/**
*  Description of the Interface
*
*@author     Luke Lindsay
*@created    09 October 2001
*/


public interface TileView {
    
    /**
    *  Description of the Method
    *
    *@param  x    Description of Parameter
    *@param  y    Description of Parameter
    *@param  map  Description of Parameter
    *@return      Description of the Returned Value
    */
    
    public int selectTileIcon( int x, int y, TerrainMap map );
    
    /**
    *  Gets the rGB attribute of the TileViewInterface object
    *
    *@return    The rGB value
    */
    
    public int getRGB();
    
    /**
    *  Gets the tileWidth attribute of the TileViewInterface object
    *
    *@return    The tileWidth value
    */
    
    public int getTileWidth();
    
    /**
    *  Gets the tileHeight attribute of the TileViewInterface object
    *
    *@return    The tileHeight value
    */
    
    public int getTileHeight();
    
    /**
    *  Gets the icon attribute of the TileViewInterface object
    *
    *@param  x                       Description of Parameter
    *@param  y                       Description of Parameter
    *@param  map                     Description of Parameter
    *@return                         The icon value
    *@exception  FreerailsException  Description of Exception
    */
    
    public Image getIcon( int x, int y, TerrainMap map ) throws jfreerails.common.exception.FreerailsException;
    
    /**
    *  Gets the icon attribute of the TileViewInterface object
    *
    *@return    The icon value
    */
    
    public Image getIcon();
    
    /**
    *  Description of the Method
    *
    *@param  g                 Description of Parameter
    *@param  renderCoordinate  Description of Parameter
    *@param  mapCoordinate     Description of Parameter
    *@param  map               Description of Parameter
    */
    
    public void renderTile( java.awt.Graphics g, int renderX, int renderY, int mapX, int mapY, TerrainMap map );
    
    /**
    *  Gets the terrainType attribute of the TileViewInterface object
    *
    *@return    The terrainType value
    */
    
    public String getTerrainType();
}
