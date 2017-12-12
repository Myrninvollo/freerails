package jfreerails;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import jfreerails.client.common.ImageSplitter;
import jfreerails.client.renderer.SideOnTrainTrainViewImages;
import jfreerails.client.renderer.TileRendererList;
import jfreerails.client.renderer.TrackPieceRendererList;
import jfreerails.client.renderer.ViewLists;
import jfreerails.world.top.World;

public class ViewListsImpl implements ViewLists {

	private final TileRendererList tiles;
	private final TrackPieceRendererList trackPieceViewList;

	private final SideOnTrainTrainViewImages sideOnTrainTrainView;

	public ViewListsImpl() throws IOException {

		//Load the xml file specifying terrain types.
		URL tiles_xml_url = ViewListsImpl.class.getResource("/jfreerails/data/terrain_tiles.xml");

		//Load the picture containing the tile graphics.
		URL tiles_url = ViewListsImpl.class.getResource("/jfreerails/data/terrain_tiles.png");
		ImageSplitter terrain = new ImageSplitter(tiles_url);

		TileSetFactory tileFactory = new jfreerails.TileSetFactoryImpl(tiles_xml_url, terrain);

		//Get tile images from the picture as specified by the xml file.
		tiles = tileFactory.getTileViewList();
		java.awt.Point tilesSize = tileFactory.getTileSize();

		//Load the track graphics and create the trackset
		URL track_tiles_url = ViewListsImpl.class.getResource("/jfreerails/data/track_tiles.png");
		
		
		URL track_xml_url = ViewListsImpl.class.getResource("/jfreerails/data/track_tiles.xml");
		ImageSplitter track = new ImageSplitter(track_tiles_url);
		TrackSetFactory trackSetFactory = new SAX2TracksetFactoryImpl(track_xml_url, track);

		//Get the track graphics and track rules.
		trackPieceViewList = trackSetFactory.getTrackViewList(track);

		//wagon views
		Image tempImage=null;

		sideOnTrainTrainView = new SideOnTrainTrainViewImages(5, 3);
		URL wagon = ViewListsImpl.class.getResource("/jfreerails/data/wagon_151x100.png");
		System.out.println(wagon);
		tempImage = (new javax.swing.ImageIcon(wagon) ).getImage();
		sideOnTrainTrainView.setWagonImage(0, tempImage);
		sideOnTrainTrainView.setWagonImage(1, tempImage);
		sideOnTrainTrainView.setWagonImage(2, tempImage);
		sideOnTrainTrainView.setWagonImage(3, tempImage);
		sideOnTrainTrainView.setWagonImage(4, tempImage);

		//engine views
		
		URL engine = ViewListsImpl.class.getResource("/jfreerails/data/engine_350x100.png");
		System.out.println(engine);
		tempImage = (new javax.swing.ImageIcon(engine)).getImage();
		sideOnTrainTrainView.setEngineImage(0, tempImage);
		sideOnTrainTrainView.setEngineImage(1, tempImage);
		sideOnTrainTrainView.setEngineImage(2, tempImage);
		System.out.println("Done");

	}

	public TileRendererList getTileViewList() {
		return this.tiles;
	}

	public TrackPieceRendererList getTrackPieceViewList() {
		return this.trackPieceViewList;
	}

	public boolean validate(World w) {
		boolean okSoFar = true;
		if (!this.tiles.validate(w)) {
			okSoFar = false;
		}
		if (!this.trackPieceViewList.validate(w)) {
			okSoFar = false;
		}
		return okSoFar;
	}

	public SideOnTrainTrainViewImages getSideOnTrainTrainViewImages() {
		return sideOnTrainTrainView;
	}

}
