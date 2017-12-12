package jfreerails;

import java.net.URL;

import jfreerails.client.common.ImageSplitter;
import jfreerails.client.renderer.TileRendererList;
import jfreerails.client.renderer.TrackPieceRendererList;
import jfreerails.client.top.ViewLists;
import jfreerails.world.top.World;

public class ViewListsImpl implements ViewLists {

	private final TileRendererList tiles;
	private final TrackPieceRendererList trackPieceViewList;

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
	
	public TileRendererList getTileViewList(){
		return this.tiles;
	}
		
	public TrackPieceRendererList getTrackPieceViewList(){
		return this.trackPieceViewList;
	}
	
	public boolean validate(World w){
		boolean okSoFar=true;
		if(!this.tiles.validate(w)){
			okSoFar=false;
		}
		if(!this.trackPieceViewList.validate(w)){
			okSoFar=false;
		}
		return okSoFar;
	}

}
