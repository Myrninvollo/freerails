/*
 * TrackFactory.java
 *
 * Created on 18 July 2001, 19:42
 */

package jfreerails.common.trackmodel;

import java.net.URL;
import java.awt.Image;
import jfreerails.common.trackmodel.TrackRule;
import jfreerails.common.trackmodel.TrackNode;
import jfreerails.client.trackview.TrackPieceView;
import jfreerails.common.trackmodel.TrackRule;
import jfreerails.common.exception.FreerailsException;



import jfreerails.lib.ImageSplitter;
/**
 *
 * @author  Luke Lindsay
 * @version 
 */
public class TrackSetFactory extends java.lang.Object {

    private URL trackXMLFilename;
    
    private ImageSplitter trackImageSplitter;
    
    private TrackRule[] trackRulesArray;
    
    private TrackPieceView[] trackPieceViewArray;
    
    private  int[] trackTemplates={18,   //half straight
                          146,  //full straight
                          274,  //curve
                          402,  //left-hand points
                          210,  //right-hand points
                          186,  //crossing(X)
                          50,   //90 degree bend
                          22,   //half built points
                          58,   //half bulit crossing
                            };
    
    /** Creates new TrackFactory */
    public TrackSetFactory(ImageSplitter trackImageSplitter)  throws FreerailsException {
        this.trackImageSplitter=trackImageSplitter;
    }

    public TrackRule[] getTrackRules()  throws FreerailsException  {
          if(null==trackRulesArray){
            trackRulesArray=new TrackRule[1];
            trackRulesArray[0]=new TrackRule(trackTemplates);
          }
          return    trackRulesArray;
}
    
public TrackPieceView[] getTrackGraphics()  throws FreerailsException  {
    if (null==trackPieceViewArray){
       trackImageSplitter.set_tile_grid( 0, 0, 30, 30 ); 
      trackPieceViewArray =new  TrackPieceView[1]; 
      trackPieceViewArray[0] =new TrackPieceView(trackTemplates, trackImageSplitter);
    }
    return trackPieceViewArray;
}

}
