package jfreerails.world.track;

import jfreerails.world.train.FreerailsPathIterator;
import jfreerails.world.train.IntLine;

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

	/**
	 * @see jfreerails.world.train.FreerailsPathIterator#hasNext()
	 */
	public boolean hasNext() {
		return trackExplorer.hasNextBranch();
	}

	/**
	 * @see jfreerails.world.train.FreerailsPathIterator#nextSegment(IntLine)
	 */
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
