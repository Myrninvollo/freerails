package jfreerails;
import java.awt.Point;

import jfreerails.client.common.ImageSplitter;
import jfreerails.client.renderer.TrackPieceRendererList;
import jfreerails.world.top.World;

/**
*  Description of the Interface
*
*@author     Luke Lindsay
*     09 October 2001
*/

public interface TrackSetFactory {

	TrackPieceRendererList getTrackViewList(ImageSplitter trackImageSplitter);

	void addTrackRules(World w);

	Point getTrackPieceSize();
}
