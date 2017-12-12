package jfreerails;

import java.net.URL;

import jfreerails.controller.MoveReceiver;
import jfreerails.controller.TrackMoveExecutor;
import jfreerails.world.top.World;
import jfreerails.world.top.WorldImpl;

public class OldWorldImpl {

	private World w;

	public OldWorldImpl(
		TileSetFactory tileFactory,
		TrackSetFactory trackSetFactory,
		World world) {
		if (null == tileFactory || null == trackSetFactory || null == world) {
			throw new java.lang.NullPointerException(
				"Null pointer passed to WorldImpl constructor");
		}
		this.w = world;
		tileFactory.addTerrainTileTypesList(w);
		trackSetFactory.addTrackRules(w);
	}

	public static World createWorldFromMapFile(String mapName) {

		//Load the xml file specifying terrain types.
		URL tiles_xml_url =
			RunFreerails.class.getResource(
				"/jfreerails/data/terrain_tiles.xml");

		TileSetFactory tileFactory =
			new jfreerails.TileSetFactoryImpl(tiles_xml_url);
			
		WorldImpl w = new WorldImpl();	
		
		WagonAndEngineTypesFactory wetf = new WagonAndEngineTypesFactory();
		wetf.addTypesToWorld(w);

		tileFactory.addTerrainTileTypesList(w);		
				
		URL track_xml_url =
			RunFreerails.class.getResource("/jfreerails/data/track_tiles.xml");

		Track_TilesHandlerImpl trackSetFactory =
			new Track_TilesHandlerImpl(track_xml_url);

		trackSetFactory.addTrackRules(w);
						
		//Load the terrain map
		URL map_url = RunFreerails.class.getResource("/jfreerails/data/" + mapName);		
		MapFactory.setupMap(map_url, w);
		
		//Create the object that controls building track.
		MoveReceiver trackMoveExecutor = new TrackMoveExecutor(w);
		
		return w;
	}
}
