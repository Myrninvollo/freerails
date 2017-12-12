

package jfreerails.world;
import jfreerails.world.std_track.TrackAndTerrainTileMap;
import jfreerails.world.train.TrainList;



/**
 * Represents ...
 * 
 * @see OtherClasses
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





