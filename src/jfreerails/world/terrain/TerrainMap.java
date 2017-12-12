package jfreerails.world.terrain;



/**
 *  Description of the Interface
 *
 *@author     Luke Lindsay
 *     07 November 2001
 */
public interface TerrainMap {

    int getTerrainTileType(int x, int y);


    String getTerrainTypeName(int x, int y);


    int getWidth();


    int getHeight();



}
