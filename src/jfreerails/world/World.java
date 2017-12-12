package jfreerails.world;
import jfreerails.list.TerrainTileTypesList;
import jfreerails.list.TrackRuleList;
import jfreerails.world.std_track.TrackAndTerrainTileMap;

public interface World extends Types{

	TrackAndTerrainTileMap getMap();	

}
