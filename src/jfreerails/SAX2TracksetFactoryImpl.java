/*
 * SAX2TracksetFactoryImpl.java
 *
 * Created on 22 January 2002, 14:57
 */

package jfreerails;
import java.awt.Point;
import java.net.URL;

import jfreerails.client.common.ImageSplitter;
import jfreerails.world.top.World;
/**
 *
 * @author  lindsal
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
	 * Gets the trackViewList attribute of the TRackSetFactInterface object
	 *
	 * @param  trackImageSplitter      Description of Parameter
	 * @return                         The trackViewList value
	 * @exception  FreerailsException  Description of Exception
	 */
	public jfreerails.client.renderer.TrackPieceRendererList getTrackViewList(
		ImageSplitter trackImageSplitter) {
		return track_Tiles_View_Handler.getTrackPieceViewList();
	}

	public void addTrackRules(World w) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("not implemented yet");
		
	}

}