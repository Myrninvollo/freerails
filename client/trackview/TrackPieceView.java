package jfreerails.client.trackview;
import java.awt.Image;
import java.awt.Dimension;
import jfreerails.common.exception.FreerailsException;
import java.awt.Graphics;

/**
*  Description of the Interface
*
*@author     Luke Lindsay
*@created    09 October 2001
*/


public interface TrackPieceView {
    
    /**
    *  Gets the trackPieceIcon attribute of the TrackPieceViewInterface object
    *
    *@param  trackTemplate           Description of Parameter
    *@return                         The trackPieceIcon value
    *@exception  FreerailsException  Description of Exception
    */
    
    public Image getTrackPieceIcon( int trackTemplate ) throws FreerailsException;
    
    /**
    *  Description of the Method
    *
    *@param  trackTemplate           Description of Parameter
    *@param  g                       Description of Parameter
    *@param  x                       Description of Parameter
    *@param  y                       Description of Parameter
    *@param  tileSize                Description of Parameter
    *@exception  FreerailsException  Description of Exception
    */
    
    public void drawTrackPieceIcon( int trackTemplate, java.awt.Graphics g, int x, int y, java.awt.Dimension tileSize ) throws FreerailsException;
}
