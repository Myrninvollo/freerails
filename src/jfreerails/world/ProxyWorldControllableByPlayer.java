

package jfreerails.world;

import jfreerails.world.terrain.TerrainTileTypesList;
import jfreerails.world.track.TrackAndTerrainTileMap;
import jfreerails.world.track.TrackRuleList;
import jfreerails.world.train.TrainList;



/**
 *
 *
 *
 * @author lindsal
 */

public class ProxyWorldControllableByPlayer implements World {
	private World world;

	 public ProxyWorldControllableByPlayer(World w){
	 	this.world=w;
	 }
	 public TrackAndTerrainTileMap getMap(){
		return world.getMap();
	}
	public TerrainTileTypesList getTerrainTileTypesList(){
		return world.getTerrainTileTypesList();
	}
	public TrackRuleList getTrackRuleList(){
		return world.getTrackRuleList();
	}
	public TrainList getTrainList(){
		return world.getTrainList();
	}


}





