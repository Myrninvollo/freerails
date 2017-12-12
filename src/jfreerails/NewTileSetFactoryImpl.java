/*
 * Created on 28-Apr-2003
 * 
 */
package jfreerails;

import java.awt.Point;

import jfreerails.client.renderer.TileRendererList;
import jfreerails.server.parser.CargoAndTerrainHandlerImpl;
import jfreerails.server.parser.CargoAndTerrainParser;
import jfreerails.server.parser.RunTypesParser;
import jfreerails.world.top.World;

/**
 * @author Luke
 * 
 */
public class NewTileSetFactoryImpl implements TileSetFactory {

	World world;
	TileRendererList tileRendererList;

	public void addTerrainTileTypesList(World w) {
		try {

			world = w;
			java.net.URL url =
				RunTypesParser.class.getResource("/jfreerails/data/cargo_and_terrain.xml");
			
			CargoAndTerrainParser.parse(url, new CargoAndTerrainHandlerImpl(w));

			tileRendererList = new QuickRGBTileRendererList(w);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalStateException();
		}

	}

	public Point getTileSize() {
		return new Point(30, 30);
	}

	public TileRendererList getTileViewList() {

		return tileRendererList;
	}

}
