package jfreerails.world;

import jfreerails.world.misc.FreerailsSerializable;
import jfreerails.world.track.TrackAndTerrainTileMap;
import jfreerails.world.train.TrainList;


public interface World extends Types, FreerailsSerializable  {

	TrackAndTerrainTileMap getMap();
	
	TrainList getTrainList();	

}
