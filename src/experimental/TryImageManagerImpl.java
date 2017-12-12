/*
 * Created on 30-Apr-2003
 * 
 */
package experimental;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.net.URL;

import jfreerails.NewTileSetFactoryImpl;
import jfreerails.TileSetFactory;
import jfreerails.ViewListsImpl;
import jfreerails.client.common.ImageManager;
import jfreerails.client.common.ImageManagerImpl;
import jfreerails.client.renderer.TileRenderer;
import jfreerails.client.renderer.TileRendererList;
import jfreerails.client.renderer.TrackPieceRenderer;
import jfreerails.client.renderer.WagonRenderer;
import jfreerails.world.top.WorldImpl;

/**
 * @author Luke
 * 
 */
public class TryImageManagerImpl {

	public static void main(String[] args) {
		try {

			GraphicsConfiguration defaultConfiguration =
				GraphicsEnvironment
					.getLocalGraphicsEnvironment()
					.getDefaultScreenDevice()
					.getDefaultConfiguration();

			URL in = TryImageManagerImpl.class.getResource("/experimental");
			URL out = TryImageManagerImpl.class.getResource("/experimental/graphics");

			
			ImageManager imageManager = new ImageManagerImpl(in.getPath(), out.getPath());

			WorldImpl w = new WorldImpl();

			TileSetFactory tileFactory = new NewTileSetFactoryImpl();
			tileFactory.addTerrainTileTypesList(w);	

			TileRendererList tiles = (new ViewListsImpl(w)).getTileViewList(); 
			
			System.out.println(tiles.getLength());
			for (int i = 0; i < tiles.getLength(); i++) {
				TileRenderer renderer = tiles.getTileViewWithNumber(i);
				renderer.dumpImages(imageManager);
			}
			TrackPieceRenderer[] track = ViewListsImpl.loadTrackViews().getTrackPieceViewArray();
			for (int i = 0; i < track.length; i++) {
				track[i].dumpImages(imageManager);
			}
			
			WagonRenderer.writeImages(imageManager, w);

			imageManager.writeAllImages();

			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
