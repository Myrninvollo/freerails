
/*
* TrackRuleList.java
*
* Created on 21 July 2001, 01:01
*/
package jfreerails.world.track;

import java.util.ArrayList;
import java.util.Iterator;

import jfreerails.world.misc.FreerailsSerializable;
import jfreerails.world.terrain.TerrainType;




/**
*
* @author  Luke Lindsay
*/


final public class TrackRuleList implements FreerailsSerializable{

    public static final int DEFAULT_TRACK_TYPE = 0;

    public static final int NO_TRACK = -1;

    /**
     *  Object
     */
    ArrayList trackRuleArrayList;

    public int getLength() {
        return trackRuleArrayList.size();
    }

    /** Creates new TrackRuleList */

    public TrackRuleList( TrackRule[] trackRuleArray ) {
        this.trackRuleArrayList = new ArrayList(trackRuleArray.length);
        for (int i=0;i<trackRuleArray.length;i++){
           trackRuleArrayList.add(i,(Object)trackRuleArray[i]);
        }
    }

     /** Creates new TrackRuleList */

    public TrackRuleList(ArrayList inputArrayList ) {
        this.trackRuleArrayList = new ArrayList(inputArrayList.size());
        Iterator iterator=inputArrayList.iterator();
       while (iterator.hasNext()){
           TrackRule rule=(TrackRule)(iterator.next());
           trackRuleArrayList.add((Object)rule);
        }
    }



    public java.util.ArrayList getTrackRulesBuildableOnThisTerrain( TerrainType tileModel ) {
        String  terrainName = tileModel.getTerrainTypeName();
        java.util.ArrayList  trackRulesBuildableOnThisTerrain = new java.util.ArrayList();
        for( int  i = 0;i < trackRuleArrayList.size();i++ ) {
            TrackRule rule=(TrackRule)(trackRuleArrayList.get(i));
            if( rule.canBuildOnThisTerrainType( terrainName ) ) {
                trackRulesBuildableOnThisTerrain.add( rule );
            }
        }
        return trackRulesBuildableOnThisTerrain;
    }

    public TrackRule getTrackRule( int i ) {
        return(TrackRule)(trackRuleArrayList.get(i));
    }

}
