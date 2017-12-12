package jfreerails;

import java.net.URL;

import jfreerails.controller.MoveReceiver;
import jfreerails.controller.TrackMoveExecutor;
import jfreerails.world.World;
import jfreerails.world.terrain.TerrainTileTypesList;
import jfreerails.world.track.TrackAndTerrainTileMap;
import jfreerails.world.track.TrackAndTerrainTileMapImpl;
import jfreerails.world.track.TrackRuleList;
import jfreerails.world.train.TrainList;

public class WorldImpl implements World {

	private final TerrainTileTypesList terrainTileTypesList;

	private final TrackRuleList trackRuleList;

	private final TrackAndTerrainTileMap map;
	
	private final TrainList trainList;

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
		trainList= new TrainList();
		//TestOverHeadTrainView.addtrainsTotrainlist(trainList);
		
	}
	
	
	public  static World  createWorldFromMapFile(String mapName) {

		//Load the xml file specifying terrain types.
		URL tiles_xml_url =
			RunFreerails.class.getResource(
				"/jfreerails/data/terrain_tiles.xml");

		TileSetFactory tileFactory =
			new jfreerails.TileSetFactoryImpl(tiles_xml_url);

		TerrainTileTypesList terrainTileTypesList =
			tileFactory.getTerrainTileTypesList();

		URL track_xml_url =
			RunFreerails.class.getResource("/jfreerails/data/track_tiles.xml");

		Track_TilesHandlerImpl trackSetFactory =
			new Track_TilesHandlerImpl(track_xml_url);

		TrackRuleList trackRuleList = trackSetFactory.getTrackRuleList();

		//Load the terrain map
		URL map_url =
			RunFreerails.class.getResource("/jfreerails/data/" + mapName);
		 TrackAndTerrainTileMap map =
			new TrackAndTerrainTileMapImpl(
				map_url,
				terrainTileTypesList,
				trackRuleList);

		//Create the object that controls building track.
		MoveReceiver trackMoveExecutor = new TrackMoveExecutor(map);
		
		
       return new WorldImpl(tileFactory, trackSetFactory, map); 		

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
	public TrainList getTrainList(){
		return trainList;
	}

}