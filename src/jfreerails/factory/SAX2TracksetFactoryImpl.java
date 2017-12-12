/*
 * SAX2TracksetFactoryImpl.java
 *
 * Created on 22 January 2002, 14:57
 */

package jfreerails.factory;
import java.awt.Point;
import java.net.URL;

import jfreerails.lib.ImageSplitter;
import jfreerails.xmlparsers.Track_Tiles_View_Handler;
import jfreerails.world.TrackRuleList;
/**
 *
 * @author  lindsal
 * @version 
 */
final public class SAX2TracksetFactoryImpl
	implements TrackSetFactory {

	Track_Tiles_View_Handler track_Tiles_View_Handler;

	/** Creates new SAX2TracksetFactoryImpl */
	public SAX2TracksetFactoryImpl(URL url, ImageSplitter imageSplitter) {
		track_Tiles_View_Handler = new Track_Tiles_View_Handler(imageSplitter, url);
	}

	/**
	 * Gets the trackPieceSize attribute of the TRackSetFactInterface object
	 *
	 * @return                         The trackPieceSize value
	 * @exception  FreerailsException  Description of Exception
	 */
	public Point getTrackPieceSize() {
		return new Point(30, 30);
	}

	/**
	 * Gets the trackRuleList attribute of the TRackSetFactInterface object
	 *
	 * @return                         The trackRuleList value
	 * @exception  FreerailsException  Description of Exception
	 */
	public TrackRuleList getTrackRuleList() {
		return track_Tiles_View_Handler.getTrackRuleList();
	}

	/**
	 * Gets the trackViewList attribute of the TRackSetFactInterface object
	 *
	 * @param  trackImageSplitter      Description of Parameter
	 * @return                         The trackViewList value
	 * @exception  FreerailsException  Description of Exception
	 */
	public jfreerails.client.trackview.TrackPieceViewList getTrackViewList(
		ImageSplitter trackImageSplitter) {
		return track_Tiles_View_Handler.getTrackPieceViewList();
	}

}