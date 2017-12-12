/*
 * Created on 22-Mar-2003
 * 
 */
package jfreerails;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import jfreerails.world.terrain.TerrainType;
import jfreerails.world.top.KEY;
import jfreerails.world.top.WorldImpl;
import jfreerails.world.track.FreerailsTile;

/**
 * @author Luke
 * 
 */
public class MapFactory {

	public static void setupMap(URL map_url, WorldImpl w) {

		Image mapImage = (new javax.swing.ImageIcon(map_url)).getImage();
		Rectangle mapRect = new java.awt.Rectangle(0, 0, mapImage.getWidth(null), mapImage.getHeight(null));
		BufferedImage mapBufferedImage = new BufferedImage(mapRect.width, mapRect.height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = mapBufferedImage.getGraphics();
		g.drawImage(mapImage, 0, 0, null);
		w.setupMap(mapRect.width, mapRect.height);
		
		
		HashMap rgb2TerrainType = new HashMap();
		for(int i=0; i< w.size(KEY.TERRAIN_TYPES);i++ ){
			TerrainType tilemodel = (TerrainType) w.get(KEY.TERRAIN_TYPES, i);
			rgb2TerrainType.put(new Integer(tilemodel.getRGB()), tilemodel);						
		}

		for (int x = 0; x < mapRect.width; x++) {
			for (int y = 0; y < mapRect.height; y++) {
				int rgb = mapBufferedImage.getRGB(x, y);				
				FreerailsTile tile;
				TerrainType type = (TerrainType)rgb2TerrainType.get(new Integer(rgb));		
				if(null==type){
					throw new NullPointerException("There is no terrain type mapped to rgb value "+rgb+" at location "+x+", "+y);		 
				}
				tile = new FreerailsTile(type);
				w.setTile(x, y, tile);
			}
		}

	}

}
