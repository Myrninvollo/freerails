
/*
 *  FreeRailsMap.java
 *
 *  Created on 08 August 2001, 14:01
 */
package jfreerails.map;
import java.awt.Point;




public interface FreerailsMap{

    /** Get the terrain map*/
    public TerrainMap getTerrainMap();

    /** Get the track map */
    public TrackSystem getTrackMap();
    
    
}
