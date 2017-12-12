package jfreerails.client.trackview;
import java.awt.Image;

/**
*  Description of the Interface
*
*@author     Luke Lindsay
*     09 October 2001
*/

public interface TrackPieceView {

	 Image getTrackPieceIcon(int trackTemplate);

	 void drawTrackPieceIcon(
		int trackTemplate,
		java.awt.Graphics g,
		int x,
		int y,
		java.awt.Dimension tileSize);
}