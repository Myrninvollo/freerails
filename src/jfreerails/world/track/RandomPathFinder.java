package jfreerails.world.track;

import jfreerails.world.common.FreerailsPathIterator;
import jfreerails.world.common.IntLine;
import jfreerails.world.common.PositionOnTrack;

/**
 * @author Luke Lindsay 13-Oct-2002
 *
 */
public class RandomPathFinder implements FreerailsPathIterator {

	TrackExplorer trackExplorer;

	static final int tileSize=30;

	public RandomPathFinder(TrackExplorer tx){
		trackExplorer=tx;
	}


	public boolean hasNext() {
		return trackExplorer.hasNextBranch();
	}


	public void nextSegment(IntLine line) {
		PositionOnTrack p1=trackExplorer.getCurrentPosition();
		line.x1=p1.getX()*tileSize+tileSize/2;
		line.y1=p1.getY()*tileSize+tileSize/2;
		trackExplorer.nextBranch();
		trackExplorer.moveForward();
		PositionOnTrack p2=trackExplorer.getCurrentPosition();
		line.x2=p2.getX()*tileSize+tileSize/2;
		line.y2=p2.getY()*tileSize+tileSize/2;
	}

}
