package jfreerails.factory;
import jfreerails.common.exception.FreerailsException;
import jfreerails.lib.ImageSplitter;
import java.awt.*;
import jfreerails.list.*;


/**
*  Description of the Interface
*
*@author     Luke Lindsay
*@created    09 October 2001
*/


public interface TrackSetFactory {
    
    /**
    *  Gets the trackViewList attribute of the TRackSetFactInterface object
    *
    *@param  trackImageSplitter      Description of Parameter
    *@return                         The trackViewList value
    *@exception  FreerailsException  Description of Exception
    */
    
    public jfreerails.client.trackview.TrackPieceViewList getTrackViewList( ImageSplitter trackImageSplitter ) throws FreerailsException;
    
    /**
    *  Gets the trackRuleList attribute of the TRackSetFactInterface object
    *
    *@return                         The trackRuleList value
    *@exception  FreerailsException  Description of Exception
    */
    
    public TrackRuleList getTrackRuleList() throws FreerailsException;
    
    /**
    *  Gets the trackPieceSize attribute of the TRackSetFactInterface object
    *
    *@return                         The trackPieceSize value
    *@exception  FreerailsException  Description of Exception
    */
    
    public Point getTrackPieceSize() throws FreerailsException;
}
