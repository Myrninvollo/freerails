/*
 * Track_Tiles_View_Handler.java
 *
 * Created on 22 January 2002, 12:36
 */

package jfreerails;

import java.awt.Point;
import java.util.ArrayList;

import jfreerails.client.trackview.TrackPieceViewList;
import jfreerails.lib.ImageSplitter;

import org.xml.sax.SAXException;


/**
 *
 * @author  lindsal
 */
final public class Track_Tiles_View_Handler
	extends Track_TilesHandlerImpl {

	java.awt.Dimension tileSize;
	Point grapOrigin;
	Point tilePosition;
	ImageSplitter trackImageSplitter;
	TrackPieceViewList trackPieceViewList;

    /**
     *  TrackPieceViewImpl
     */
	ArrayList trackPieceViews;
	int[] trackPieceTemplateArray;

	/** Creates new Track_Tiles_View_Handler */
	public Track_Tiles_View_Handler(jfreerails.lib.ImageSplitter imageSplitter, java.net.URL trackXmlUrl) {
		trackImageSplitter = imageSplitter;

		if (null == imageSplitter) {
			throw new java.lang.NullPointerException(
				"null==trackImageSplitter, cannot get track icons");
		}
		try {
			Track_TilesParser.parse(trackXmlUrl, this, new Track_TilesParsletImpl());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start_TrackSet(org.xml.sax.Attributes meta) throws SAXException {
		super.start_TrackSet(meta);
		tileSize = new java.awt.Dimension();
		grapOrigin = new Point();
		tileSize.width = (int) Integer.parseInt(meta.getValue("Width"));
		tileSize.height = (int) Integer.parseInt(meta.getValue("Height"));
		grapOrigin.x = (int) Integer.parseInt(meta.getValue("X"));
		grapOrigin.y = (int) Integer.parseInt(meta.getValue("Y"));

		  trackImageSplitter.setTileGrid(grapOrigin.x, grapOrigin.y, tileSize.width, tileSize.height);
		trackPieceViews = new java.util.ArrayList();

	}

	public void end_TrackSet() throws SAXException {

		trackPieceViewList = new jfreerails.client.trackview.TrackPieceViewList(trackPieceViews);
		trackPieceViews = null;
		super.end_TrackSet();
	}

	public void start_TrackType(org.xml.sax.Attributes meta) throws SAXException {
		super.start_TrackType(meta);
		tilePosition = new Point();
		tilePosition.x = (int) Integer.parseInt(meta.getValue("X"));
		tilePosition.y = (int) Integer.parseInt(meta.getValue("Y"));
		trackImageSplitter.setSubGridOffset(tilePosition.x , tilePosition.y );
	}
	public void end_ListOfTrackPieceTemplates() throws SAXException {
		trackPieceTemplateArray = new int[legalTemplates.size()];
		for (int i = 0; i < legalTemplates.size(); i++) {

			//Dirty hack - so that result is as expected by earlier written code.
			StringBuffer strb = new StringBuffer((String) (legalTemplates.get(i)));
			strb = strb.reverse();

			//End of dirty hack
			trackPieceTemplateArray[i] = (int) Integer.parseInt(strb.toString(), 2);
		}

		super.end_ListOfTrackPieceTemplates();
	}

	public void end_TrackType() throws SAXException {
		trackPieceViews.add(
			new jfreerails.client.trackview.TrackPieceViewImpl(trackPieceTemplateArray, trackImageSplitter));
		trackPieceTemplateArray = null;
		super.end_TrackType();
		tilePosition = null;
	}
	public jfreerails.client.trackview.TrackPieceViewList getTrackPieceViewList() {
		return trackPieceViewList;
	}
}