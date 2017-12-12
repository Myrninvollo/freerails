package jfreerails.world;
import jfreerails.world.std_track.TrackAndTerrainTileMap;

import experimental.FreerailsSerializable;

public interface World extends Types, FreerailsSerializable  {

	TrackAndTerrainTileMap getMap();	

}
