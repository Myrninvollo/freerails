/*
 * TrackRuleList.java
 *
 * Created on 21 July 2001, 01:01
 */

package jfreerails.common.trackmodel;
import jfreerails.common.trackmodel.TrackRule;

/**
 *
 * @author  Luke Lindsay
 * @version 
 */
public class TrackRuleList {

    TrackRule[] trackRuleArray;
    
    /** Creates new TrackRuleList */
    public TrackRuleList(TrackRule[] trackRuleArray) {
        this.trackRuleArray=trackRuleArray;
    }

    public TrackRule[] getTrackRuleArray() {
        return trackRuleArray;
        
    }
     public TrackRule getTrackRuleArray(int i) {
        return trackRuleArray[i];
    }
    
}
