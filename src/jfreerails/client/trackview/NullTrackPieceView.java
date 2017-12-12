/*
 * (c) Copyright 2001 MyCorporation.
 * All Rights Reserved.
 */
package jfreerails.client.trackview;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

/**
 * This class implements the TrackPieceView interface, but
 * intentionally does nothing.  Its methods are called when
 * drawing tiles with no track.
 * 
 * @version 	1.0
 * @author
 */
final public class NullTrackPieceView implements TrackPieceView {

	public static final NullTrackPieceView instance=new NullTrackPieceView();
	
	private NullTrackPieceView(){
	}

	/*
	 * @see TrackPieceView#getTrackPieceIcon(int)
	 */
	public Image getTrackPieceIcon(int trackTemplate)  {
		return null;
	}

	/*
	 * @see TrackPieceView#drawTrackPieceIcon(int, Graphics, int, int, Dimension)
	 */
	public void drawTrackPieceIcon(
		int trackTemplate,
		Graphics g,
		int x,
		int y,
		Dimension tileSize)
		 {
		 	//Draw nothing since there no track here.
	}

}
