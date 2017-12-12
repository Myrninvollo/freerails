package jfreerails;

import jfreerails.client.ViewLists;
import java.net.URL;

import jfreerails.client.tileview.TileViewList;
import jfreerails.client.trackview.TrackPieceViewList;
import jfreerails.lib.ImageSplitter;
import jfreerails.world.Types;

public class ViewListsImpl implements ViewLists {

	private final TileViewList tiles;
	private final TrackPieceViewList trackPieceViewList;

	public ViewListsImpl() {

		//Load the xml file specifying terrain types.
		URL tiles_xml_url =
			ViewListsImpl.class.getResource(
				"/jfreerails/data/terrain_tiles.xml");

		//Load the picture containing the tile graphics.
		URL tiles_url =
			ViewListsImpl.class.getResource(
				"/jfreerails/data/terrain_tiles.png");
		ImageSplitter terrain = new ImageSplitter(tiles_url);

		TileSetFactory tileFactory =
			new jfreerails.TileSetFactoryImpl(tiles_xml_url, terrain);

		//Get tile images from the picture as specified by the xml file.
		tiles = tileFactory.getTileViewList();
		java.awt.Point tilesSize = tileFactory.getTileSize();
		
		//Load the track graphics and create the trackset
		URL track_tiles_url =
			ViewListsImpl.class.getResource("/jfreerails/data/track_tiles.png");
		URL track_xml_url =
			ViewListsImpl.class.getResource("/jfreerails/data/track_tiles.xml");
		ImageSplitter track = new ImageSplitter(track_tiles_url);
		TrackSetFactory trackSetFactory =
			new SAX2TracksetFactoryImpl(track_xml_url, track);

		//Get the track graphics and track rules.
		trackPieceViewList = trackSetFactory.getTrackViewList(track);

	}
	
	public TileViewList getTileViewList(){
		return this.tiles;
	}
		
	public TrackPieceViewList getTrackPieceViewList(){
		return this.trackPieceViewList;
	}
	
	public boolean validate(Types t){
		boolean okSoFar=true;
		if(!this.tiles.validate(t.getTerrainTileTypesList())){
			okSoFar=false;
		}
		if(!this.trackPieceViewList.validate(t.getTrackRuleList())){
			okSoFar=false;
		}
		return okSoFar;
	}

}
