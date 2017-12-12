package jfreerails;
import java.awt.Point;

import jfreerails.client.tileview.TileViewList;
import jfreerails.world.terrain.TerrainTileTypesList;



/**
*  Description of the Interface
*
*@author     Luke Lindsay
*     09 October 2001
*/


public interface TileSetFactory {



     TerrainTileTypesList getTerrainTileTypesList();



     Point getTileSize();



     TileViewList getTileViewList();
}
