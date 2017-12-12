
/*
* TerrainTileTypesList.java
*
* Created on 30 July 2001, 14:28
*/
package jfreerails.common;
import java.util.*;

/** This class encapsulates the list of terrain types that
* where loaded.
*
* @author Luke Lindsay
* @version 1.0
*/


public class TerrainTileTypesList {

    private HashMap terrainModels;

    private ArrayList tilesModels;

    private HashMap terrainNames;

    private HashMap terrainRGBValues;
    
    public int getTerrainRGBValue( java.lang.String name ) {
        Integer  i = (Integer)terrainRGBValues.get( name );
        return i.intValue();
    }
    
    public TileModel getTerrainModel( int rgb ) {
        return (TileModel)terrainModels.get( new Integer( rgb ) );
    }
    
    /** Creates new TerrainTileTypesList
    * @param tilesModels The array of tile models ith which to initiate the object.
    */
    
    public TerrainTileTypesList( ArrayList tilesModels ) {
        this.tilesModels = tilesModels;
        terrainNames = new HashMap();
        terrainRGBValues = new HashMap();
        terrainModels = new HashMap();
        for( int  i = 0;i < tilesModels.size();i++ ) {
            TileModel  tilemodel = ( (TileModel)tilesModels.get( i ) );
            terrainNames.put( new Integer( tilemodel.getRGB() ), tilemodel.getTerrainType() );
            terrainRGBValues.put( tilemodel.getTerrainType(), new Integer( tilemodel.getRGB() ) );
            terrainModels.put( new Integer( tilemodel.getRGB() ), tilemodel );
        }
    }
    
    public String getTerrainName( int rgb ) {
        return (String)terrainNames.get( new Integer( rgb ) );
    }
}
