
/*
* TrackPieceViewList.java
*
* Created on 21 July 2001, 01:04
*/
package jfreerails.client.trackview;
import jfreerails.client.trackview.TrackPieceView;

/**
*
* @author  Luke Lindsay
* @version 
*/


public class TrackPieceViewList {

    private TrackPieceView[] trackPieceViewArray;
    
    public TrackPieceView[] getTrackPieceViewArray() {
        return trackPieceViewArray;
    }
    
    public TrackPieceView getTrackPieceView( int i ) {
        return trackPieceViewArray[ i ];
    }
    
    /** Creates new TrackPieceViewList */
    
    public TrackPieceViewList( TrackPieceView[] trackPieceViewArray ) {
        this.trackPieceViewArray = trackPieceViewArray;
    }
}
