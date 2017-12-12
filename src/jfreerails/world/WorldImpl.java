package jfreerails.world;

import java.net.URL;

import jfreerails.RunFreerails;
import jfreerails.factory.TileSetFactory;
import jfreerails.factory.TrackSetFactory;
import jfreerails.list.TerrainTileTypesList;
import jfreerails.list.TrackRuleList;
import jfreerails.move.receiver.MoveReceiver;
import jfreerails.move.receiver.TrackMoveExecutor;
import jfreerails.parsers.Track_TilesHandlerImpl;
import jfreerails.world.std_track.TrackAndTerrainTileMap;
import jfreerails.world.std_track.TrackAndTerrainTileMapImpl;

public class WorldImpl implements World {

	private final TerrainTileTypesList terrainTileTypesList;

	private final TrackRuleList trackRuleList;

	private final TrackAndTerrainTileMap map;

	public WorldImpl(
		TileSetFactory tileFactory,
		TrackSetFactory trackSetFactory,
		TrackAndTerrainTileMap fm) {
		if (null == tileFactory || null == trackSetFactory || null == fm) {
			throw new java.lang.NullPointerException(
				"Null pointer passed to WorldImpl constructor");
		}
		this.map = fm;
		this.terrainTileTypesList = tileFactory.getTerrainTileTypesList();
		this.trackRuleList = trackSetFactory.getTrackRuleList();

	}
	public WorldImpl(String mapName) {

		//Load the xml file specifying terrain types.
		URL tiles_xml_url =
			RunFreerails.class.getResource(
				"/jfreerails/data/terrain_tiles.xml");

		TileSetFactory tileFactory =
			new jfreerails.factory.TileSetFactoryImpl(tiles_xml_url);

		 terrainTileTypesList =
			tileFactory.getTerrainTileTypesList();

		URL track_xml_url =
			RunFreerails.class.getResource("/jfreerails/data/track_tiles.xml");

		Track_TilesHandlerImpl trackSetFactory =
			new Track_TilesHandlerImpl(track_xml_url);

		trackRuleList = trackSetFactory.getTrackRuleList();

		//Load the terrain map
		URL map_url =
			RunFreerails.class.getResource("/jfreerails/data/" + mapName);
		 map =
			new TrackAndTerrainTileMapImpl(
				map_url,
				terrainTileTypesList,
				trackRuleList);

		//Create the object that controls building track.
		MoveReceiver trackMoveExecutor = new TrackMoveExecutor(map);

	}

	public TrackAndTerrainTileMap getMap() {
		return map;
	}
	public TerrainTileTypesList getTerrainTileTypesList() {
		return terrainTileTypesList;
	}
	public TrackRuleList getTrackRuleList() {
		return trackRuleList;
	}

}