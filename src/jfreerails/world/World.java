package jfreerails.world;
import jfreerails.lib.FreerailsSerializable;
import jfreerails.world.std_track.TrackAndTerrainTileMap;
import jfreerails.world.train.TrainList;


public interface World extends Types, FreerailsSerializable  {

	TrackAndTerrainTileMap getMap();
	
	TrainList getTrainList();	

}
