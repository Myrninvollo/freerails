package jfreerails;
import java.awt.Point;

import jfreerails.client.renderer.TileRendererList;
import jfreerails.world.top.World;



/**
*  Description of the Interface
*
*@author     Luke Lindsay
*     09 October 2001
*/


public interface TileSetFactory {



     void addTerrainTileTypesList(World w);



     Point getTileSize();



     TileRendererList getTileViewList();
}
