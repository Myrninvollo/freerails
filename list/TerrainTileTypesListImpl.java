
/*
*  TerrainTileTypesList.java
*
*  Created on 30 July 2001, 14:28
*/
package jfreerails.list;
import java.util.*;
import jfreerails.type.*;
import jfreerails.list.*;



/**
*  This class encapsulates the list of terrain types that where loaded.
*
*@author     Luke Lindsay
*@created    09 October 2001
*@version    1.0
*/


public class TerrainTileTypesListImpl implements TerrainTileTypesList {
    /**
     * @associates String 
     */
    private HashMap terrainNames;

    /**
     * @associates Integer 
     */
    private HashMap terrainRGBValues;

    /**
     * @associates TileType 
     */
    private HashMap terrainModels;

    private ArrayList tilesModels;
    
    /**
    *  Gets the terrainModel attribute of the TerrainTileTypesList object
    *
    *@param  rgb  Description of Parameter
    *@return      The terrainModel value
    */
    
    public TileType getTerrainModel( int rgb ) {
        return (TileType)terrainModels.get( new Integer( rgb ) );
    }
    
    /**
    *  Gets the terrainName attribute of the TerrainTileTypesList object
    *
    *@param  rgb  Description of Parameter
    *@return      The terrainName value
    */
    
    public String getTerrainName( int rgb ) {
        return (String)terrainNames.get( new Integer( rgb ) );
    }
    
    /**
    *  Creates new TerrainTileTypesList
    *
    *@param  tilesModels  The array of tile models ith which to initiate the
    *      object.
    */
    
    public TerrainTileTypesListImpl( ArrayList tilesModels ) {
        this.tilesModels = tilesModels;
        terrainNames = new HashMap();
        terrainRGBValues = new HashMap();
        terrainModels = new HashMap();
        for( int  i = 0;i < tilesModels.size();i++ ) {
            TileType  tilemodel = ( (TileType)tilesModels.get( i ) );
            terrainNames.put( new Integer( tilemodel.getRGB() ), tilemodel.getTerrainType() );
            terrainRGBValues.put( tilemodel.getTerrainType(), new Integer( tilemodel.getRGB() ) );
            terrainModels.put( new Integer( tilemodel.getRGB() ), tilemodel );
        }
    }
    
    /**
    *  Gets the terrainRGBValue attribute of the TerrainTileTypesList object
    *
    *@param  name  Description of Parameter
    *@return       The terrainRGBValue value
    */
    
    public int getTerrainRGBValue( java.lang.String name ) {
        Integer  i = (Integer)terrainRGBValues.get( name );
        return i.intValue();
    }
}
