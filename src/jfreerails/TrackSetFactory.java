package jfreerails;
import java.awt.Point;

import jfreerails.client.trackview.TrackPieceViewList;
import jfreerails.lib.ImageSplitter;
import jfreerails.world.track.TrackRuleList;


/**
*  Description of the Interface
*
*@author     Luke Lindsay
*     09 October 2001
*/


public interface TrackSetFactory {



     TrackPieceViewList getTrackViewList( ImageSplitter trackImageSplitter ) ;



     TrackRuleList getTrackRuleList();



     Point getTrackPieceSize();
}
