
/*
 *  FreerailsMapImpl.java
 *
 *  Created on 08 August 2001, 14:01
 */
package jfreerails.map;


import java.awt.Point;
import java.net.URL;

import jfreerails.common.exception.FreerailsException;
import jfreerails.list.TerrainTileTypesList;
import jfreerails.list.TrackRuleList;
import jfreerails.type.TileType;




/** This class encapsulates and provides access to the objects that compose the
 * map. E.g. the terrain map, the track map, and the train map.
 * @author Luke Lindsay
 * @created 09 October 2001
 * @version 1.0
 */

public class FreerailsMapImpl implements FreerailsMap {

    /** The terrain map
     */
    private TerrainMap terrainLayer;

    /** The track map
     */
    private TrackSystem trackLayer;
    
    private int height, width;


   /*
     *
     * @param w                       Description of the Parameter
     * @param h                       Description of the Parameter
     * @param terrainTileTypesList    Description of the Parameter
     * @exception FreerailsException  Description of the Exception
     */

    public FreerailsMapImpl(int w, int h, TerrainTileTypesList terrainTileTypesList, TrackRuleList trackRuleList) throws FreerailsException {
    	height=h;
    	width=w;
        terrainLayer = new TerrainMapImpl(w, h, terrainTileTypesList);
        trackLayer = new TrackSystemImpl( trackRuleList, terrainLayer);
    }
    
    
	public static FreerailsMapImpl getInstanceWithExptTerrain(int w, int h, TrackRuleList trackRuleList){
		try{
			//Quickly knock up a terrain types list...
			TerrainTileTypesList terrainTileTypesList = new TerrainTileTypesList (){
			TileType tileType = new TileType() {
				public int getRGB() {
					return 0;
				}
				public String getTerrainType() {
					return "Expt terrain type";
				}
			};
			public String getTerrainName(int rgb){
				return "Expt terrain type";
			}
			public int getTerrainRGBValue(String name) {
				return 0;
			}
			public TileType getTerrainModel(int rgb) {
				return tileType;
			}
		};
		FreerailsMapImpl map=new FreerailsMapImpl(w, h, terrainTileTypesList, trackRuleList);
		return map;
		}
		
		catch (FreerailsException fe){
			fe.printStackTrace();
			return null;
		}
	}
			


    /**
     * @param url
     * @param terrainTileTypesList
     * @throws FreerailsException  */    
    public FreerailsMapImpl(URL url, TerrainTileTypesList terrainTileTypesList, TrackRuleList trackRuleList) throws FreerailsException {
        terrainLayer = new TerrainMapImpl(url, terrainTileTypesList);
        trackLayer = new TrackSystemImpl(trackRuleList, terrainLayer);
    }
    
    /** Get the terrain map */
    public TerrainMap getTerrainMap() {
        return terrainLayer;
    }
    
    /** Get the track map  */
    public TrackSystem getTrackMap() {
        return trackLayer;
    }
    
   
}
