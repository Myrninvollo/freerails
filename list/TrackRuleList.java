
/*
* TrackRuleList.java
*
* Created on 21 July 2001, 01:01
*/
package jfreerails.list;
import java.util.ArrayList;

import jfreerails.type.TileType;
import jfreerails.type.TrackRule;



/**
*
* @author  Luke Lindsay
* @version
*/


public class TrackRuleList {

    public static final int DEFAULT_TRACK_TYPE = 0;

    public static final int NO_TRACK = -1;

    TrackRule[] trackRuleArray;
    
    public int getLength() {
        return trackRuleArray.length;
    }
    
    /** Creates new TrackRuleList */
    
    public TrackRuleList( TrackRule[] trackRuleArray ) {
        this.trackRuleArray = trackRuleArray;
    }
    
    
    
    public java.util.ArrayList getTrackRulesBuildableOnThisTerrain( jfreerails.type.TileType tileModel ) {
        String  terrainName = tileModel.getTerrainType();
        java.util.ArrayList  trackRulesBuildableOnThisTerrain = new java.util.ArrayList();
        for( int  i = 0;i < trackRuleArray.length;i++ ) {
            if( trackRuleArray[ i ].canBuildOnThisTerrainType( terrainName ) ) {
                trackRulesBuildableOnThisTerrain.add( trackRuleArray[ i ] );
            }
        }
        return trackRulesBuildableOnThisTerrain;
    }
    
    public TrackRule getTrackRule( int i ) {
        return trackRuleArray[ i ];
    }
}
