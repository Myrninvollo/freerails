package jfreerails.world;
import jfreerails.list.TerrainTileTypesList;
import jfreerails.list.TrackRuleList;
import jfreerails.map.FreerailsMap;


/**
 * Defining operations expected of ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public interface World {
	
	public FreerailsMap getMap();
	public TerrainTileTypesList getTerrainTileTypesList();
	public TrackRuleList getTrackRuleList();
	
    

}

