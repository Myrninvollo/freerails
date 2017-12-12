package jfreerails.list;
import jfreerails.type.*;


/**
*  Description of the Interface
*
*@author     Luke Lindsay
*@created    09 October 2001
*/


public interface TerrainTileTypesList {
    
    /**
    *  Gets the terrainName attribute of the TTTL object
    *
    *@param  rgb  Description of Parameter
    *@return      The terrainName value
    */
    
    public String getTerrainName( int rgb );
    
    /**
    *  Gets the terrainRGBValue attribute of the TTTL object
    *
    *@param  name  Description of Parameter
    *@return       The terrainRGBValue value
    */
    
    public int getTerrainRGBValue( java.lang.String name );
    
    /**
    *  Gets the terrainModel attribute of the TTTL object
    *
    *@param  rgb  Description of Parameter
    *@return      The terrainModel value
    */
    
    public TileType getTerrainModel( int rgb );
}
