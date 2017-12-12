
/*
* TerrainTileTypesList.java
*
* Created on 30 July 2001, 14:28
*/
package jfreerails.common;
import java.util.*;
import jfreerails.common.TileModel;
import java.lang.Integer;

/**
*
* @author  Luke Lindsay
* @version 
*/


public class TerrainTileTypesList {

    private HashMap terrainRGBValues;

    private HashMap terrainModels;

    private TileModel[] tilesModels;

    private HashMap terrainNames;
    
    public String getTerrainName( int rgb ) {
        return (String)terrainNames.get( new Integer( rgb ) );
    }
    
    public int getTerrainRGBValue( java.lang.String name ) {
        Integer  i = (Integer)terrainRGBValues.get( name );
        return i.intValue();
    }
    
    public TileModel getTerrainModel( int rgb ) {
        return (TileModel)terrainModels.get( new Integer( rgb ) );
    }
    
    /** Creates new TerrainTileTypesList */
    
    public TerrainTileTypesList( TileModel[] tilesModels ) {
        this.tilesModels = tilesModels;
        terrainNames = new HashMap();
        terrainRGBValues = new HashMap();
        terrainModels = new HashMap();
        for( int  i = 0;i < tilesModels.length;i++ ) {
            terrainNames.put( new Integer( tilesModels[ i ].getRGB() ), tilesModels[ i ].getTerrainType() );
            terrainRGBValues.put( tilesModels[ i ].getTerrainType(), new Integer( tilesModels[ i ].getRGB() ) );
            terrainModels.put( new Integer( tilesModels[ i ].getRGB() ), tilesModels[ i ] );
        }
    }
}
