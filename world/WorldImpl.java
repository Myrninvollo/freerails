package jfreerails.world;

import jfreerails.common.exception.FreerailsException;
import jfreerails.factory.TileFactory;
import jfreerails.factory.TrackSetFactory;
import jfreerails.list.TerrainTileTypesList;
import jfreerails.list.TrackRuleList;
import jfreerails.map.FreerailsMap;

public class WorldImpl implements World {

	
	private TerrainTileTypesList terrainTileTypesList;
	
	private TrackRuleList trackRuleList;
	
	private FreerailsMap map;
 
	public WorldImpl(TileFactory tileFactory, TrackSetFactory trackSetFactory, FreerailsMap fm) throws FreerailsException{
		if(null==tileFactory||null==trackSetFactory||null==fm){
			throw new java.lang.NullPointerException("Null pointer passed to WorldImpl constructor");
		}
		this.map=fm;
		this.terrainTileTypesList=tileFactory.getTerrainTileTypesList();
		this.trackRuleList=trackSetFactory.getTrackRuleList();
		
	}
	
	public FreerailsMap getMap(){
		return map;
	}
	public TerrainTileTypesList getTerrainTileTypesList(){
		return terrainTileTypesList;
	}
	public TrackRuleList getTrackRuleList(){
		return trackRuleList;
	}
	
}

