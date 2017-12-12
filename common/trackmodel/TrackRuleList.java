
/*
* TrackRuleList.java
*
* Created on 21 July 2001, 01:01
*/
package jfreerails.common.trackmodel;

/**
*
* @author  Luke Lindsay
* @version 
*/


public class TrackRuleList {

    TrackRule[] trackRuleArray;
    
    public java.util.ArrayList getTrackRulesBuildableOnThisTerrain( jfreerails.common.TileModel tileModel ) {
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
    
    public int getLength() {
        return trackRuleArray.length;
    }
    
    /** Creates new TrackRuleList */
    
    public TrackRuleList( TrackRule[] trackRuleArray ) {
        this.trackRuleArray = trackRuleArray;
    }
    
    public TrackRule[] getTrackRuleArray() {
        return trackRuleArray;
    }
}
